/*
 * File: Transaction.java
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
class Transaction extends Thread {

	public static enum TransactionType {
		DEPOSIT(1), WITHDRAW(2);

		private int value;

		private TransactionType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	};

	private TransactionType transactionType;

	private Account account;

	private double amount;

	/*
	 * If transactionType == 1, deposit else if transactionType == 2 withdraw
	 */
	public Transaction(Account account, TransactionType transactionType, double amount) {
		this.transactionType = transactionType;
		this.account = account;
		this.amount = amount;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " is running ");
		switch (this.transactionType) {
			case DEPOSIT:
				System.out.println(Thread.currentThread().getName() + " is depositing ");
				deposit();
				printBalance();
				break;
			case WITHDRAW:
				System.out.println(Thread.currentThread().getName() + " is withdrawing ");
				withdraw();
				printBalance();
				break;
			default:
				System.out.println("NOT A VALID TRANSACTION");
		}
		;
	}

	public void deposit() {
		this.account.deposit(this.amount);
	}

	public void withdraw() {
		this.account.withdraw(amount);
	}

	public void printBalance() {
		System.out.println(Thread.currentThread().getName() + " : TransactionType: " + this.transactionType + ", Amount: " + this.amount);
		System.out.println("Account Balance: " + this.account.getAccountBalance());
	}
}
