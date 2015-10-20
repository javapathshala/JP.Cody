/*
 * File: ThreadRaceCondition.java
 * Date: 09-Jul-2013
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
package com.jp.core.threads.threadRace;

/**
 * @author dimit.chadha
 */
public class ThreadRaceCondition {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Account myAccount = new Account(22222222);

		// Expected deposit: 250
		for (int i = 0; i < 50; i++) {
			Transaction t = new Transaction(myAccount, Transaction.TransactionType.DEPOSIT, 5.00);
			t.start();
		}

		// Expected withdrawal: 50
		for (int i = 0; i < 50; i++) {
			Transaction t = new Transaction(myAccount, Transaction.TransactionType.WITHDRAW, 1.00);
			t.start();

		}

		// Temporary sleep to ensure all threads are completed. Don't use in
		// realworld :-)
		Thread.sleep(1000);
		// Expected account balance is 200
		System.out.println("Final Account Balance: " + myAccount.getAccountBalance());

	}

}