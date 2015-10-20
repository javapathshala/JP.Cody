/*
 * File: MemberHealthState.java
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
 * Indicates whether a specific aspect of external application functionality can currently successfully
 * process transactions.
 * 
 * @author Dimit Chadha
 */
public enum MemberHealthState {
	/**
	 * Indicates the external application aspect can no longer process transactions, because it has been shut down
	 * (ie. it will not process any transactions in the future either).
	 */
	SHUTDOWN(0),
	/**
	 * Indicates the external application aspect is currently UNABLE TO process transactions
	 * (i.e. that aspect of the application is currently unhealthy).
	 */
	DOWN(1),
	/**
	 * Indicates the external application aspect, can't currently process transactions but is in the process of
	 * initializing.
	 */
	CONNECTING(2),
	/**
	 * Indicates the external application aspect CAN currently process transactions
	 * (i.e. that aspect of the application is healthy).
	 */
	UP(3);
	/**
	 * Private copy of all values.
	 */
	private static final MemberHealthState[] VALUES = MemberHealthState.values();
	/**
	 * The level of health the enum value represents (the higher the number the healthier)
	 */
	private int healthLevel;

	/**
	 * Creates an MemberHealthState value with healthLevel set to that specified.
	 * @param level
	 */
	MemberHealthState(int level) {
		this.healthLevel = level;
	}

	/**
	 * Returns the health-level the enum value represents.
	 */
	public int getHealthLevel() {
		return this.healthLevel;
	}

	/**
	 * Returns the enum value associated with the specified health level.
	 */
	public static MemberHealthState fromHealthLevel(int level) {
		for (MemberHealthState state : VALUES) {
			if (state.getHealthLevel() == level) {
				return state;
			}
		}
		return null;
	}
}
