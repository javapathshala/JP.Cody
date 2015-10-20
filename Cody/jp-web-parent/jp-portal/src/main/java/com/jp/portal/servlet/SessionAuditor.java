/** File: SessionVerify.java
 *  Date: Apr 13, 2015
 *
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn 
 * &amp; gain ideas from it. Its unauthorised use is explicitly prohibited &amp; any 
 * addition &amp; removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention orders
 * and to prosecute the authors of any infraction.
 * 
 * Visit us at www.javapathshala.com
 **/
package com.jp.portal.servlet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public final class SessionAuditor {

	/**
     * The singleton instance of the {@link SessionAuditor} managed by this
     * class.
     *
     */
    public static final SessionAuditor INSTANCE = new SessionAuditor();
    /**
     * The {@link Logger} used to record audit messages.
     *
     */
    private static final Logger AUDIT_LOGGER = LoggerFactory.getLogger("jp-audit");

    /**
     * An association of current session IDs to remote users.
     *
     * @since 1.0
     */
    private final Map<String, String> activeUsers;

    /**
     * A default constructor used to prevent unnecessary instantiation.
     *
     * @since 1.0
     */
    private SessionAuditor()
    {
        activeUsers = new ConcurrentHashMap<>();
    }
  
    /**
     * Associates the specified session ID with the specified user ID. If no
     * session ID exists in the {@link Map} of {@link #activeUsers}, a message
     * is sent to the {@link #AUDIT_LOGGER} regarding the login event.
     *
     * @param sessionId the session ID of the session that initiated the login.
     * @param remoteUser the user ID of the user associated with the session.
     */
    public void put(String sessionId, String remoteUser)
    {
        // If the map of active user session does not contain the specified session ID
        if (sessionId != null && !activeUsers.containsKey(sessionId))
        {
            // Map the session ID to the remote user
            activeUsers.put(sessionId, remoteUser);
            // Log the association of the session to the remote user
            AUDIT_LOGGER.info("LOGIN completed for {}", remoteUser);
        }
    }
   
    /**
     * De-associates the specified session ID from the specified user ID. If a
     * session ID exists in the {@link Map} of {@link #activeUsers}, a message
     * is sent to the {@link #AUDIT_LOGGER} regarding the logout event.
     *
     * @param sessionId the session ID of the session that initiated the logout.
     */
    public void remove(String sessionId)
    {
        // If the map of active user session does not contain the specified session ID
        if (sessionId != null && activeUsers.containsKey(sessionId))
        {
            // Remove the session ID mapping
            String remoteUser = activeUsers.remove(sessionId);
            // Log the removal of the session to the remote user
            AUDIT_LOGGER.warn("LOGOUT completed for {}", remoteUser);
        }
    }
}
