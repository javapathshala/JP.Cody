/*
 * File: SimulateLog.java
 * Date: 17-Jul-2013
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
package com.jp.spring.api.aop.logging;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author dimit.chadha
 */
public class ExecuteLog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("aop.xml");
		final ExampleBean example = (ExampleBean) context.getBean("exampleBean");
		printContentsOfExample(example);
		context.close();
	}

	/**
	 * @param example
	 */
	private static void printContentsOfExample(ExampleBean example) {
		System.out.println("\n\n=============================================================");
		System.out.println(" Java Pathshala .....");
		System.out.println("=============================================================");
		System.out.println("Name: " + example.getExampleName());
		System.out.println("Version: " + example.getExampleVersion());
		System.out.println("Name (Version): " + example.provideNameAndVersion());
		System.out.println("=============================================================\n\n");
	}
}
