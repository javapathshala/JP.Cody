/*
 * File: TaskHealth.java
 * Date: 06-Oct-2015
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
package com.jp.core.threads.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class TaskHealth {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String... args) throws InterruptedException, ExecutionException {
		ExecutorService exService = Executors.newSingleThreadExecutor();
		FutureTask<String> futureTask = new FutureTask<String>(new DemoTask());
		exService.execute(futureTask);
		// checks if task done
		System.out.println("Is Task Done ?? --> " + futureTask.isDone());
		// checks if task canceled
		System.out.println(futureTask.isCancelled());
//		boolean b=futureTask.cancel(true);
//		futureTask.run();
//		System.out.println(b);
		// fetches result and waits if not ready
		System.out.println(futureTask.get());
		System.exit(0);
	}
}

class DemoTask implements Callable<String> {
	public String call() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		return "Task Done";
	}
}
