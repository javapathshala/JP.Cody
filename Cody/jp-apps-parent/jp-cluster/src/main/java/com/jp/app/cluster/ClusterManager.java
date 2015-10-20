/*
 * File: ClusterManager.java
 * Date: 12-Aug-2015
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention
 * orders
 * and to prosecute the authors of any infraction.
 * Visit us at www.javapathshala.com
 */
package com.jp.app.cluster;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jp.status.base.StatusTracker;

/**
 * The main public class of the "application clustering support" library, the ClusterManager maintains a current view of
 * all the application instances (cluster members) participating in the same cluster as the local member is a part of.
 * <p/>
 * The ClusterManager is also responsible for providing the cluster-related functionality to the application, which
 * includes:
 * <ul>
 * <li>Internally, logic to maintain a replicated cache of {@link ClusterMember} objects representing the real-time
 * state of each member participating in the cluster</li>
 * <li>Methods that can be used by the application to determine the best member to service a request, based on member
 * availability and health state, and (if the cluster is session-aware) the number of active sessions being serviced by
 * each member</li>
 * <li>An optional means for the application to register additional "health aspects" indicating the local member's
 * current ability to service requests. If registered, these health aspects are taken into consideration by the other
 * methods (above) that determine the best member to service a request</li>
 * <ii>If configured for master/auxiliary awareness, methods to allow the local application member to determine whether
 * it is currently the "master" member in the cluster, or a participating "auxiliary", and an optional means for the
 * application to register and be notified of local member master/auxiliary role transitions </li>
 * </ul>
 * 
 * @author Dimit Chadha
 */
public class ClusterManager {

	/** A reference to the SLF4J object used to record logging activity. */
	private static final Logger LOG = LoggerFactory.getLogger(ClusterManager.class);

	/** A reference to the ClusterMember object representing the local application instance. */
	private final ClusterMember localMember;

	/** A reference to the local application instances Session repository - only set if the application is "session
	 * aware", so may be validly null. */
	private MemberSessionStore localSessionStore;

	/** The replicated Ehcache containing a real-time up-to-date view of every application instance (each represented by
	 * a {@link ClusterMember} object) currently participating in the cluster. This will be null if the local
	 * application is currently not configured to run in a clustered environment. */
	private Ehcache clusterCache;

	/** The list of additional health-aspect objects used to determine the overall health-state of the local application
	 * instance/member. Having health-aspects is optional - if none are registered, the state of the local member
	 * instance just always determined to be "UP". */
	private final List<MemberHealthAspect> localHealthAspects = new ArrayList<>();

	/** The name of the most-recently known "Master" in the cluster - gets updated every time
	* {@link #updateLocalMemberStatus} is called. */
	private String lastReportedMaster;

	/** The time-stamp of the last log-entry made, reporting who is the master. */
	private long lastLogReportTime;

	/** Last view of all the cluster member priorities - tracking this to report FATAL if more than one member
	 * configured with the same priority. */
	private int[] statusMemberPriorities;

	/** A list of optional objects registered to get notified of local member Master/Auxiliary role transitions. */
	private final List<RoleChangeListener> roleChangeListeners = new ArrayList<>();

	/** Tracks the number of master/auxiliary role transitions for status reporting/monitoring. */
	private final StatusTracker statusRoleChangeTracker = new StatusTracker();

	/** How often (in milliseconds) to log the status of the cluster. * */
	private static final long LOGREPORT_INTERVAL_MS = TimeUnit.MILLISECONDS.convert(10, TimeUnit.MINUTES);

	/** The default number of sessions the local member can have more than any other member before
	 * {@link #pickBestMemberForNewSession} stops seeing the local member as viable for a new session. */
	private static final long DEFAULT_LOCAL_LENIENCY_THRESHOLD = 10;
	/** The default percentage of sessions the local member can have more than any other member before
	 * {@link #pickBestMemberForNewSession} stops seeing the local member as viable for a new session. */
	private static final long DEFAULT_LOCAL_LENIENCY_PERCENTAGE = 20;
	private static final int MIN_PERCENTAGE = 1;
	private static final int MAX_PERCENTAGE = 100;
	/** The friendly name for this object used with the Health Status API for monitoring. */
	private static final String STATUS_API_COMPONENT_NAME = "Application Cluster";
	
