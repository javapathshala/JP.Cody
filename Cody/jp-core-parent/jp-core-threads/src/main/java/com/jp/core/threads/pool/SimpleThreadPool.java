/*
 * File: SimpleThreadPool.java
 * Date: 21-Mar-2013
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
package com.jp.core.threads.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dimit.chadha
 */
public class SimpleThreadPool {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(1);
//		ExecutorService executor1 = Executors.newFixedThreadPool(1); // 2nd thread pool :)
		System.out.println(Runtime.getRuntime().availableProcessors());
		for (int i = 0; i <= 10; i++) {
			Runnable worker = new WorkerThread("Get this man" + i);
			executor.execute(worker);
	//		executor1.execute(worker);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {

		}
		System.out.println("Finished all threads");
	}
}