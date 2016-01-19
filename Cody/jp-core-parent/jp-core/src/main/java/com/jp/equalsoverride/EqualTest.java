/*
 * File: EqualTest.java
 * Date: 19-Jan-2016
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
package com.jp.equalsoverride;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class EqualTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestClass one = new TestClass(8);
		TestClass two = new TestClass(8);
		if (one.equals(two)) {
			System.out.println("one and two are equal");
		} else {
			System.out.println("one and two are not equal");
		}
	}
}
