/*
 * File: SimpleJoin.java
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
package com.jp.core.threads;

/**
 * @author dimit.chadha
 */
public class SimpleJoin {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {

		System.out.println(Thread.currentThread().getName() + " is Started");

		Thread exampleThread = new Thread() {

			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + " is Started");
					Thread.sleep(2000000);
					System.out.println(Thread.currentThread().getName() + " is Completed");
				} catch (InterruptedException ex) {
					System.out.println(ex);
				}
			}
		};
		Thread exampleThread2 = new Thread() {

			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + " is Started");
					System.out.println(Thread.currentThread().getName() + " is working now because of join");
					Thread.sleep(2000);
					System.out.println(Thread.currentThread().getName() + " is Completed");
				} catch (InterruptedException ex) {
					System.out.println(ex);
				}
			}
		};

//		exampleThread.start();
//		exampleThread.join();
		exampleThread.start();
		exampleThread2.start();
		exampleThread2.join();

		//exampleThread.join();

		System.out.println(Thread.currentThread().getName() + " is Completed");
	}

}
