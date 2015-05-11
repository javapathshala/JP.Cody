/*
 * File: MyHashMap.java Date: 11-May-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.collection.map.custom;

/**
 * @author Dimit Chadha
 */
public class MyHashMap<K, V> {
	private int DEFAULT_CAPACITY = 100;
	KeyValuePair<K, V> mapList[] = new KeyValuePair[DEFAULT_CAPACITY];

	public void put(K key, V value) {
		int index = getHash(key);
		storeValue(index, key, value);
	}

	public V get(K key) {
		int index = getHash(key);
		KeyValuePair<K, V> list = mapList[index];
		return getMatchValue(list, key);
	}

	private int getHash(K key) {
		int hash = key.hashCode();
		return hash % 100;
	}

	private void storeValue(int index, K key, V value) {
		KeyValuePair<K, V> list = mapList[index];

		if (list == null) {
			mapList[index] = new KeyValuePair<K, V>(key, value);
		} else {
			boolean done = false;
			// traverse through the list. if key is found,replace
			while (list.next != null) {
				if (list.getKey().equals(key)) {
					list.setValue(value);
					done = true;
					break;
				}
				list = list.next;
			}
			// add at the end of the list
			if (!done) {
				list.next = new KeyValuePair<K, V>(key, value);
			}
		}
	}

	public void remove(K key) {
		int index = getHash(key);
		KeyValuePair<K, V> list = mapList[index];
		if (list == null)
			return;
		// if only one element is present in the list ,set the index to null
		if (list.getKey().equals(key)) {
			if (list.next == null) {
				mapList[index] = null;
				return;
			}
		}
		KeyValuePair<K, V> prev = null;
		do {
			if (list.key.equals(key)) {
				if (prev == null) {
					list = list.getNext();
				} else {
					prev.next = list.getNext();
				}
				break;
			}
			list = list.next;
		} while (list != null);

		mapList[index] = list;
	}

	/*
	 * find the match value and return , if not found either throw exception or
	 * return null.
	 */
	private V getMatchValue(KeyValuePair<K, V> list, K key) {
		while (list != null) {
			if (list.getKey().equals(key))
				return list.getValue();
			list = list.next;
		}
		return null;
	}
}
