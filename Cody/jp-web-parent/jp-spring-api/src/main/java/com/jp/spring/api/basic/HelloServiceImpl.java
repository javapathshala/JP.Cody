/*
 * File: HelloImpl.java
 * Date: 30-Oct-2015
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention
 * orders
 * and to prosecute the authors of any infraction.
 * Visit us at www.javapathshala.com
 */
package com.jp.spring.api.basic;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class HelloServiceImpl implements HelloService {

	private String message;

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	private String value;

	public HelloServiceImpl(String value) {
		this.value = value;
	}

	@Override
	public String sayHello() {
		return "Hello Spring Master. Property value  is --> " + message + " Value is --> " + value;

	}

}
