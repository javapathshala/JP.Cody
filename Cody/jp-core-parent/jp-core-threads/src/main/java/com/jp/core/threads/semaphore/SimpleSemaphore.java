/*
 * File: SimpleSemaphore.java
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
public class SimpleSemaphore {

	private final int MAX_COUNT = 10;

	private boolean[] flag = new boolean[MAX_COUNT];

	private Object[] res = new Object[MAX_COUNT];

	private final Semaphore semaphore = new Semaphore(MAX_COUNT, true);

	public SimpleSemaphore() {
		initializeResource();
	}

	private void initializeResource() {
		for (int i = 0; i < MAX_COUNT; ++i) {
			res[i] = new Thread();
		}
	}

	public synchronized Object getResource() throws InterruptedException {
		semaphore.acquire();
		for (int i = 0; i < MAX_COUNT; ++i) {
			if (!flag[i]) {
				flag[i] = true;
				return res[i];
			}
		}
		return null;
	}

	public synchronized void putResource(Object ob) {
		boolean isRes = false;
		for (int i = 0; i < MAX_COUNT; ++i) {
			if (ob == res[i]) {
				if (flag[i]) {
					flag[i] = false;
					isRes = true;
				} else
					isRes = false;
			}
		}
		if (isRes) {
			semaphore.release();
		}

	}

	public static void main(String args[]) {
		new SimpleSemaphore();
		
	}
}