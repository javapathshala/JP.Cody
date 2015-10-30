/*
 * File: ShapeService.java
 * Date: 02-Jul-2013
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
package com.jp.spring.api.aop.basic;

/**
 * @author dimit.chadha
 */
public class ShapeService {

	private CircleModel circleModel;

	private TriangleModel triangleModel;

	/**
	 * @return the circleModel
	 */
	public CircleModel getCircleModel() {
		return circleModel;
	}

	/**
	 * @param circleModel
	 *            the circleModel to set
	 */
	public void setCircleModel(CircleModel circleModel) {
		this.circleModel = circleModel;
	}

	/**
	 * @return the triangleModel
	 */
	public TriangleModel getTriangleModel() {
		return triangleModel;
	}

	/**
	 * @param triangleModel
	 *            the triangleModel to set
	 */
	public void setTriangleModel(TriangleModel triangleModel) {
		this.triangleModel = triangleModel;
	}
	
	protected String getAll(int i) {
		return "Get for all "+i;
	}


}
