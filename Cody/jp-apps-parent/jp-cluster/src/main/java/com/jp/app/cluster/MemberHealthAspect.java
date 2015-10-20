/*
 * File: MemberHealthAspect.java
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
 * An application can optionally implement this interface and register external objects with the
 * ClusterManager during initialization (see one of the {@link ClusterManager#setLocalMemberHealthAspect} methods)
 * that will be taken into consideration when determining the health state (UP or DOWN) of the local application member.
 * <p/>
 * Having and registering health-aspects is optional - if none are registered, the state of the local member
 * instance is just always determined to be "UP".
 */
public interface MemberHealthAspect {
	/**
	 * Called be the {@link ClusterManager#updateLocalMemberStatus} method periodically when determining the overall
	 * health-state of the local application member - if this aspect of the application has failed to a point that
	 * it can no longer process application requests, {@link MemberHealthState#DOWN} should be returned, otherwise
	 * return {@link MemberHealthState#UP}.
	 */
	MemberHealthState getHealthState();
}
