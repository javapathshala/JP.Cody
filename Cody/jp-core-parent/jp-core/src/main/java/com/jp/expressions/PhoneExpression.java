/*
 * File: PhoneExpression.java Date: 31-Jul-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.expressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dimit Chadha
 */
public class PhoneExpression {

	private static final String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{0,4})$";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile(expression);

		String phoneNumber = "972480-5594";
		Matcher matcher = pattern.matcher(phoneNumber);

		if (matcher.matches()) {
			System.out.println("Matches");
		} else {
			System.out.println("Invalid");
		}
	}
}
