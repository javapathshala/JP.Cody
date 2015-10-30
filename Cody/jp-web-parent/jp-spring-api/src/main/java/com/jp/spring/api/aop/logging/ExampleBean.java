/*
 * File: ExampleBean.java
 * Date: 17-Jul-2013
 *
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn 
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any 
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention orders
 * and to prosecute the authors of any infraction.
 * 
 * Visit us at www.javapathshala.com
 */
package com.jp.spring.api.aop.logging;

/**
 * @author dimit.chadha
 */
public class ExampleBean {

	/**
	 * The name of the example.
	 */
	private String exampleName;

	/**
	 * The version of the example.
	 */
	private String exampleVersion;

	/**
	 * No-arguments constructor.
	 */
	public ExampleBean() {
	}

	/**
	 * Constructor accepting arguments to set my state.
	 * 
	 * @param newExampleName
	 *            Name of this example.
	 * @param newExampleVersion
	 *            Version of this example.
	 */
	public ExampleBean(final String newExampleName, final String newExampleVersion) {
		this.exampleName = newExampleName;
		this.exampleVersion = newExampleVersion;
	}

	/**
	 * Provide my example name.
	 * 
	 * @return My example name.
	 */
	public String getExampleName() {
		return exampleName;
	}

	/**
	 * Set/change my example name.
	 * 
	 * @param exampleName
	 *            New name for this example.
	 */
	public void setExampleName(String exampleName) {
		this.exampleName = exampleName;
	}

	/**
	 * Provide my example version.
	 * 
	 * @return My example version.
	 */
	public String getExampleVersion() {
		return exampleVersion;
	}

	/**
	 * Set/change the example's version.
	 * 
	 * @param exampleVersion
	 *            New value for my version.
	 */
	public void setExampleVersion(String exampleVersion) {
		this.exampleVersion = exampleVersion;
	}

	/**
	 * Provide my example name with version in parentheses.
	 * 
	 * @return My name and version if format "Name (Version)."
	 */
	public String provideNameAndVersion() {
		return this.exampleName + " (" + this.exampleVersion + ")";
	}
}
