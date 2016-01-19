/*
 * File: ScanNext.java
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
package com.jp.text.scanner;

import java.util.Scanner;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class ScanNext {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean b2, b;
		int i;
		String s, hits = " ";
		Scanner s1 = new Scanner("1 true 34 hi");
		Scanner s2 = new Scanner("1 true 34 hi");
		while (b = s1.hasNext()) {
			s = s1.next();
			hits += "s";
			System.out.println(s);
		}
		System.out.println("hits " + hits);
		hits = "  ";
		while (b = s2.hasNext()) {
			if (s2.hasNextInt()) {
				i = s2.nextInt();
				System.out.println(i);
				hits += "i";
			} else if (s2.hasNextBoolean()) {
				b2 = s2.nextBoolean();
				System.out.println(b);
				System.out.println(b2);
				hits += "b";
			} else {
				s2.next();
				hits += "s2";
			}
			System.out.println(s2);

		}
		System.out.println("hits " + hits);
	}

}
