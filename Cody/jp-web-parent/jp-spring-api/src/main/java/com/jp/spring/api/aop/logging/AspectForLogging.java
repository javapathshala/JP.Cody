/*
 * File: LoggingAspect.java
 * Date: 17-Jul-2013
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
package com.jp.spring.api.aop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author dimit.chadha
 */
@Aspect
public class AspectForLogging {

	@Pointcut("within(com.jp.spring.api.aop.logging.ExampleBean)")
	public void logMethods() {

	}

	/**
	 * Log method entry.
	 * 
	 * @param joinPoint
	 */
	@Before("logMethods()")
	public void logEntry(final JoinPoint joinPoint) {
//		/log("Entering method " + joinPoint.getSignature().getName() + "...");
		log("Entering method " + joinPoint.toShortString() + "...");
	}

	/**
	 * Log method exit.
	 * 
	 * @param joinPoint
	 */
	@After("logMethods()")
	public void logExit(final JoinPoint joinPoint) {
		log("Exiting method " + joinPoint.getSignature().getName() + ".");
	}

	/**
	 * Log method exit after returning.
	 * 
	 * @param joinPoint
	 */
	@AfterReturning("logMethods()")
	public void logExitAfterReturn(final JoinPoint joinPoint) {
		log("Exiting method (after return) " + joinPoint.getSignature().getName() + ".");
	}

	/**
	 * "Log" the provided String.
	 * 
	 * @param messageToLog
	 *            String to be logged.
	 */
	public static void log(final String messageToLog) {
		System.out.println(messageToLog);
	}
}
