/*
 * File: Triangle.java
 * Date: 08-May-2013
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
package com.jp.spring.api.depend.simple;

/**
 * @author dimit.chadha
 */
public class Triangle {

	private String type;

	private int height;

	private String color;

	// for constructor dependency
	public Triangle(int height) {
		this.height = height;
	}

	public Triangle(int height, String color) {
		this.height = height;
		this.color = color;
	}

	public void draw() {
		System.out.println(getType() + " Triangle Drawn Perfectly");
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

}
