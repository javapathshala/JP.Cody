/*
 * File: SimpleThread.java
 * Date: 17-Apr-2012
 *
 * Copyright (C) 2012, Java Pathshala
 * All rights reserved.
 * Visit us at blog http://javapathshala.wordpress.com
 * Cloud Applications at http://dimitcloud.cloudfoundry.com
 */
package com.jp.core.threads;

/**
 * Description :
 * 
 * @author dimit.chadha
 */
public class SimpleThreadExtends extends Thread {

	public static void main(String[] args) {
		SimpleThreadExtends st = new SimpleThreadExtends();
		st.setName("Tech Thread");
		st.start();
		System.out.println("After Thread");
	}

	public void run() {
		System.out.println(Thread.currentThread().getName());
		System.out.println("In Thread Run");
	}
}
