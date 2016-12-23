/*
 * File: StaticInnerClass.java Date: 30-Jul-2013 This source code is part of
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
public class StaticOuterClass {

	private static String str = "Static-String";
	private String nonStr = "NonStatic-String";

	public StaticOuterClass() {
	}

	protected static class StaticInnerClass {
		public static int innerMethod() {
			System.out.println(str);
			StaticOuterClass outerClass = new StaticOuterClass();
			System.out.println(outerClass.nonStr);
			return 4 + 9;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(StaticInnerClass.innerMethod());

	}

}
