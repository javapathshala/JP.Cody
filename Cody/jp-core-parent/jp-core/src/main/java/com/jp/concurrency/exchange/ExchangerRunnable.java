/*
 * File: ExchangerRunnable.java Date: 04-May-2015 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.concurrency.exchange;

import java.util.concurrent.Exchanger;

/**
 * @author Dimit Chadha
 */
public class ExchangerRunnable implements Runnable {

	Exchanger<Address> exchanger = null;

	Address address = null;

	public ExchangerRunnable(Exchanger<Address> exchanger, Address address) {
		this.exchanger = exchanger;
		this.address = address;
	}

	@Override
	public synchronized void run() {
		try {
			Address residence = address;
			address = exchanger.exchange(address);
			System.out.println(Thread.currentThread().getName() + " exchanged " + residence + " for " + address);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
