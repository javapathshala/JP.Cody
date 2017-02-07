/*
 * File: Point.java Date: 22-Aug-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.patterns.structure.flyweight;

/**
 * @author dchadha
 */
public class Point {
	int pointX;
	int pointY;

	/**
	 * @param i
	 * @param j
	 */
	public Point(int pointX, int pointY) {
		this.pointX = pointX;
		this.pointY = pointY;
	}

	public int getPointX() {
		return pointX;
	}

	public void setPointX(int pointX) {
		this.pointX = pointX;
	}

	public int getPointY() {
		return pointY;
	}

	public void setPointY(int pointY) {
		this.pointY = pointY;
	}

	@Override
	public String toString() {
		return "Point [pointX=" + pointX + ", pointY=" + pointY + "]";
	}

}
