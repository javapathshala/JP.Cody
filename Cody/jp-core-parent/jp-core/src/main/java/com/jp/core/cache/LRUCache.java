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
package com.jp.core.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class LRUCache<K, V> {

	private final int CACHE_SIZE;

	private final int initialCapacity = 16;

	private final float loadFactor = 0.75F;

	private static long accessCount = 0;

	private static long hitCount = 0;

	private LinkedHashMap<K, V> cache;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LRUCache<String, String> lrucache = new LRUCache<String, String>(2);

		// add to cache
		lrucache.put("1", "One");
		lrucache.put("2", "Two");
		lrucache.put("3", "Three");
		lrucache.put("4", "Four");

		System.out.println("##### Checking Cache Hit Count & Access Count ######");
		System.out.println(lrucache.get("2"));
		System.out.println("Access Count " + accessCount);
		System.out.println("Hit Count  " + hitCount);

		// list cache content
		System.out.println("##### Listing all Contents ######");
		for (Map.Entry<String, String> e : lrucache.getAll())
			System.out.println(e.getKey() + " : " + e.getValue());
	}

	public LRUCache(int size) {
		this.CACHE_SIZE = size;
		cache = new LinkedHashMap<K, V>(initialCapacity, loadFactor, true) {

			// (an anonymous inner class)
			private static final long serialVersionUID = -2303882326918858775L;

			@Override
			protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
				boolean ifRemove = this.size() > CACHE_SIZE;
				return ifRemove;
			}
		};
	}

	private Object get(K key) {
		accessCount++;
		if (containsKey(key)) {
			hitCount++;
		}
		Object value = cache.get(key);
		return value;

	}

	private boolean containsKey(K key) {
		return cache.containsKey(key);

	}

	public long getAccessCount() {
		return accessCount;
	}

	public long getHitCount() {
		return hitCount;
	}

	/**
	 * 
	 */

	public synchronized void put(K key, V value) {
		cache.put(key, value);
	}

	/**
	 * Clears the cache.
	 */
	public synchronized void clear() {
		cache.clear();
	}

	/**
	 * Returns the number of used entries in the cache.
	 * 
	 * @return the number of entries currently in the cache.
	 */
	public synchronized int usedEntries() {
		return cache.size();
	}

	/**
	 * Returns a <code>Collection</code> that contains a copy of all cache
	 * entries.
	 * 
	 * @return a <code>Collection</code> with a copy of the cache content.
	 */
	public synchronized Collection<Map.Entry<K, V>> getAll() {
		return new ArrayList<Map.Entry<K, V>>(cache.entrySet());
	}

}
