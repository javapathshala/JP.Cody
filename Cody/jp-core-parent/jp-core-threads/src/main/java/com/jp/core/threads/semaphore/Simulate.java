/*
 * File: Simulate.java
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

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author dimit.chadha
 */
public class Simulate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Queue queue = new Queue(5);
		Executor producer = Executors.newSingleThreadExecutor();
		Executor consumer = Executors.newSingleThreadExecutor();
		producer.execute(new Producer(queue));
		consumer.execute(new Consumer(queue));
	}

}
