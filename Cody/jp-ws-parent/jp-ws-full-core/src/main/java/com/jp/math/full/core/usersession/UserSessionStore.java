/*
 * File: UserSessionStore.java Date: 05-Aug-2015 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.math.full.core.usersession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dimit Chadha
 */
public class UserSessionStore {

	 /** A reference to the SLF4J object used to record logging activity. */
    private static final Logger LOG = LoggerFactory.getLogger(UserSessionStore.class);
	/** The default initial size for the session store. */
	private static final int INITIAL_SESSION_STORE_SIZE = 100;
	
	 /** The synchronization object used to ensure integrity of the session maps in a multi-threaded environment. */
    private final Object localMapsLock = new Object();
    
    /** The list of Agent Sessions currently being managed by the local instance, keyed by session-id. */
    private final Map<String, UserSession> localSessionsById = new HashMap<>(INITIAL_SESSION_STORE_SIZE);

	/**
	 * The replicated cache of User Sessions being serviced by all instances in
	 * the cluster (if this instance is part of a clustered deployment). If not
	 * part of a clustered deployment, this is null.
	 */
	private Ehcache globalSessionCache;
	
	/**
     * Used to set the replicated cache of User Sessions being serviced by all instances in the cluster. Only called
     * when this instance is part of a clustered deployment.
     */
    public void setGlobalSessionCache(Ehcache globalCache)
    {
        this.globalSessionCache = globalCache;

//        if (globalCache != null)
//        {
//            // we're running with a cache, so hook in a cache-waiter
//            this.updateWaiter = new CachedSessionUpdateWaiter();
//            this.updateWaiter.setGlobalSessionCache(globalCache);
//            globalCache.getCacheEventNotificationService().registerListener(updateWaiter);
//
//            // and if we already have an idle-agent tracker registered, hook in a listener for that too
//            if (idleAgentTracker != null)
//            {
//                globalCache.getCacheEventNotificationService().registerListener(
//                        new CacheIdleTrackingListener(localMemberName, idleAgentTracker));
//            }
//        }
    }
    
    
    /**
     * Internal - generates a new unique Agent Session ID.
     */
    private static String createUniqueSessionId()
    {
        return UUID.randomUUID().toString();
    }
    
    /**
     * Creates and initializes a new Agent Session object that will be owned by the local Resource Manager instance, and
     * will track the specified call center agent. Also broadcasts the new session out to all instances in the cluster.
     * @param agentId the agent id (usually Windows login ID) of the call center the new agent session will track.
     * @param client the 3-alpha client code of the client the call center agent is servicing.
     * @param additionalUserIdentifier an (optional) additional piece of information useful from an operations
     * perspective in identifying the agent being tracked by this session.
     * @return a reference to the new UserSession object.
     */
    public UserSession createNewAgentSession(String userId)
    {
        // create a new session object with a new unique ID, and set the owner to be us
        UserSession newSession = new UserSession(createUniqueSessionId(), userId);
//        newSession.setLocalStore(this);
//        newSession.setOwner(localMemberName);
        LOG.info("New UserSession created for agent id {}, session ID is: {}", newSession.getUserId(), newSession.getSessionId());

        // new session is ours, so add it to the local map
        synchronized (localMapsLock)
        {
            localSessionsById.put(newSession.getSessionId(), newSession);
        }

        // mark the session as being tracked in the
        //newSession.setInSessionStore(true);

        // notify the cluster if required
        localSessionUpdated(newSession);

        return newSession;
    }
    
    /**
     * Called (from internally within this class, or by an AgentSession object itself) whenever a locally owned agent
     * session has been updated (had its state changed).
     * <p/>
     * This method broadcasts the new image of the Agent session out to all instances in the cluster (via the globally
     * replicated {@link #globalSessionCache} Ehcache object.
     */
    public void localSessionUpdated(UserSession updatedSession)
    {
        // wake up any threads waiting on the session for updates
        synchronized (updatedSession)
        {
            updatedSession.notifyAll();
        }

//        // notify the idle-agent tracker if we have one
//        if (idleAgentTracker != null)
//        {
//            idleAgentTracker.agentStateChange(updatedSession.getSessionId(), updatedSession.toCoreLimitedAgentStateInfo());
//        }

        // and if we are playing in a cluster, push the new image of the session out to the cache
        if (globalSessionCache != null)
        {
            globalSessionCache.put(new Element(updatedSession.getSessionId(), updatedSession));
            LOG.trace("localSessionUpdated() pushed new copy of session {} to cache", updatedSession.getSessionId());
        }
    }
    /**
     * Locates and returns the Agent Session keyed by the given ID - only looks for the session in the local session
     * store - the global session cache is not consulted.
     * @param sessionId the session ID of the local Agent Session to be located.
     * @return a reference to the Agent Session if present (i.e. if managed locally), otherwise null.
     */
    public UserSession getLocalSessionById(String sessionId)
    {
        // try to find the session (locally only)
        UserSession session = null;
        synchronized (localMapsLock)
        {
            session = localSessionsById.get(sessionId);
        }
        if (session != null)
        {
            LOG.trace("Session {} found (in local map)", sessionId);
            return session;
        }

        // no session found
        LOG.debug("Session {} not found (looked in local map only)", sessionId);
        return null;
    }