	/** The number of sessions the local member can have more than any other member before
     * {@link #pickBestMemberForNewSession} stops seeing the local member as viable for a new session. */
    private long localSessionLeniencyThreshold = DEFAULT_LOCAL_LENIENCY_THRESHOLD;
    /** The percentage of sessions the local member can have more than any other member before
     * {@link #pickBestMemberForNewSession} stops seeing the local member as viable for a new session. */
    private long localSessionLeniencyPercentage = DEFAULT_LOCAL_LENIENCY_PERCENTAGE;

	/**
	* Instantiates a new ClusterManager object, as well as a {@link ClusterMember} object representing the local
	* application instance. Initializes the {@link #localMember} property with the member-name set to the local
	* hostname, and no URL prefix.
	*/
	public ClusterManager() {
		this(null);
	}

	/**
	 * Instantiates a new ClusterManager object, as well as a {@link ClusterMember} object representing the local
	 * application instance. Initializes the {@link #localMember} property with the name provided, the local hostname
	 * determined from InetAddress.getLocalHost(), and no URL prefix.
	 * @param localName the friendly name the local application member will be referred to within the cluster.
	 */
	public ClusterManager(String localName) {
		this(localName, null);
	}

	/**
	 * Instantiates a new ClusterManager object, as well as a {@link ClusterMember} object representing the local
	 * application instance. Initializes the {@link #localMember} property with the details provided, and no URL prefix.
	 * @param localName the friendly name the local application member will be referred to within the cluster.
	 * @param localHost the physical server host address (DNS name or ip-address) of the local application.
	 */
	public ClusterManager(String localName, String localHost) {
		this(localName, localHost, null);
	}

	/**
	 * Instantiates a new ClusterManager object, as well as a {@link ClusterMember} object representing the local
	 * application instance, and initializes the {@link #localMember} property with the details provided.
	 * @param localName the friendly name the local application member will be referred to within the cluster.
	 * @param localHost the physical server host address (DNS name or ip-address) of the local application.
	 * @param localUrlPrefix the prefix of the public URL used to send web-service requests to the local application.
	 */
	public ClusterManager(String localName, String localHost, String localUrlPrefix) {
		// if no local hostname provided, grab it from network library
		if (localHost == null) {
			try {
				localHost = InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException ex) {
				localHost = "Unknown";
			}
		}

		// if no localName provided, use the host-name
		if (localName == null) {
			localName = localHost;
		}

		// instantiate the local member object
		localMember = new ClusterMember(localName, localHost, localUrlPrefix);
		updateLocalMemberStatus();
		LOG.info("ClusterManager initialized - our local application instance member is: {}", localMember);
	}

	/**
	 * Called by the {@link LocalMemberStatusUpdater} to have the local ClusterMember object refreshed to represent the
	 * latest state of the local application instance, and have the new state replicated out to the other participants
	 * in the cluster.
	 */
	void updateLocalMemberStatus() {
		synchronized (localMember) {
			localMember.setActiveSessions(0);
			// refresh the current state of the local member
			localMember.setHealthState(assessLocalHealthState());
			if (localSessionStore != null) {
				localMember.setActiveSessions(localSessionStore.getLocalSessionCount());
			}
			updateMasterAuxiliaryState();
			// push new image to Cluster Status cache
			if (clusterCache != null) {
				clusterCache.put(new Element(localMember.getFriendlyName(), localMember));
			}
		}
	}

