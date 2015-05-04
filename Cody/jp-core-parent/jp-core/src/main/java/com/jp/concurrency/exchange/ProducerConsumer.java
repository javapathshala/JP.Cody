/*
 * File: ProducerConsumer.java Date: 04-May-2015 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.concurrency.exchange;

/**
 * @author Dimit Chadha
 */
import java.util.concurrent.Exchanger;

public class ProducerConsumer {

	Exchanger exchanger = new Exchanger();

	private class Producer implements Runnable {
		private String queue;

		@Override
		public void run() {
			try {
				// create tasks & fill the queue
				// exchange the full queue for a empty queue with Consumer
				queue = (String) exchanger.exchange("Ready Queue");
				System.out.println(Thread.currentThread().getName() + " now has " + queue);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private class Consumer implements Runnable {

		private String queue;

		@Override
		public void run() {
			try {
				// do procesing & empty the queue
				// exchange the empty queue for a full queue with Producer
				queue = (String) exchanger.exchange("Empty Queue");
				System.out.println(Thread.currentThread().getName() + " now has " + queue);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void start() {
		new Thread(new Producer(), "Producer").start();
		new Thread(new Consumer(), "Consumer").start();
	}

	public static void main(String[] args) {
		new ProducerConsumer().start();
	}

}
