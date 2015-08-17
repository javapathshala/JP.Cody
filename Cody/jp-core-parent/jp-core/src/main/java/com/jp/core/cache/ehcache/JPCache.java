/*
 * File: JPCaching.java
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
package com.jp.core.cache.ehcache;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public interface JPCache {
	
	public void store(String cacheName, String key, String value);

	public Object find(String cacheName, String key);
}