	/**
	 * Called internally by {@link #updateLocalMemberStatus} to determine health-state of the local application member.
	 * <p>
	 * If no external health aspects are configured (see {@link #setLocalMemberHealthAspect}) this method simply always
	 * returns "UP". Otherwise all the registered health aspects are consulted to determine the UP/DOWN state. If any
	 * aspect reports DOWN, then the overall state is considered DOWN.
	 */
	private MemberHealthState assessLocalHealthState() {
		// assume UP until we determine otherwise
		int overallHealthLevel = MemberHealthState.UP.getHealthLevel();

		// the registered health aspects can validly be empty, in which case the
		// assessed state is always UP (above)
		for (MemberHealthAspect healthAspect : this.localHealthAspects) {
			// assess each registered health-aspect - the overall state is the
			// lowest
			if (healthAspect.getHealthState() == null) {
				overallHealthLevel = MemberHealthState.SHUTDOWN.getHealthLevel();
			} else {
				if (healthAspect.getHealthState().getHealthLevel() < overallHealthLevel) {
					overallHealthLevel = healthAspect.getHealthState().getHealthLevel();
				}
			}
		}
		// return the outcome
		return MemberHealthState.fromHealthLevel(overallHealthLevel);
	}

	/**
	 * Called internally by {@link #updateLocalMemberStatus} to determine the current Master/Auxiliary role of the local
	 * application member.
	 * <p>
	 * The outcome of this method is that the {@link ClusterMember#master} indicator on the {@link #localMember} is
	 * updated to its true new state based on the current state of the rest of the cluster. If a role-transition occurs
	 * on the local application member as part of this method execution, then any {@link #roleChangeListeners} that are
	 * registered will also be notified as part of this method execution.
	 */
	private void updateMasterAuxiliaryState() {
		synchronized (localMember) {
			if (!localMember.isMasteringAware()) {
				LOG.trace("updateMasterAuxiliaryState() - not master/auxiliary aware - will never be master");
				localMember.setMaster(false);
				lastReportedMaster = null;
				return;
			}
			boolean shouldLocalBeMaster = false;
			ClusterMember foreignMaster = null;
			// if we're not in a cluster...
			if (clusterCache == null) {
				LOG.trace("updateMasterAuxiliaryState() - no cluster cache, stand-alone assumed, local will be master");
				shouldLocalBeMaster = true;
			} else {
				// we are master/auxiliary aware, and in a cluster - figure it
				// out...
				boolean localInCluster = false;
				ClusterMember highestForeignMember = null;
				statusMemberPriorities = new int[10];
				// get a list of all members in the cluster
				List<String> memberKeys = clusterCache.getKeys();
				if (memberKeys != null) {
					// look at each member...
					for (String key : memberKeys) {
						Element memberEle = clusterCache.getQuiet(key);
						if (memberEle != null) {
							ClusterMember member = (ClusterMember) memberEle.getObjectValue();
							statusMemberPriorities[member.getMasteringPriority()]++;
							// is this entry the local member?
							if (member.getFriendlyName().equals(localMember.getFriendlyName())) {
								// yes - track we found local already in the
								// cluster,
								localInCluster = true;
								// if we were already in the cluster, and
								// already announced as "master", lets assume we
								// remain master - NOTE: this may get overriden
								// below, if more than 1 master found
								if (member.isMaster()) {
									shouldLocalBeMaster = true;
								}
							} else {
								// track who the current foreign master is
								if (member.isMaster()) {
									foreignMaster = member;
								}

								// track the highest ranking foreign member
								if (member.isMasteringAware()) {
									// member is master/auxiliary aware...
									// Is this member higher than any other
									// foreign member seen so far?
									if (highestForeignMember == null
											|| member.getMasteringPriority() < highestForeignMember.getMasteringPriority()) {
										// yes - this is the new highest ranking
										// foreign member
										highestForeignMember = member;
									}
								}
							}
						}
					}
				}
				LOG.trace(
						"updateMasterAuxiliaryState() - cluster cache present, members: {}, local {} in cache, foreign-master: {}, highestForeigner: {}",
						memberKeys == null ? "0(null)" : memberKeys.size(), localInCluster ? "is" : "not", foreignMaster == null ? "none"
								: foreignMaster.getFriendlyName(),
						highestForeignMember == null ? "none" : highestForeignMember.getFriendlyName());
				// if local found in cluster, its not our first time, so
				// consider us
				// (Note: first time into cluster, we don't do this - we join as
				// auxiliary and wait to see who's there)
				if (localInCluster) {
					// if no foreign masters found anywhere,
					if (foreignMaster == null) {
						// if we arent already the master, see if we should take
						// over...
						if (!shouldLocalBeMaster) {
							// Are we the highest priority for mastership?
							if (highestForeignMember == null
									|| localMember.getMasteringPriority() < highestForeignMember.getMasteringPriority()) {
								// yes - so we should be master
								shouldLocalBeMaster = true;
							} else {
								LOG.info("ClusterManager - found NO masters in the cluster, but we arent the highest priority - waiting for someone else to take mastership");
							}
						}
					}
					// otherwise at least one foreign master found..
					else {
						// if we are currently the master too...
						if (shouldLocalBeMaster) {
							// yield if the foreign master has a higher priority
							// than us...
							if (foreignMaster.getMasteringPriority() < localMember.getMasteringPriority()) {
								LOG.info(
										"ClusterManager - member {} already MASTER, and they have higher priority than us. About to yield masterhip...",
										foreignMaster.getFriendlyName());
								shouldLocalBeMaster = false;
							} else {
								LOG.info(
										"ClusterManager - we have mastership, but member {} also found to be MASTER. Will NOT be yielding mastership as we have the higher priority!",
										foreignMaster.getFriendlyName());
							}
						}
					}
				}
			}
			// based on what we determined, perform a role transition if
			// required...
			performRoleChangeIfReqd(foreignMaster, shouldLocalBeMaster);
		}
	}

