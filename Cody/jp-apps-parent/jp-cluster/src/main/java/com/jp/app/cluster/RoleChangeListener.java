/*
 * File: RoleChangeListener.java
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

/**
 * A master/auxiliary aware application can optionally implement this interface and register with the ClusterManager
 * to be notified whenever the role of the local member instance transitions from master-to-auxiliary or from
 * auxiliary-to-master.
 *
 * If implemented, the application registers any RoleChangeListener objects with the ClusterManager via one of the
 * {@link ClusterManager#setRoleChangeListener} methods during initialization.
 * <p/>
 * RoleChangeListener's are option - an application does not *have* to implement these to make use of the master/auxiliary
 * concept of the clustering library. The application can simply have a reference to the singleton ClusterManager
 * object, and call its {@link ClusterManager#isLocalMaster} as often as needed, to determine in real-time whether
 * the local application instance is currently considered the "master" instance in the cluster.
 */
public interface RoleChangeListener {
	/**
	 * Called by the clustering library whenever the local application member transitions from being a auxiliary
	 * into the master role.
	 */
	void notifyLocalBecomingMaster();

	/**
	 * Called by the clustering library whenever the local application member gets transitioned from being the master
	 * back into the auxiliary role.
	 * <p/>
	 * This type of role change (master-to-auxiliary) should rarely happen, if ever. To have this condition, there would
	 * have had to be some extreme ehcache race-condition, or the local member was MASTER but took a big pause bit
	 * didn't go down (paused long enough to temporarily drop out of the cluster at which point someone else took over).
	 */
	void notifyLocalBecomingAuxiliary();
}