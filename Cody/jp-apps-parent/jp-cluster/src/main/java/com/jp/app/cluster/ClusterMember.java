/*
 * File: ClusterMember.java
 * Date: 12-Aug-2015
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to
 * learn
 * & gain ideas from it. Its unauthorised use is explicitly prohibited
 * & any
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code
 * Area.
 * Its unauthorised use gives Java Pathshala the right to obtain
 * retention
 * orders
 * and to prosecute the authors of any infraction.
 * Visit us at www.javapathshala.com
 */
package com.jp.app.cluster;

import java.io.Serializable;

/**
 * This ClusterMember class, along with {@link ClusterManager}, are used to implement the high-availability
 * cluster concept.
 * <p/>
 * A ClusterMember object represents the current state of an application instance in the cluster, and tracks
 * (if configured) the "health" of the instance/member, the (if the application is session-aware) number
 * of sessions being serviced by the instance/member, and its current role in the cluster (Master or Auxiliary).
 * <p/>
 * (This class is intentionally declared with no class modifier as its should only need to be referenced locally
 * within the application-clustering package, and not directly exposed to the external application).
 * 
 * @author Dimit Chadha
 */
class ClusterMember implements Cloneable, Serializable {

	/** Class version ID for Serialization support. Increment this if you change the makeup of this class/object. */
	private static final long serialVersionUID = 6102506779887136925L;

	/** The friendly name of the application member used in configuration and when reporting its status to the cluster. */
	private final String friendlyName;

	/** The physical server host address (DNS name or ip-address) of where the application member/instance is running. */
	private final String host;

	/** The prefix of the public URL (including protocol, hostname, port, and web-app context) used to send a SOAP web-service request to this application member/instance (if applicable - may be null). */
	private final String urlPrefix;

	/** Flag indicating if the application instance represented by this member object has been configured as "session aware" - see {@link ClusterManager#setLocalMemberSessionStore()}. */
	private boolean sessionAware;

	/** The number of active sessions this member is currently managing (only relevant when {@link #sessionAware} is true). */
	private long activeSessions;

	/** If greater than 0 (zero), indicates that the application is "master/auxiliary aware", and then indicates in the scenario where there is no active master in the cluster, what priority this member has to take over the master role. */
	private int masteringPriority;

	/** Flag indicating whether this member is currently performing the "Master" role (true), or whether its a
	 * Auxiliary (false). Can only be true when {@link #masteringPriority} is greater than zero (indicating the
	 * application is master/auxiliary aware). */
	private boolean master;

	/** The reported current health state of the application member/instance in terms of its ability
	 * to service requests. */
	private MemberHealthState healthState;

	/**
	 * Instantiates a new ClusterMember instance, and populates it with the specified details.
	 * @param name friendly name this application member is referred to as within the cluster.
	 * @param hostName the physical server host address (DNS name or ip-address) of where the member/instance is
	 * running.
	 * @param urlPrefixPart the prefix of the public URL (including protocol, hostname, port, and web-app context)
	 * used to send a SOAP web-service request to this member (if applicable - may be null).
	 */
	public ClusterMember(String name, String hostName, String urlPrefixPart) {
		this.friendlyName = name;
		this.host = hostName;
		this.urlPrefix = urlPrefixPart;
	}

	/**
	 * Returns the friendly name of the application member used in configuration and when reporting its status to the
	 * cluster.
	 */
	public String getFriendlyName() {
		return friendlyName;
	}

	/**
	 * Returns the physical server host address (DNS name or ip-address) of where the member is running.
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Returns the prefix of the public URL (including protocol, hostname, port, and web-app context) used to
	 * send a SOAP web-service request to this member (if applicable - may return null).
	 */
	public String getUrlPrefix() {
		return urlPrefix;
	}

	/**
	 * Returns true if the application instance represented by this member object has been configured as
	 * "session aware", otherwise returns false.
	 * @see #sessionAware
	 */
	public boolean isSessionAware() {
		return this.sessionAware;
	}

	/**
	 * Updates the flag indicating if the application instance represented by this member object has been configured as
	 * "session aware".
	 * @see #sessionAware
	 */
	public void setSessionAware(boolean value) {
		this.sessionAware = value;
	}

	/**
	 * Returns the number of active sessions this member is currently managing (only relevant when
	 * {@link #sessionAware} is true).
	 */
	public long getActiveSessions() {
		return activeSessions;
	}

	/**
	 * Updates the number of active sessions this member is currently managing to the specified value. Will only
	 * be called if the application is session-aware - see {@link #sessionAware}.
	 */
	public void setActiveSessions(long value) {
		this.activeSessions = value;
	}

	/**
	 * Returns the current health state of the application instance referred to by this member object, in terms of its
	 * ability to service requests.
	 */
	public MemberHealthState getHealthState() {
		return this.healthState;
	}

	/**
	 * Sets the current health state of the application instance referred to by this member object, in terms of its
	 * ability to service requests.
	 */
	public void setHealthState(MemberHealthState value) {
		this.healthState = value;
	}

	/**
	 * Returns the priority this member has to take over the master role in the scenario where there is no active
	 * master in the cluster, if the application is "master/auxiliary aware". A value of zero indicates the application
	 * is not master/auxiliary aware.
	 */
	public int getMasteringPriority() {
		return masteringPriority;
	}

	/**
	 * Sets the priority this member has to take over the master role in the scenario where there is no active
	 * master in the cluster, if the application is "master/auxiliary aware". A value of zero indicates the application
	 * is not master/auxiliary aware.
	 */
	public void setMasteringPriority(int value) {
		this.masteringPriority = value;
	}

	/**
	 * Convenience method - returns true if {@link #masteringPriority} is > 0, otherwise returns false.
	 */
	public boolean isMasteringAware() {
		return this.masteringPriority > 0;
	}

	/**
	 * Returns the flag indicating whether this member is currently performing the "Master" role (true), or whether its
	 * a Auxiliary (false). Can only be true when {@link #masteringPriority} is greater than zero (indicating the
	 * application is master/auxiliary aware).
	 */
	public boolean isMaster() {
		return master;
	}

	/**
	 * Updates the flag indicating whether this member is currently performing the "Master" role (true), or whether its
	 * a Auxiliary (false). Can only be true when {@link #masteringPriority} is greater than zero (indicating the
	 * application is master/auxiliary aware).
	 */
	public void setMaster(boolean value) {
		this.master = value;
	}

	/**
	 * Returns true if the health state of the member is reportedly "healthy" (UP). Otherwise returns false.
	 */
	public boolean isHealthy() {
		return this.healthState == MemberHealthState.UP;
	}

	/**
	 * Creates and returns a deep copy of this ClusterMember object.
	 * <p/>
	 * {@inheritDoc}
	 */
	@Override
	public ClusterMember clone() {
		try {
			return (ClusterMember) super.clone();
		} catch (CloneNotSupportedException ex) {
			throw new AssertionError(ex);
		}
	}

	/**
	 * Returns a debug string describing the contents of the ClusterMember object.
	 */
	@Override
	public String toString() {
		StringBuilder msg = new StringBuilder("ClusterMember[");
		msg.append(friendlyName);
		msg.append(" - host=").append(host);
		msg.append(", health-state=").append(healthState);
		if (sessionAware) {
			msg.append(", sessions=").append(activeSessions);
		}
		if (isMasteringAware()) {
			msg.append(", role=").append(isMaster() ? "MASTER" : "AUXILIARY");
		}

		msg.append(']');
		return msg.toString();
	}

}