	/**
	* Called only internally from {@link #updateMasterAuxiliaryState}, this method performs the actual master/auxiliary
	* role-change if it is required.
	* <p/>
	* Inputs:
	* @param foreignMaster the foreign member instance in the cluster determined to be master - null indicates either
	* no foreign members present, or foreign members were found but none were currently the master.
	* @param shouldLocalBeMaster the flag indicating where the local member should be the master or not.
	*/
	private void performRoleChangeIfReqd(ClusterMember foreignMaster, boolean shouldLocalBeMaster) {
		// determine if we are about to transition roles
		boolean becomingMaster = false;
		boolean becomingAuxiliary = false;
		if (shouldLocalBeMaster && !localMember.isMaster()) {
			becomingMaster = true;
			statusRoleChangeTracker.mark();
			LOG.info("*** Local member ({}) becoming MASTER", localMember.getFriendlyName());
		}
		if (localMember.isMaster() && !shouldLocalBeMaster) {
			becomingAuxiliary = true;
			statusRoleChangeTracker.mark();
			LOG.info("*** Local member ({}) relinquishing master role - now becoming AUXILIARY", localMember.getFriendlyName());
		}

		// Update local member to the determined master/auxiliary role
		localMember.setMaster(shouldLocalBeMaster);

		// log and keep track of the current master
		if (shouldLocalBeMaster) {
			lastReportedMaster = localMember.getFriendlyName();
		} else if (foreignMaster == null) {
			lastReportedMaster = null;
		} else {
			lastReportedMaster = foreignMaster.getFriendlyName();
		}
		long now = System.currentTimeMillis();
		if (lastLogReportTime + LOGREPORT_INTERVAL_MS < now) {
			lastLogReportTime = now;
			LOG.info("ClusterManager - Current Master is: {}", lastReportedMaster == null ? "NO MASTER!" : lastReportedMaster);
		}

		// notify any listeners of a role change
		if (!roleChangeListeners.isEmpty() && (becomingMaster || becomingAuxiliary)) {
			for (RoleChangeListener listener : roleChangeListeners) {
				if (becomingMaster) {
					listener.notifyLocalBecomingMaster();
				}
				if (becomingAuxiliary) {
					// This should rarely happen, if ever. To get here there
					// would have been some extreme ehcache
					// race-condition, or the local member was MASTER but took a
					// big pause bit didnt go down (paused long
					// enough to temporarily drop out of the cluster at which
					// point someone else took over).
					listener.notifyLocalBecomingAuxiliary();
				}
			}
		}

	}

