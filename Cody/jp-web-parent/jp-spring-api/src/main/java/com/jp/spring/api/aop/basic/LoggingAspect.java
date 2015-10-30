/*
 * File: LoggingAspect.java
 * Date: 02-Jul-2013
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

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

// import org.aspectj.lang.JoinPoint;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Before;
// import org.aspectj.lang.annotation.Pointcut;

/**
 * @author dimit.chadha
 */
@Aspect
public class LoggingAspect {

	@Before("execution(public String com.jp.spring.api.aop.basic.TriangleModel.getName())")
	public void LoggingAdvice() {
		System.out.println("Advice Run. Get Method is called");
	}

	// // @Before("execution( public  * get*())")
	// @Before("execution( * com.jp.spring.api.aop.basic.*.get*(..))")
	// public void LoggingAdviceCommon() {
	// System.out.println("Advice Run for all getters in Applications");
	// }

	// one point - multiple advice.. do not repeat configuration rather define
	// point

	@Pointcut("execution( * com.jp.spring.api.aop.basic.*.get*(..))")
	public void allGettters() {
		// dumpy methods
	}

	@Before("allGettters()")
	public void LoggingAdviceForAll() {
		System.out.println("Advice Run for all getters in Applications");
	}

	@Before("allGettters()")
	public void LoggingAdviceForAll2() {
		System.out.println("Advice Run2 for all getters in Applications");
	}

	@Pointcut("within(com.jp.spring.api.aop.basic.*)")
	public void allMethods() {
		// dumpy methods
	}

	@Before("allMethods()")
	public void LoggingAdviceForAfter() {
		System.out.println("Before Advice Run for all methods");
	}

	@Before("allMethods()")
	public void LoggingAdviceJointPoint(JoinPoint joinPoint) {
		System.out.println("Before Advice Run for all methods");
		System.out.println(joinPoint.getSignature());
		System.out.println(joinPoint.toLongString());
		System.out.println(joinPoint.toString());
		System.out.println(joinPoint.getTarget());
	}
}
