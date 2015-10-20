/*
 * File: WorkerThread.java
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
 * The worker threads that make up the thread pool.
 * 
 * @author dimit.chadha
 */
public class WorkerThread extends Thread {

	/**
	 * True if this thread is currently processing.
	 */
	public boolean busy;

	/**
	 * The thread pool that this object belongs to.
	 */
	public CustomThreadPool owner;

	/*
	 * The constructor.
	 * @param o the thread pool
	 */
	WorkerThread(CustomThreadPool o) {
		owner = o;
	}
	/**
	  * Scan for and execute tasks.
	  */
	 public void run()
	 {
	  Runnable target = null;

	  do {
	   target = owner.getAssignment();
	   if (target!=null) {
	    target.run();      
	    owner.done.workerEnd();
	   }
	  } while (target!=null);
	 }
	
}
