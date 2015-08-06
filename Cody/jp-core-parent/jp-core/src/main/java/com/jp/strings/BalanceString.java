/*
 * File: BalanceString.java
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

import java.util.Stack;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class BalanceString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BalanceString bal = new BalanceString();
		String input = "[(2+3]]"; //balanced value - [(2+3)]
		if (bal.IsStringBalance(input)) {
			System.out.println("String - " + input + " is balanced");
		} else {
			System.out.println("String - " + input + " is not balanced");
		}

	}

	/**
	 * @param input
	 * @return
	 */
	private boolean IsStringBalance(String input) {
		Stack<Character> stack = new Stack<Character>();
		int len = input.length();
		for (int i = 0; i < len; i++) {
			char c = input.charAt(i);
			switch (c) {

				case '{':
				case '(':
				case '[':
					stack.push(c);
					break;

				case ']':
					if (stack.isEmpty() || stack.pop() != '[')
						return false;
					break;
				case ')':
					if (stack.isEmpty() || stack.pop() != '(')
						return false;
					break;
				case '}':
					if (stack.isEmpty() || stack.pop() != '{')
						return false;
					break;
			}
		}
		return stack.isEmpty();
	}

}
