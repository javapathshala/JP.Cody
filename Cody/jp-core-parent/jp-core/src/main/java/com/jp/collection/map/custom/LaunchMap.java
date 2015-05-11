/*
 * File: LaunchMap.java
 * Date: 11-May-2015
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
package com.jp.collection.map.custom;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class LaunchMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>();
		map.put(1, 1);
		map.put(2, 2);
		map.put(201, 201);
		map.put(201, 203);
		System.out.println("get value is " + map.get(1));
		System.out.println("get value is " + map.get(201));
		System.out.println("get value is " + map.get(2));
		//map.remove(1);
		System.out.println("After deletion " + map.get(1));
		System.out.println("get value is " + map.get(201));

	}

}
