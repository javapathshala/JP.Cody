/*
 * File: ExecuteBasicAop.java
 * Date: 30-Oct-2015
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
package com.jp.spring.api.aop.basic;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class ExecuteBasicAop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("aop.xml");

		ShapeService shapeService = context.getBean("shapeService", ShapeService.class);
		System.out.println(shapeService.getCircleModel().getName());
		// System.out.println(shapeService.getTriangleModel().getName());
		System.out.println(shapeService.getAll(3));
		context.close();

	}

}
