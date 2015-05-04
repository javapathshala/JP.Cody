/*
 * File: SimpleLatch.java Date: 11-Jul-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.concurrency.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author dimit.chadha
 */
public class SimpleCyclicBarrier { // Runnable task for each thread

	private static class Task implements Runnable {

		private CyclicBarrier barrier;

		public Task(CyclicBarrier barrier) {
			this.barrier = barrier;
		}

		public void run() {
			try {
				System.out.println(Thread.currentThread().getName() + " is waiting on barrier");
				barrier.await();
				System.out.println(Thread.currentThread().getName() + " has crossed the barrier");
			} catch (InterruptedException ex) {
			} catch (BrokenBarrierException ex) {
			}
		}
	}

	public static void main(String args[]) {

		// creating CyclicBarrier with 3 parties i.e. 3 Threads needs to call
		// await()
		final CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {

			public void run() {
				// This task will be executed once all thread reaches barrier
				System.out.println("All parties are arrived at barrier, lets play now !!!!!!!!!!!!");
			}
		});

		// starting each of thread
		Thread t1 = new Thread(new Task(cb), "Thread 1");
		Thread t2 = new Thread(new Task(cb), "Thread 2");
		Thread t3 = new Thread(new Task(cb), "Thread 3");

		t1.start();
		t2.start();
		t3.start();

	}
}
