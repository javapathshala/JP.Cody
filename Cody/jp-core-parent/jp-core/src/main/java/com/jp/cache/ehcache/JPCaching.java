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
package com.jp.cache.ehcache;

//import net.sf.ehcache.Cache;
//import net.sf.ehcache.CacheManager;
//import net.sf.ehcache.Element;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class JPCaching {//implements JPCache {

//	private static CacheManager manager;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		manager = CacheManager.newInstance("src/main/java/com/jp/core/cache/ehcache/ehcache.xml");
//
//		String[] cacheNames = CacheManager.getInstance().getCacheNames();
//		System.out.println("Cache Avaliable are --> " + cacheNames.toString());
//		JPCaching jpcache = new JPCaching();
//
//		System.out.println("Pushing System of Records to cache");
//		jpcache.store("demo", "key1", "value1");
//		jpcache.store("demo", "key2", "value2");
//		jpcache.store("demo", "key3", "value3");
//
//		System.out.println("Reading from cache for Key - " + jpcache.find("demo", "key3"));
//		manager.shutdown();
	}

//	@Override
	public void store(String cacheName, String key, String value) {
//		Cache testCache = manager.getCache(cacheName);
//		testCache.putIfAbsent(new Element(key, value));
	}

	//@Override
//	public Object find(String cacheName, String key) {
//		Cache cache = manager.getCache(cacheName);
//		System.out.println("Total elements in cache -->" + cache.getSize());
//		Element element = cache.get(key);
//		return element != null ? element.getObjectValue() : "No Value found in cache";
//	}

}
