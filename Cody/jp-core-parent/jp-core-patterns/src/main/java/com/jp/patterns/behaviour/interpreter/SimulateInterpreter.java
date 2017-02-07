/*
 * File: SimulateInterpreter.java Date: 21-Aug-2013 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.patterns.behaviour.interpreter;

/**
 * Interpreter pattern is one of the behavioral design pattern and is used to
 * defines a grammatical representation for a language and provides an
 * interpreter to deal with this grammar. The best example of this pattern is
 * java compiler that interprets the java source code into byte code that is
 * understandable by JVM. Google Translator is also an example of interpreter
 * pattern where the input can be in any language and we can get the output
 * interpreted in another language.
 * 
 * @author dchadha
 */
public class SimulateInterpreter {

	InterpreterContext ic;

	public SimulateInterpreter(InterpreterContext ic) {
		this.ic = ic;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str1 = "5 in Binary";
		String str2 = "28 in Hexadecimal";
		SimulateInterpreter si = new SimulateInterpreter(new InterpreterContext());
		System.out.println(str1 + "= " + si.interpret(str1));
		System.out.println(str2 + "= " + si.interpret(str2));
	}

	private String interpret(String str) {
		Expression exp = null;
		// create rules for expressions
		if (str.contains("Hexadecimal")) {
			exp = new IntToHexExpression(Integer.parseInt(str.substring(0, str.indexOf(" "))));
		} else if (str.contains("Binary")) {
			exp = new IntToBinaryExpression(Integer.parseInt(str.substring(0, str.indexOf(" "))));
		} else
			return str;
		return exp.interpret(ic);
	}

}