	/**
	 * Sets the replicated Ehcache containing a real-time up-to-date view of every application instance (each
	 * represented by a {@link ClusterMember} object) currently participating in the cluster. This is only expected to
	 * be called / used if the current member configured to run in a clustered environment.
	 */
	public void setClusterCache(Ehcache value) {
		this.clusterCache = value;
		if (value != null) {
			// default local member state is "master" (if master/auxiliary
			// aware) - but now we are in a cluster
			// so set default local state to "auxiliary" to begin
			this.localMember.setMaster(false);
		}
		updateLocalMemberStatus();
	}

	/**
	 * Returns the friendly name the local application instance (member) is referred to within the cluster.
	 */
	public String getLocalMemberName() {
		return this.localMember.getFriendlyName();
	}

	/**
	 * Returns true if the specified cluster member name matches the name of the local member, otherwise returns false.
	 */
	public boolean isLocal(String memberName) {
		return this.localMember.getFriendlyName().equals(memberName);
	}

	/**
	 * Determines whether the application member with the specified name is available in the cluster (i.e. regularly
	 * reporting in, and has (if configured) a good health state).
	 * @param targetMember the name of the application member to verify.
	 * @return true if the named member is regularly reporting in, and is healthy, otherwise returns false.
	 */
	public boolean isMemberAvailable(String targetMember) {
		// if target is local member, dont need to go to cluster cache...
		if (isLocal(targetMember)) {
			synchronized (localMember) {
				return this.localMember.isHealthy();
			}
		}

		// if we're configured to run clustered..
		if (clusterCache != null) {
			// get all our cluster members
			List<String> memberKeys = clusterCache.getKeys();
			if (memberKeys != null) {
				for (String key : memberKeys) {
					Element memberEle = clusterCache.getQuiet(key);
					if (memberEle != null) {
						ClusterMember member = (ClusterMember) memberEle.getObjectValue();

						// if this the one we are looking for, return based on
						// its reported phone-link state
						if (member.getFriendlyName().equals(targetMember)) {
							return member.isHealthy();
						}
					}
				}
			}
		}

		// couldnt find target member, so return 'unavailable'
		return false;
	}

	/**
	 * Returns the URL prefix to use to send web-service requests to the specified member instance in the cluster.
	 * @param targetMember the friendly name of the cluster member to obtain the URL prefix for.
	 */
	public String getMemberUrlPrefix(String targetMember) {
		// if target is local member, dont need to go to cluster cache...
		if (isLocal(targetMember)) {
			return this.localMember.getUrlPrefix();
		}

		// if we're configured to run clustered..
		if (clusterCache != null) {
			// get all our cluster members
			List<String> memberKeys = clusterCache.getKeys();
			if (memberKeys != null) {
				for (String key : memberKeys) {
					Element memberEle = clusterCache.getQuiet(key);
					if (memberEle != null) {
						ClusterMember member = (ClusterMember) memberEle.getObjectValue();

						// if this the one we are looking for, return based on
						// its reported phone-link state
						if (member.getFriendlyName().equals(targetMember)) {
							return member.getUrlPrefix();
						}
					}
				}
			}
		}

		// couldnt find target member, so return null
		return null;
	}

