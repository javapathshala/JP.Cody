/*
 * File: Producer.java
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

import java.util.Random;

/**
 * @author dimit.chadha
 */
public class Producer implements Runnable {

	private Queue queue;

	private Random random = new Random();

	public Producer(Queue queue) {
		this.queue = queue;
	}

	public void run() {
		while (true) {
			queue.putItem(Math.abs(random.nextInt(1000)));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}