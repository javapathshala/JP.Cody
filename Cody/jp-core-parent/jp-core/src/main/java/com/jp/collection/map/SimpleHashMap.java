/*
 * File: SimpleHashMap.java
 * Date: 07-Aug-2015
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
package com.jp.collection.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class SimpleHashMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> hashMap = new HashMap<String, String>();
		// Create a key for the map, keep the strong reference
		String strongRef = new String("key");
		hashMap.put(strongRef, "Keyvalue");
		// Run the GC and check if the key is still there.
		System.gc();
		System.out.println(hashMap.get("key"));

		// Now, null out the strong reference and try again the same above.
		strongRef = null;
		System.gc();
		System.out.println(hashMap.get("key"));

	}

}