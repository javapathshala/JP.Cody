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
package com.jp.spring.api.depend.annotations;

/**
 * @author dimit.chadha
 */
public class Triangle implements Shape {

	private Point pointA;

	private Point pointB;

	private Point pointC;

//	@Override
	public void draw() {
		System.out.println("Drawing Triangle");
		System.out.println("Triangle: Point is : ( " + pointC.getxPoint() + " , " + pointC.getyPoint() + ")");
	}

	/**
	 * @return the pointA
	 */
	public Point getPointA() {
		return pointA;
	}

	/**
	 * @param pointA
	 *            the pointA to set
	 */
	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}

	/**
	 * @return the pointB
	 */
	public Point getPointB() {
		return pointB;
	}

	/**
	 * @param pointB
	 *            the pointB to set
	 */
	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}

	/**
	 * @return the pointC
	 */
	public Point getPointC() {
		return pointC;
	}

	/**
	 * @param pointC
	 *            the pointC to set
	 */
	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Triangle [pointA=" + pointA + ", pointB=" + pointB + ", pointC=" + pointC + "]";
	}

}
