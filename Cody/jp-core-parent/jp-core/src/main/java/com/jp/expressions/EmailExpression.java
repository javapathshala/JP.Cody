/*
 * File: EmailExpression.java Date: 31-Jul-2015 This source code is part of Java
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
public class EmailExpression {

	private static String pattern = "([a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*" +
            "|\"([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09" +
            "\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9]([a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]" +
            "*[a-z0-9])?|\\[((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]" +
            "?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]" +
            "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	private static Pattern mypattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);

	public static void main(String args[]) {

		String valEmail1 = "testemail@domain.com";
		String invalEmail1 = "....@domain.com";
		String invalEmail2 = ".$$%%@domain.com";
		String valEmail2 = "test.email@domain.com";

		System.out.println("Is Email ID1 valid? " + validateEMailID(valEmail1));
		System.out.println("Is Email ID1 valid? " + validateEMailID(invalEmail1));
		System.out.println("Is Email ID1 valid? " + validateEMailID(invalEmail2));
		System.out.println("Is Email ID1 valid? " + validateEMailID(valEmail2));

	}

	public static boolean validateEMailID(String emailID) {
		Matcher mtch = mypattern.matcher(emailID);
		if (mtch.matches()) {
			return true;
		}
		return false;
	}
}
