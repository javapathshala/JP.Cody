/*
 * File: Simulate.java Date: 19-Mar-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.core.threads.local;

import java.util.Random;

/**
 * @author dimit.chadha
 */
public class Simulate extends Thread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Creating  Multiple Requests");
		Thread threadOne = new Simulate();
		threadOne.start();

		Thread threadTwo = new Simulate();
		threadTwo.start();

		Thread threadThree = new Simulate();
		threadThree.start();
	}

	public void run() {
		Transaction tx = setTransaction();
		TxThreadLocal.set(tx);
		System.out.println("Thread Awaken.. Good Morning..." + Thread.currentThread().getName());
		System.out.println("Get Transation details ::");
		TxService service = new TxService();
		service.getTxDetails();

		// System.out.println("Trying to reset the threadlocal");
		// TxThreadLocal.unset();
		// service.getTxDetails();
	}

	/**
	 * 
	 */
	private static synchronized Transaction setTransaction() {
		Transaction tx = null;
		// synchronized (Simulate.class) {
		System.out.println(Thread.currentThread().getName());
		System.out.println("Entering ()");
		tx = new Transaction();
		int nextInt = new Random().nextInt(10);
		tx.setTxId(nextInt);
		tx.setTxName("TX-" + nextInt);
		try {
			System.out.println("Going to Sleep..." + Thread.currentThread().getName());
			Thread.sleep(1000);
			System.out.println("################################");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// }
		return tx;
	}
}
