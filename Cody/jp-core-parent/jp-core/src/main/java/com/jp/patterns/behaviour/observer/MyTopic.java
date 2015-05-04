/*
 * File: MyTopic.java Date: 04-May-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.patterns.behaviour.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dimit Chadha
 */
public class MyTopic implements Subject {

	private final Object MUTEX = new Object();

	private List<MyObserver> observers;
	private String message;
	private boolean changed;

	public MyTopic() {
		this.observers = new ArrayList<>();
	}

	@Override
	public void register(MyObserver obj) {
		if (obj == null) {
			throw new NullPointerException("Null Observer");
		}
		synchronized (MUTEX) {
			if (!observers.contains(obj))
				observers.add(obj);
		}
	}

	@Override
	public void unregister(MyObserver obj) {
		synchronized (MUTEX) {
			observers.remove(obj);
		}
	}

	@Override
	public void notifyObservers() {
		List<MyObserver> observersLocal = null;
		// synchronization is used to make sure any observer registered after
		// message is received is not notified
		synchronized (MUTEX) {
			if (!changed) {
				return;
			}
			observersLocal = new ArrayList<>(this.observers);
			this.changed = false;
		}
		for (MyObserver obj : observersLocal) {
			obj.update();
		}
	}

	public Object getUpdate(MyObserver obj) {
		return this.message;
	}

	// method to post message to the topic
	public void postMessage(String msg) {
		System.out.println("Message Posted to Topic:" + msg);
		this.message = msg;
		this.changed = true;
		notifyObservers();
	}

}
