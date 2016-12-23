/*
 * File: SimpleLRU.java
 * Date: 17-Aug-2015
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
package com.jp.cache.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class SimpleLinkedHashMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> simpleMap = new HashMap<String, String>();
		simpleMap.put("Key-3", "value-3");
		simpleMap.put("Key-1", "value-1");
		simpleMap.put("Key-2", "value-2");
		simpleMap.put("Key-4", "value-4");
		System.out.println(simpleMap);

		simpleMap = null;
		Map<String, String> simpleLink = new LinkedHashMap<String, String>(10, 0.75f, false);
		simpleLink.put("Key-2", "value-2");
		simpleLink.put("Key-4", "value-4");
		simpleLink.put("Key-5", "value-5");
		simpleLink.put("Key-3", "value-3");
		simpleLink.put("Key-1", "value-1");
		System.out.println(simpleLink);

		// Get a set of the entries
		Set<Entry<String, String>> entrySet = simpleLink.entrySet();
		// Get an iterator
		// Iterator<String> i = entrySet.iterator();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		// Display elements
		while (iterator.hasNext()) {
			Map.Entry me = (Map.Entry) iterator.next();
			System.out.print(me.getKey() + ": ");
			System.out.println(me.getValue());
		}
		System.out.println();
	}

}
