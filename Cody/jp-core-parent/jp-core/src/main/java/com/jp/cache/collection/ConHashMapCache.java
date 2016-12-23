/*
 * File: ConHashMapCache.java
 * Date: 10-Jul-2013
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
package com.jp.cache.collection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dimit.chadha
 */
public class ConHashMapCache {

	private static final String CONCURRENCY_LEVEL_DEFAULT = "2";

	private static final String CONCURRENCY_KEY = "concurrency";

	private ConcurrentMap<Double, Double> cache = new ConcurrentHashMap<Double, Double>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final ConHashMapCache test = new ConHashMapCache();
		final int concurrencyLevel = Integer.parseInt(System.getProperty(CONCURRENCY_KEY, CONCURRENCY_LEVEL_DEFAULT));
		final ExecutorService executor = Executors.newCachedThreadPool();
		try {
			for (int i = 0; i < concurrencyLevel; i++) {
				for (String s : args) {
					final Double d = Double.valueOf(s);
					executor.submit(new Runnable() {

						public void run() {
							System.out.printf("sqrt of %s = %s in thread %s%n", d, test.getSqrt(d), Thread.currentThread().getName());
						}
					});
				}

			}
		} finally {
			executor.shutdown();
		}

	}

	// 4 steps as outlined above
	public double getSqrt(Double d) {
		Double sqrt = cache.get(d);
		if (sqrt == null) {
			sqrt = Math.sqrt(d);
			System.out.printf("calculated sqrt of ########## %s = %s%n", d, sqrt);
			Double existing = cache.putIfAbsent(d, sqrt);
			if (existing != null) {
				System.out.printf("discard calculated sqrt %s and use the cached sqrt %s", sqrt, existing);
				sqrt = existing;
			}
		}
		return sqrt;
	}
}
