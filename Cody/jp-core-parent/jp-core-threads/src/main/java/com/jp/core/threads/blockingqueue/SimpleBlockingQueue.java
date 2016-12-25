/*
 * File: SimpleBlockingQueue.java
 * Date: 26-Dec-2016
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
package com.jp.core.threads.blockingqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Dimit Chadha
 */
public class SimpleBlockingQueue {

	BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleBlockingQueue blockingQueue = new SimpleBlockingQueue();
		blockingQueue.add();
		blockingQueue.remove();

	}

	private void add() {
		try {
			queue.put("Item 1");
			queue.put("Item 2");
			queue.put("Item 3");
//			queue.put("Item 4");
		} catch (IllegalStateException e) {
			System.out.println("Kaake Queue is full ! Can not Add more items");
		} catch (InterruptedException e) {

		}
		Iterator<String> itr = queue.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

	private void remove() {
		try {
			queue.remove();
			queue.remove();
			queue.remove();
			queue.remove();
		} catch (NoSuchElementException e) {
			System.out.println("Kaake Queue is about to Empty ! Wait for someone to add more items");
		}
		Iterator<String> itr = queue.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

}
