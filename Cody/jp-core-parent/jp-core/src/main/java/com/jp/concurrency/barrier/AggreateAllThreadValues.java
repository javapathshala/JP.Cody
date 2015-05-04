/*
 * File: AggreateAllThreadValues.java Date: 11-Jul-2013 This source code is part
 * of Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.concurrency.barrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author dimit.chadha
 */
public class AggreateAllThreadValues {

	List<Integer> list = new ArrayList<Integer>();

	final CyclicBarrier barrierTest;

	AggreateAllThreadValues() {
		barrierTest = new CyclicBarrier(3, new Runnable() {

			public void run() {
				addListvalue();

			}
		});
		Thread t1 = new Thread(new Task(1, 3));
		Thread t2 = new Thread(new Task(4, 6));
		Thread t3 = new Thread(new Task(7, 9));
		t1.setName("Sum 1 ");
		t2.setName("Sum 2 ");
		t3.setName("Sum 3 ");
		t1.start();
		t2.start();
		t3.start();

		barrierTest.reset();
	}

	void addListvalue() {
		int total = 0;
		for (int j = 0; j < list.size(); j++) {
			total += list.get(j);
		}
		System.out.println("Total addtion:" + total);
	}

	class Task implements Runnable {

		int start = 0;

		int end = 0;

		Task(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public void run() {
			add(start, end);
			try {
				barrierTest.await();
				// barrierTest.await(1, TimeUnit.SECONDS);
			} catch (InterruptedException ex) {
				return;
			} catch (BrokenBarrierException ex) {
				return;
			}
			// catch (TimeoutException e) {
			// e.printStackTrace();
			// }
		}
	}

	public static void main(String... args) {
		new AggreateAllThreadValues();
	}

	synchronized void add(int start, int end) {
		int sum = 0;
		for (int s = start; s <= end; s++) {
			sum += s;
		}
		list.add(sum);
		System.out.println("Per Thread Addition for " + Thread.currentThread().getName() + "  :" + sum);

	}
}
