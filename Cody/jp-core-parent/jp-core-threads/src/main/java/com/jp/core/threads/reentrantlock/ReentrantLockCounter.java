/*
 * File: ReentrantLockCounter.java Date: 10-Jul-2013 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.core.threads.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dimit.chadha
 */
public class ReentrantLockCounter {

	private final ReentrantLock lock = new ReentrantLock();

	private int count = 0;

	// Implicit locking using synchronized keyword
	public synchronized int getCountTwo() {
		System.out.println(Thread.currentThread().getName() + " gets Count: " + count);
		return count++;
	}

	// Locking using Lock and ReentrantLock
	public int getCount() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " gets Count: " + count);
			return count++;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final ReentrantLockCounter counter = new ReentrantLockCounter();
		Thread t1 = new Thread() {

			@Override
			public void run() {
				while (counter.getCount() > 6) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		};

		Thread t2 = new Thread() {

			@Override
			public void run() {
				while (counter.getCount() > 6) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		};
		t1.start();
		t2.start();
	}
}