	/**
	 * The local application can call this method to determine the best application instance member in the cluster to
	 * handle general incoming requests (if the application is "session-aware", and the request is a "new session"
	 * request, refer to the other alternate {@link #pickBestMemberForNewSession} method rather than this method.
	 * <p/>
	 * In general, if the application is in a mastering-aware cluster, the name of the current master is returned.
	 * Otherwise this method always determines the local member as the "best", unless the "health state" of the local
	 * member is assessed as "DOWN", and there is an alternative member in the cluster that has a healthy state.
	 * <p/>
	 * @return the friendly name of the best member to service an incoming request, or null if no viable member was
	 * found.
	 */
	public String pickBestMemberForRequest() {
		ClusterMember localMemberCopy = getLocalMember();
		// if there is a master, use the master
		if (localMemberCopy.isMasteringAware()) {
			String masterName = getCurrentMasterName();
			if (masterName == null) {
				LOG.warn("pickBestMemberForRequest() returned null (mastering-aware, but there's no current master!)");
			} else {
				LOG.debug("pickBestMemberForRequest() chose master instance ({})", masterName);
			}
			return masterName;
		}
		// if not master-aware, and the local instance is healthy, use local
		else if (localMemberCopy.isHealthy()) {
			LOG.debug("pickBestMemberForRequest() chose local instance");
			return localMemberCopy.getFriendlyName();
		}
		// if there is no cluster and the local instance is *not* healthy,
		else if (clusterCache == null) {
			LOG.warn("pickBestMemberForRequest() returning null (local member is not healthy, and not in a cluster)");
			return null;
		}

		// Go looking for the least busy remote member
		ClusterMember chosenMember = null;
		List<String> memberKeys = clusterCache.getKeys();
		if (memberKeys != null) {
			// look at each member...
			for (String key : memberKeys) {
				Element memberEle = clusterCache.getQuiet(key);
				if (memberEle != null) {
					ClusterMember member = (ClusterMember) memberEle.getObjectValue();
					// if member's state is "healthy", and its less busy than
					// current 'chosen'...
					if (member.isHealthy()) {
						if (chosenMember == null || chosenMember.getActiveSessions() > member.getActiveSessions()) {
							// make this member the new chosen one
							chosenMember = member;
						}
					}
				}
			}
		}

		if (chosenMember == null) {
			LOG.warn("pickBestMemberForRequest() couldn't find any active cluster member to recommend");
			return null;
		} else {
			LOG.debug("pickBestMemberForRequest chose {} member", chosenMember.getFriendlyName());
			return chosenMember.getFriendlyName();
		}
	}

	// session aware
	/**
	 * Optional setter - used to inject a reference to the local application instances Session repository if the
	 * application is "session aware".
	 */
	public void setLocalMemberSessionStore(MemberSessionStore value) {
		this.localSessionStore = value;
		this.localMember.setSessionAware(value != null);
	}

	/**
	 * Sets the percentage of sessions the local instance can have more than any other instance before
	 * {@link #pickBestMemberForNewSession} stops seeing the local instance as viable for a new session.
	 */
	public void setLocalSessionLeniencyPercentage(int value) {
		if (value < MIN_PERCENTAGE || MAX_PERCENTAGE < value) {
			LOG.warn("Change of Cluster Local Session-count Leniency percentage ignored - value ({}) must be between {} and {}", value,
					MIN_PERCENTAGE, MAX_PERCENTAGE);
		} else {
			this.localSessionLeniencyPercentage = value;
			LOG.info("Cluster Local Session-count Leniency percentage now set to : {}%", this.localSessionLeniencyPercentage);
		}
	}

	/**
	 * Sets the number of sessions the local instance can have more than any other instance before
	 * {@link #pickBestMemberForNewSession} stops seeing the local instance as viable for a new session.
	 */
	public void setLocalSessionLeniencyThreshold(int value) {
		if (value < MIN_PERCENTAGE || MAX_PERCENTAGE < value) {
			LOG.warn("Change of Cluster Local Session-count Leniency threshold ignored - value ({}) must be between {} and {}", value,
					MIN_PERCENTAGE, MAX_PERCENTAGE);
		} else {
			this.localSessionLeniencyThreshold = value;
			LOG.info("Cluster Local Session-count Leniency threshold now set to : {}", this.localSessionLeniencyThreshold);
		}
	}

	/**
	 * Sets a single external health-aspect object used to determine the overall health-state of the local application
	 * instance/member. Having health-aspects is optional - if none are registered, the state of the local member
	 * instance is just always determined to be "UP".
	 * @param healthAspect
	 */
	public void setLocalMemberHealthAspect(MemberHealthAspect healthAspect) {
		this.localHealthAspects.clear();
		if (healthAspect != null) {
			this.localHealthAspects.add(healthAspect);
			LOG.info("Cluster local-member Health Aspect registered: {} - aspect state currently reporting {}", healthAspect.getClass()
					.getSimpleName(), healthAspect.getHealthState());
		}
		updateLocalMemberStatus();
	}

