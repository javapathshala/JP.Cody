/*
 * File: TestClass.java
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
package com.jp.equalsoverride;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class TestClass {
	private int testValue;

	TestClass(int val) {
		testValue = val;
	}

	public int getMoofValue() {
		return testValue;
	}

	public boolean equals(Object o) {
		if ((o instanceof TestClass) && (((TestClass) o).getMoofValue() == this.testValue)) {
			return true;
		} else {
			return false;
		}
	}
}
