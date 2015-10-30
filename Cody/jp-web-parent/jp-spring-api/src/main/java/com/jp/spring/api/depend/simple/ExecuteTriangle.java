/*
 * File: ExecuteTriangle.java
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
package com.jp.spring.api.depend.simple;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class ExecuteTriangle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dependencies.xml");
		Triangle triangle = (Triangle) context.getBean("triangle");
		Triangle triangleAlias = (Triangle) context.getBean("triangle-alias");
		triangle.draw();
		triangleAlias.draw();
		System.out.println(triangle.getType()); // through setter
		System.out.println(triangle.getHeight()); // through constructor
		System.out.println(triangle.getColor()); // through constructor

		System.out.println(triangleAlias.getType()); // through setter
		System.out.println(triangleAlias.getHeight()); // through constructor
		System.out.println(triangleAlias.getColor()); // through constructor
		context.close();
	}

}
