/*
 * File: BinarySemaphore.java
 * Date: 11-Jul-2013
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
package com.jp.core.threads.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author dimit.chadha
 */
public class BinarySemaphore {

	Semaphore binary = new Semaphore(1);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final BinarySemaphore test = new BinarySemaphore();
		new Thread() {

			@Override
			public void run() {
				test.mutualExclusion();
			}
		}.start();

		new Thread() {

			@Override
			public void run() {
				test.mutualExclusion();
			}
		}.start();
	}

	private void mutualExclusion() {
		try {
			binary.acquire();
			// mutual exclusive region
			System.out.println(Thread.currentThread().getName() + " inside mutual exclusive region");
			Thread.sleep(1000);

		} catch (InterruptedException ie) {
			ie.printStackTrace();
		} finally {
			binary.release();
		System.out.println(Thread.currentThread().getName() + " outside of mutual exclusive region");
		}
	}
}
