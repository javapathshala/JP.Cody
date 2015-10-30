/*
 * File: RunDependencies.java
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

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author dimit.chadha
 */
public class ExecuteAnn {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("annoatDepend.xml");
		// Shape shape = (Shape) context.getBean("triangle");
		// shape.draw();
		Shape shape1 = (Shape) context.getBean("circle");
		shape1.draw();
		context.close();
	}
}
