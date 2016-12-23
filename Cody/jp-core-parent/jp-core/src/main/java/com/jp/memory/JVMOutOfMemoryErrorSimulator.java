/*
 * File: JVMOutOfMemoryErrorSimulator.java
 * Date: 15-Jul-2013
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
package com.jp.memory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dimit.chadha
 */
public class JVMOutOfMemoryErrorSimulator {

	private final static int NB_ITERATIONS = 500000;

	// ~1 KB data footprint
	private final static String LEAKING_DATA_PREFIX = "datadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadata";

	// Map used to stored our leaking String instances
	private static Map<String, String> leakingMap;

	static {
		leakingMap = new HashMap<String, String>();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("JVM OutOfMemoryError Simulator 1.0");
		System.out.println("Author: Pierre-Hugues Charbonneau");
		System.out.println("http://javaeesupportpatterns.blogspot.com/");
		try {

			for (int i = 0; i < NB_ITERATIONS; i++) {

				String data = LEAKING_DATA_PREFIX + i;

				// Add data to our leaking Map data structure...
				leakingMap.put(data, data);

			}

		} catch (Throwable any) {
			if (any instanceof java.lang.OutOfMemoryError) {
				System.out.println("OutOfMemoryError triggered! " + any.getMessage() + " [" + any + "]");

			} else {
				System.out.println("Unexpected Exception! " + any.getMessage() + " [" + any + "]");
			}
		}

		System.out.println("simulator done!");
	}
}
