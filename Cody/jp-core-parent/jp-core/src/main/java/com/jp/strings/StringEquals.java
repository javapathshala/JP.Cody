/*
 * File: StringEquals.java
 * Date: 06-Aug-2015
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
package com.jp.strings;

/**
* @author Dimit Chadha
*/
public class StringEquals {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s1 = "Dimit";
		String s2 = "Dimit";
		String s3 = "Chadha";
		String s4 = s2.intern();
		System.out.println(s4);
		s4 = s4 + 1;
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		System.out.println(s4);
		System.out.println(s2);

	}
}
