/*
 * File: Done.java
 * Date: 10-Jul-2013
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
package com.jp.core.threads.pool.custom;

/**
 * This is a thread pool for Java, it is simple to use and gets the job done.
 * 
 * @author dimit.chadha
 */
public class Done {

	/**
	 * The number of Worker object threads that are currently working on
	 * something.
	 */
	private int _activeThreads = 0;

	/**
	 * This boolean keeps track of if the very first thread has started or not.
	 * This prevents this object from falsely reporting that the ThreadPool is
	 * done, just because the first thread has not yet started.
	 */
	private boolean _started = false;

	/**
	 * This method can be called to block the current thread until the
	 * ThreadPool is done.
	 */

	synchronized public void waitDone() {
		try {
			while (_activeThreads > 0) {
				wait();
			}
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Called to wait for the first thread to start. Once this method returns
	 * the process has begun.
	 */

	synchronized public void waitBegin() {
		try {
			while (!_started) {
				wait();
			}
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Called by a Worker object to indicate that it has begun working on a
	 * workload.
	 */
	synchronized public void workerBegin() {
		_activeThreads++;
		_started = true;
		notify();
	}

	/**
	 * Called by a Worker object to indicate that it has completed a workload.
	 */
	synchronized public void workerEnd() {
		_activeThreads--;
		notify();
	}

	/**
	 * Called to reset this object to its initial state.
	 */
	synchronized public void reset() {
		_activeThreads = 0;
	}

}
