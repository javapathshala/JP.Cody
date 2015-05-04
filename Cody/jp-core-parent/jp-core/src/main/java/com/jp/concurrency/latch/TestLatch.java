/*
 * File: TestLatch.java Date: 04-May-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.concurrency.latch;

import java.util.concurrent.CountDownLatch;

/**
 * @author Dimit Chadha
 */
public class TestLatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(3);
		Thread cacheService = new Thread(new Service("CacheService", 3000, latch));
		Thread alertService = new Thread(new Service("AlertService", 6000, latch));
		Thread validationService = new Thread(new Service("ValidationService", 3000, latch));

		cacheService.start(); // separate thread will initialize CacheService
		alertService.start(); // another thread for AlertService initialization
		validationService.start();

		// application should not start processing any thread until all service
		// is up and ready to do there job.Count down latch is idle choice here,
		// main thread will start with count 3 and wait until count reaches
		// zero. each thread once up and read will do a count down. this will
		// ensure that main thread is not start processing until all services is
		// up. count is 3 since we have 3 Threads (Services)

		try {
			latch.await(); // main thread is waiting on CountDownLatch to
							// finish
			System.out.println("Now in  Thread Name:: " + Thread.currentThread().getName());
			System.out.println("All services are up, Application is starting now");
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}
