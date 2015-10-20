/*
 * File: ThreadNotification.java
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
package com.jp.core.threads.notify;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author dimit.chadha
 *
 */
public class ThreadNotification {

    private volatile boolean go = false;

    public static void main(String args[]) throws InterruptedException {
        	final ThreadNotification threadNotification = new ThreadNotification();
      
        Runnable waitTask = new Runnable(){
            public void run(){
                try {
                	threadNotification.shouldGo();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadNotification.class.getName()).
                           log(Level.SEVERE, null, ex);
                }
                System.out.println(Thread.currentThread() + " finished Execution of waited object");
            }
        };
      
        Runnable notifyTask = new Runnable(){
            public void run(){
            	threadNotification.go();
                System.out.println(Thread.currentThread() + " finished Execution of notify thread");
            }
        };
      
        Thread t1 = new Thread(waitTask, "WT-1"); //will wait
        Thread t2 = new Thread(waitTask, "WT-2"); //will wait
        Thread t3 = new Thread(waitTask, "WT-3"); //will wait
        Thread t4 = new Thread(notifyTask,"NT-1"); //will notify
      
        //starting all waiting thread
        t1.start();
        t2.start();
        t3.start();
      
        //pause to ensure all waiting thread started successfully
        Thread.sleep(600);
      
        //starting notifying thread
        t4.start();
      
    }
    /*
     * wait and notify can only be called from synchronized method or bock
     */
	private synchronized void shouldGo() throws InterruptedException {
        while(!go){
            System.out.println(Thread.currentThread()
                         + " is going to wait on this object");
            wait(); //release lock and re-acquires on wake up
            System.out.println(Thread.currentThread() + " is woken up because notify was called.");
        }
        go = false; //resetting condition
    }
  
    /*
     * both shouldGo() and go() are locked on current object referenced by "this" keyword
     */
    private synchronized void go() {
        while (!go){
            System.out.println(Thread.currentThread()
            + " is going to notify all or one thread waiting on this object");
            go = true; //making condition true for waiting thread
            //notify(); // only one out of three waiting thread WT1, WT2,WT3 will woke up
			//notifyAll(); // all waiting thread  WT1, WT2,WT3 will woke up
            notifyAll();
        }
      
    }
  
}

