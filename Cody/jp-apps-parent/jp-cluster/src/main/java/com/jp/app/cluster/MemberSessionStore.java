/*
 * File: MemberSessionStore.java
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
 * If the application using this clustering library supports the concept of "sessions" across the cluster,
 * the application should implement this interface as part of its session repository so the {@link ClusterManager}
 * can determine the number of sessions current being managed by the local application instance.
 * <p/>
 * In a session-aware cluster, the ClusterManager takes current session counts into account when selecting the best
 * cluster member for new transactions (see the {@link ClusterManager#pickBestMemberForNewSession} and
 * {@link ClusterManager#pickBestMemberForRequest} methods).
 * <p/>
 * Once implemented, the application registers its session repository with the cluster manager
 * via {@link ClusterManager#setLocalMemberSessionStore}.
 */
public interface MemberSessionStore {
	/**
	 * Return the number of currently active sessions being managed by the local member application instance.
	 */
	int getLocalSessionCount();

	/**
	 * Called by the clustering support library to notify the local application that, from a clustering perspective,
	 * the local instance is no longer participating in the cluster (i.e. it did not report into the cluster in
	 * a timely enough manner, so all other cluster members have already considered this local instance as "DOWN"
	 * - whether it currently thinks its running or not).
	 */
	void destroyAllLocalSessions();
}