    /**
     * Locates and returns the Agent Session keyed by the given ID, regardless of what instance within the cluster owns
     * the session.
     * <p/>
     * The local session store is always consulted first. If not found in the local store, and the instance is running
     * in a clustered environment, the session lookup is then attempted against the global store.
     * <p/>
     * @param sessionId the session ID of the Agent Session to be located.
     * @return a reference to the Agent Session if present across the cluster, otherwise null.
     */
    public UserSession getSessionById(String sessionId)
    {
        // try to find the session locally first
        UserSession session = null;
        synchronized (localMapsLock)
        {
            session = localSessionsById.get(sessionId);
        }
        if (session != null)
        {
            LOG.trace("Session {} found (in local map)", sessionId);
            return session;
        }

        // wasnt found locally, if we're running in a cluster, look across the cluster
        if (globalSessionCache != null)
        {
            Element gSessionEle = globalSessionCache.get(sessionId);
            if (gSessionEle != null)
            {
                session = (UserSession) gSessionEle.getObjectValue();
                
                // if session found in cache thinks local is the owner, its a bad session (because we should have found
                // it above in the local check) - clean up the failed session in the global cache...
//                if (session.getOwner().equals(localMemberName))
//                {
//                    LOG.warn("getSessionById({}) found orphaned session (was in global cache, indicating local is the owner, but not in local maps) - cleaning up session", sessionId);
//                    globalSessionCache.remove(session.getSessionId());
//                    session = null;
//                }
//                LOG.debug("Session {} found (in global cache - owner is {})", sessionId, session.getOwner());
                return session;
            }
        }

        // no session found anywhere
        LOG.debug("Session {} not found (either locally or globally)", sessionId);
        return null;
    }

    /**
     * Locates and returns the Agent Session associated with the given Agent ID, regardless of what instance within the
     * cluster owns the session.
     * <p/>
     * The local session store is always consulted first. If not found in the local store, and the instance is running
     * in a clustered environment, the session lookup is then attempted against the global store.
     * <p/>
     * NOTE: This isn't a cheap call (the session maps are NOT keyed by Agent ID). The expectation is this method will
     * only be called prior to a new Agent Session registration (to disallow multiple sessions for th same agent-id),
     * and not during regular session processing).
     * <p/>
     * @param agentId the agentId of the Agent Session to be located.
     * @return a reference to the Agent Session if present across the cluster, otherwise null.
     */
    public UserSession getSessionByAgentId(String userId)
    {
        // try to find the session locally first
        UserSession session = null;
        synchronized (localMapsLock)
        {
            // expensive, but not expected to be a high-usage method (only called prior to new agent registration)
            for (Entry<String, UserSession> sessionEntry : localSessionsById.entrySet())
            {
                UserSession iterSession = sessionEntry.getValue();
                if (iterSession.getUserId().equals(userId)) //&& iterSession.isInSessionStore())
                {
                    // found, exit
                    session = iterSession;
                    break;
                }
            }
        }
        if (session != null)
        {
            LOG.trace("Session {} found (in local map) for agent-id {}", session.getSessionId(), userId);
            return session;
        }

        // wasnt found locally, if we're running in a cluster, look across the cluster
        if (globalSessionCache != null)
        {
            // get list of keys in the global cache (includes ours plus everyone elses)
            // (note: returns expired entries too)
            List<String> sessionIds = globalSessionCache.getKeys();

            // get (QUIETLY) the cache objects for every key returned (cant use "getAll()" as its not quiet)
            if (sessionIds != null)
            {
                for (String sessionId : sessionIds)
                {
                    // get cache element for the key
                    Element element = globalSessionCache.getQuiet(sessionId);
                    if (element != null && !element.isExpired())
                    {
                        UserSession iterSession = (UserSession) element.getObjectValue();
                        // if element was found, not expired, then see it its the one we are looking for
                        if (iterSession != null && iterSession.getUserId().equals(userId))
                        {
                            // found, exit
                            session = iterSession;
                            break;
                        }
                    }
                }
            }
        }
        if (session != null)
        {
            LOG.debug("Session {} found (in global cache) for agent-id {}", session.getSessionId(), userId);
            return session;
        }

        // no session found anywhere
        LOG.debug("Session for agentId {} not found (either locally or globally)", userId);
        return null;
    }
}
