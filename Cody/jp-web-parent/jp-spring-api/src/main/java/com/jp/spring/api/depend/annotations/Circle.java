/*
 * File: Triangle.java
 * Date: 08-May-2013
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
package com.jp.spring.api.depend.annotations;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dimit.chadha
 */
public class Circle implements Shape {

	private Point center;

	// @Override
	public void draw() {
		System.out.println("Drawing Circle");
		System.out.println("Circle: Point is : ( " + center.getxPoint() + " , " + center.getyPoint() + ")");
	}

	public Point getCenter() {
		return center;
	}

	// @Required
	@Autowired
	public void setCenter(Point center) {
		this.center = center;
	}

}
