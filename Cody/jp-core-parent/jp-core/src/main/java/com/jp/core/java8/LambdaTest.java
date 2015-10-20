/*
 * File: LambaTest.java
 * Date: 01-Sep-2015
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
package com.jp.core.java8;

import java.util.List;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class LambdaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		LambdaTest lambdaTest = new LambdaTest();
		lambdaTest.withOutLambda(new TestIfc() {

			@Override
			public boolean isGood(int value) {
				return value == 42;
			}
		});

		lambdaTest.withOutLambda(answer -> answer > 42);

	}

	/**
	 * @param testIfc 
	 * 
	 */
	private void withOutLambda(TestIfc testIfc) {
		System.out.println(testIfc.isGood(45));

	}


}

interface TestIfc {

	public boolean isGood(int value);
}
