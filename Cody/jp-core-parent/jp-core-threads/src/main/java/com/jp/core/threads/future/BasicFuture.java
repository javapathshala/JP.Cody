/*
 * File: BasicFuture.java
 * Date: 25-Sep-2015
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
import java.util.concurrent.Future;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class BasicFuture {

	private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FactorialCalculator task = new FactorialCalculator(5);

		System.out.println("Submitting Task ...");

		Future future = threadpool.submit(task);

		System.out.println("Task is submitted");
		while (!future.isDone()) {
			System.out.println("Task is not completed yet....");
			Thread.sleep(1);
			// sleep for 1 millisecond before checking again

		}
		System.out.println("Task is completed, let's check result");
		long factorial = (long) future.get();
		System.out.println("Factorial of 1000000 is : " + factorial);
		threadpool.shutdown();

	}

	private static class FactorialCalculator implements Callable {
		private final int number;

		public FactorialCalculator(int number) {
			this.number = number;
		}

		@Override
		public Object call() throws Exception {
			long output = 0;
			try {
				output = factorial(number);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			return output;
		}

		private long factorial(int number) throws InterruptedException {
			if (number < 0) {
				throw new IllegalArgumentException("Number must be greater than zero");
			}
			long result = 1;
			while (number > 0) {
				Thread.sleep(1); // adding delay for example
				result = result * number;
				number--;
			}
			return result;
		}
	}
}
