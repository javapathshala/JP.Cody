/*
 * File: MethodInnerClass.java Date: 30-Jul-2013 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.inner;

/**
 * @author dimit.chadha
 */
public class MethodInnerClass {
	int x = 20;

	public MethodInnerClass() {
	}

	public int sum(int a, int b) {
		final int c = 10;
		class LocalInnerClass {

			public int diffInner(int a, int b) {
				System.out.println(c);
				System.out.println(x);
				return a - b;
			}
		}
		LocalInnerClass localInnerClass = new LocalInnerClass();
		System.out.println(localInnerClass.diffInner(a, b));
		return a + b;
	}

	public static void main(String[] args) {
		MethodInnerClass methodInnerClass = new MethodInnerClass();
		System.out.println(methodInnerClass.sum(2, 4));

	}
}
