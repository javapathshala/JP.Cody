/*
 * File: FilePathExpression.java Date: 23-Jul-2015 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
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
public class FilePathExpression {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String driveLetter = "[a-zA-Z]:\\\\";
		String folderName = "\\\\[\\w-#.\\s\\\\]*";

		String IPADDRESS_PATTERN = "\\\\([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
				+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])" + folderName;

		// "(?:[a-zA-Z]\\:)\\\\([\\w-]+\\\\)*\\w([\\w-.])+"

		String regex = "^(" + driveLetter + folderName + "|" + folderName + "|" + IPADDRESS_PATTERN + ")";

		String filePath = "c:\\a1as#kk\\aa\\fjf\\djdj";
		String filePath2 = "\\VDALVWAPPD024.vertex.client\\si s\\s-m";
		String filePath3 = "\\10.213.18.36\\an_djd\\d jd";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(filePath3);

		if (matcher.matches()) {
			System.out.println("Matches");
		} else {
			System.out.println("Invalid");
		}
	}

}
