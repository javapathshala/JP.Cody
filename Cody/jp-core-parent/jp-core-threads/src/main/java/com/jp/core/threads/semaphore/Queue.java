/*
 * File: Queue.java
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

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * @author dimit.chadha
 */
public class Queue{

	private Semaphore semaphore;

	private LinkedList<Integer> list;

	public Queue(int maxItems) {
		semaphore = new Semaphore(maxItems);
		list = new LinkedList<Integer>();
	}

	public synchronized void putItem(int number) {
		try {
			if (semaphore.availablePermits() > 0) {
				semaphore.acquire();
				list.addLast(number);
				System.out.println("Produce : " + number);
				System.out.println("Elements in queue " + list);
			} else {
				System.out.println("Wait for consumer");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void takeItem() {
		if (!list.isEmpty()) {
			System.out.println("Consume : " + list.pollFirst());
			semaphore.release();
		} else {
			System.out.println("Wait for producer");
		}
	}
}
