/*
 * File: CustomThreadPool.java
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

import java.util.ArrayList;
import java.util.Collection;

/**
 * Java Thread Pool This is a thread pool that for Java, it is simple to use and
 * gets the job done.
 * 
 * @author dimit.chadha
 */
public class CustomThreadPool {

	/**
	 * The threads in the pool.
	 */
	protected Thread threads[] = null;

	/**
	 * The backlog of assignments, which are waiting for the thread pool.
	 */
	Collection assignments = new ArrayList(3);

	/**
	 * A Done object that is used to track when the thread pool is done, that is
	 * has no more work to perform.
	 */
	protected Done done = new Done();

	/**
	 * The constructor.
	 * 
	 * @param size
	 *            How many threads in the thread pool.
	 */
	public CustomThreadPool(int size) {
		threads = new WorkerThread[size];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new WorkerThread(this);
			threads[i].start();
		}
	}

	/**
	 * Add a task to the thread pool. Any class which implements the Runnable
	 * interface may be assigned. When this task runs, its run method will be
	 * called.
	 * 
	 * @param r
	 *            An object that implements the Runnable interface
	 */
	public synchronized void assign(Runnable r) {
		done.workerBegin();
		assignments.add(r);
		notify();
	}

	/**
	 * Get a new work assignment.
	 * 
	 * @return A new assignment
	 */
	public synchronized Runnable getAssignment() {
		try {
			while (!assignments.iterator().hasNext()) {
				wait();
			}
			Runnable r = (Runnable) assignments.iterator().next();
			assignments.remove(r);
			return r;
		} catch (InterruptedException e) {
			done.workerEnd();
			return null;
		}
	}
	
	/**
	  * Called to block the current thread until
	  * the thread pool has no more work.
	  */
	 public void complete()
	 {
	  done.waitBegin();
	  done.waitDone();
	 }
	 
	 protected void finalize()  
	 {
		done.reset();
	  for (int i=0;i<threads.length;i++) {
	   threads[i].interrupt();
	   done.workerBegin();
	   threads[i].destroy();
	  }
	  done.waitDone();
	 }
}
