/*
 * File: SimulateWork.java
 * Date: 10-Jul-2013
 *
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn 
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any 
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention orders
 * and to prosecute the authors of any infraction.
 * 
 * Visit us at www.javapathshala.com
 */
package com.jp.core.threads.pool.custom;

/**
 * @author dimit.chadha
 */
public class SimulateWork implements Runnable {

	static private int count = 0;

	private int taskNumber;

	protected Done done;

	/**
	 * @param done
	 */
	SimulateWork() {
		count++;
		taskNumber = count;
	}

	public void run() {
		for (int i = 0; i < 100; i += 2) {
			System.out.println("Task number: " + taskNumber + ",percent complete = " + i);
			try {
				Thread.sleep((int) (Math.random() * 500));
			} catch (InterruptedException e) {
			}
		}
	}
}
