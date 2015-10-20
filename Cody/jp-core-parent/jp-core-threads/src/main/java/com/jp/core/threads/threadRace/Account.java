/*
 * File: Account.java
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
public class Account {

	private int accountNumber;

	private double accountBalance;

	public int getAccountNumber() {
		return accountNumber;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public Account(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	// If this method is not synchronized, you will see race condition on
	// Remove syncronized keyword to see race condition
	public synchronized boolean deposit(double amount) {
		if (amount < 0) {
			return false;
		} else {
			accountBalance = accountBalance + amount;
			System.out.println("New Deposite : "+accountBalance);
			return true;
		}
	}

	// If this method is not synchronized, you will see race condition on
	// Remove syncronized keyword to see race condition
	public synchronized boolean withdraw(double amount) {
		if (amount > accountBalance) {
			return false;
		} else {
			accountBalance = accountBalance - amount;
			System.out.println("New withdraw : "+accountBalance);
			return true;
		}
	}
}