	/**
	 * Sets a list of external health-aspects used to determine the overall health-state of the local application
	 * instance/member. Having health-aspects is optional - if none are registered, the state of the local member
	 * instance is just always determined to be "UP".
	 */
	public void setLocalMemberHealthAspects(List<MemberHealthAspect> healthAspects) {
		this.localHealthAspects.clear();
		if (healthAspects != null && !healthAspects.isEmpty()) {
			this.localHealthAspects.addAll(healthAspects);
			for (MemberHealthAspect aspect : healthAspects) {
				LOG.info("Cluster local-member Health Aspect registered: {} - aspect state currently reporting {}", aspect.getClass()
						.getSimpleName(), aspect.getHealthState());
			}
		}
		updateLocalMemberStatus();
	}

	/**
	 * When the local application receives a request to initiate a new session (if the application is session-aware),
	 * this method can be called to determine the best member in the cluster to own the new session.
	 * <p/>
	 * The "best" is determined to be the least-busy member (in terms of number of current sessions being managed) that
	 * is also in a "healthy" state. The least-busy determination also takes into account the local leniency settings
	 * (see {@link #localSessionLeniencyPercentage} and {@link #localSessionLeniencyThreshold}).
	 * <p/>
	 * @return the friendly name of the best application instance member in the cluster to manage a new session, or null
	 * if no viable cluster member was found.
	 */
	public String pickBestMemberForNewSession() {
		ClusterMember chosenMember = null;
		int memberCnt = 0;

		// get a copy of our local member for referencing
		ClusterMember localMemberCopy = getLocalMember();

		// find the least busy, healthy member (including local)
		if (clusterCache == null) {
			// no cache, so assume local as long as its healthy
			LOG.debug("pickBestMemberForNewSession considered local instance only (no cluster cache configured)");
			if (localMemberCopy.isHealthy()) {
				chosenMember = localMemberCopy;
			}
		} else {
			// get all our cluster members (this includes the local member)
			List<String> memberKeys = clusterCache.getKeys();
			if (memberKeys != null) {
				// look at each member...
				memberCnt = memberKeys.size();
				for (String key : memberKeys) {
					Element memberEle = clusterCache.getQuiet(key);
					if (memberEle != null) {
						ClusterMember member = (ClusterMember) memberEle.getObjectValue();
						// if member's assessed state is "healthy", and its less
						// busy than current 'chosen'...
						if (member.isHealthy()) {
							if (chosenMember == null || chosenMember.getActiveSessions() > member.getActiveSessions()) {
								// make this member the new chosen one
								chosenMember = member;
							}
						}
					}
				}
			}
			LOG.debug("pickBestMemberForNewSession considered {} cluster members in the cluster (including us)", memberCnt);
		}

		// if no possible member found (meaning nobody is healthy), we're done
		if (chosenMember == null) {
			LOG.warn("pickBestMemberForNewSession() couldn't find any active/healthy cluster member to recommend");
			return null;
		}

		// we found a best - if best is local, no need to test leniency, just
		// return local
		if (isLocal(chosenMember.getFriendlyName())) {
			LOG.debug("pickBestMemberForNewSession chose local instance");
			return localMember.getFriendlyName();
		}

		// the best found was foreign, if local is a possibility (i.e. healthy)
		// check the local leniency
		if (localMemberCopy.isHealthy()) {
			long leniencyByPercentage = chosenMember.getActiveSessions()
					+ ((chosenMember.getActiveSessions() * localSessionLeniencyPercentage) / 100L);

			// if local is within the percentage, use it
			if (localMemberCopy.getActiveSessions() <= leniencyByPercentage) {
				LOG.debug("pickBestMemberForNewSession chose local member (was greater than least-busy, but within leniency percentage)");
				return localMember.getFriendlyName();
			}

			long leniencyByThreshold = chosenMember.getActiveSessions() + localSessionLeniencyThreshold;
			// Local not within percentage, but if within threshold, use it
			if (localMemberCopy.getActiveSessions() <= leniencyByThreshold) {
				LOG.debug("pickBestMemberForNewSession chose local member (was greater than least-busy, and leniency percentage, but still within threshold)");
				return localMember.getFriendlyName();
			}
		}

		// if we're here, we picked a member, its foreign, and local is either
		// not healthy or already overloaded
		LOG.debug("pickBestMemberForNewSession chose {} member", chosenMember.getFriendlyName());
		return chosenMember.getFriendlyName();
	}

