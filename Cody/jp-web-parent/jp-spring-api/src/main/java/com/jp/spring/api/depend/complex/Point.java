/*
 * File: Point.java
 * Date: 09-May-2013
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
package com.jp.spring.api.depend.complex;

/**
 * @author dimit.chadha
 */
public class Point {

	int xPoint;

	int yPoint;

	/**
	 * @return the xPoint
	 */
	public int getxPoint() {
		return xPoint;
	}

	/**
	 * @param xPoint
	 *            the xPoint to set
	 */
	public void setxPoint(int xPoint) {
		this.xPoint = xPoint;
	}

	/**
	 * @return the yPoint
	 */
	public int getyPoint() {
		return yPoint;
	}

	/**
	 * @param yPoint
	 *            the yPoint to set
	 */
	public void setyPoint(int yPoint) {
		this.yPoint = yPoint;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Point [xPoint=" + xPoint + ", yPoint=" + yPoint + "]";
	}

}
