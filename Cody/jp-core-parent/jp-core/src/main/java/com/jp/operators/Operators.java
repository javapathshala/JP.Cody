/*
 * File: ObjectCompare.java
 * Date: 22-Dec-2016
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
package com.jp.operators;

/**
 * @author 72010964
 */
public class Operators {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer i = new Integer(0);
		Float f = new Float(0);
		System.out.println(i);
		System.out.println(f);
		System.out.println(i.equals(f));// false
		System.out.println(0 == 0.0);// true

		
		int i1 = 0xffffffff;
		int i2 = i1 << 1;
		int i3 = i1 >> 1;
		int i4 = i1 >>> 1;
		System.out.print(Integer.toHexString(i2) + ",");
		System.out.print(Integer.toHexString(i3) + ",");
		System.out.print(Integer.toHexString(i4));
	}

}