	// Public API methods used for 'Master/Auxiliary Aware' applications

	/**
	 * Sets the priority the local application member has to take over the master role in the scenario where there is no
	 * active master in the cluster, if the application is "master/auxiliary aware". A value of zero indicates the
	 * application is not master/auxiliary aware and doesn't care about the master/auxiliary concept.
	 */
	public void setLocalMasteringPriority(int value) {
		this.localMember.setMasteringPriority(value);
		if (value > 0) {
			// we just became master/auxiliary aware
			if (this.clusterCache == null) {
				// stand-alone mode, so default local to master always
				localMember.setMaster(true);
			}
		} else {
			// no longer master/auxiliary aware
			localMember.setMaster(false);
		}
		updateLocalMemberStatus();
	}

	/**
	 * Registers a single external application object to get notified of local member Master/Auxiliary role transitions
	 * as they occur. Even if the local application is master/auxiliary aware, registering RoleChangeListeners is
	 * optional and not required.
	 */
	public void setRoleChangeListener(RoleChangeListener listener) {
		setRoleChangeListeners(Arrays.asList(listener));
	}

	/**
	 * Registers a list of external application objects to get notified of local member Master/Auxiliary role
	 * transitions as they occur. Even if the local application is master/auxiliary aware, registering
	 * RoleChangeListeners is optional and not required.
	 */
	public void setRoleChangeListeners(List<RoleChangeListener> listeners) {
		synchronized (localMember) {
			this.roleChangeListeners.clear();
			this.roleChangeListeners.addAll(listeners);

			// send the first notification to reflect current state
			for (RoleChangeListener listener : roleChangeListeners) {
				if (localMember.isMaster()) {
					listener.notifyLocalBecomingMaster();
				} else {
					listener.notifyLocalBecomingAuxiliary();
				}
			}

			// and refresh our local state (may cause another notification is
			// role changes
			updateLocalMemberStatus();
		}
	}

	/**
	 * Returns true if the local application member is currently considered th "Master" in the cluster. Returns false if
	 * its currently considered a "Auxiliary" or if the cluster is not configured as master/auxiliary aware.
	 */
	public boolean isLocalMaster() {
		synchronized (localMember) {
			return localMember.isMaster();
		}
	}

	/**
	 * Returns the name of the cluster member currently understood to be the master - will return null if there is no
	 * current master.
	 */
	public String getCurrentMasterName() {
		return lastReportedMaster;
	}

	/**
	 * Returns the reference (if configured) to the local application instances Session repository - will return null if
	 * the application is not "session aware".
	 */
	MemberSessionStore getLocalMemberSessionStore() {
		return this.localSessionStore;
	}

	/**
	 * Returns the Time-To-Live setting on the Ehcache object (see {@link #clusterCache}) containing a real-time
	 * up-to-date view of every member in the cluster.
	 * @return the Time-To-Live setting on {@link #clusterCache}, or -1 if clusterCache is null (i.e. if this local
	 * member is not running in a clustered environment).
	 */
	long getClusterCacheTimeToLiveSecs() {
		if (this.clusterCache != null) {
			return clusterCache.getCacheConfiguration().getTimeToLiveSeconds();
		}
		return -1;
	}

	/**
	 * Returns (a cloned copy of) a {@link ClusterMember} object representing the local application instance.
	 */
	ClusterMember getLocalMember() {
		synchronized (this.localMember) {
			return localMember.clone();
		}
	}
}
