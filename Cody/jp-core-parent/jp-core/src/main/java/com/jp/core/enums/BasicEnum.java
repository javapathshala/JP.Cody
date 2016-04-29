/*
 * File: BasicEnum.java
 * Date: 30-Apr-2016
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
package com.jp.core.enums;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public enum BasicEnum {

	OK("This is OK"),

	INVALID_REQUEST_PARAMETERS("Invalid Parameters");

	String value;

	private BasicEnum(String value) {
		this.value = value;
	}

	public String getStatment() {
		return this.value;
	}

}
