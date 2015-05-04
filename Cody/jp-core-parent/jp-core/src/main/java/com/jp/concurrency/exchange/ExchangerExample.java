/*
 * File: ExchangerExample.java Date: 04-May-2015 This source code is part of
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
public class ExchangerExample {

	public static void main(String[] args) {

		Exchanger<String> exchanger = new Exchanger<String>();

		Thread t1 = new MyThread(exchanger, "I like coffee.");
		Thread t2 = new MyThread(exchanger, "I like tea");
		t1.start();
		t2.start();
	}
}

class MyThread extends Thread {

	Exchanger<String> exchanger;
	String message;

	MyThread(Exchanger<String> exchanger, String message) {
		this.exchanger = exchanger;
		this.message = message;
	}

	public void run() {
		try {
			System.out.println(this.getName() + " message: " + message);

			// exchange messages
			message = exchanger.exchange(message);

			System.out.println(this.getName() + " message: " + message);
		} catch (Exception e) {
		}
	}
}
