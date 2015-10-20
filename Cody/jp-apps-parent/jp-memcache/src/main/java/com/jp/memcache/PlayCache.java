/*
 * File: PlayCache.java
 * Date: 20-Oct-2015
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
package com.jp.memcache;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class PlayCache {

	MemcachedClient client;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlayCache playCache = new PlayCache();
		try {
			playCache.connectCache();
			playCache.storeDataInCache();
			playCache.getCacheData();
			playCache.updateExpiryTime();
			// playCache.closeConnection();
		} catch (IOException io) {
			System.out.println("Unable to connect to MemCache Server ");
			io.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Connect to MemCache running at localhost:11211
	 */
	private void connectCache() throws IOException {
		client = new XMemcachedClient("localhost", 11211);
		System.out.println("Cache Name -> " + client.getName());
		System.out.println("MemCache Server Name -> " + client.getAvailableServers());
	}

	/**
	 * Store Data in MemCache
	 * 
	 * @throws MemcachedException
	 * @throws InterruptedException
	 * @throws TimeoutException
	 */
	private void storeDataInCache() throws TimeoutException, InterruptedException, MemcachedException {
		// Store the value for one hr[3600 sec] synchronously
		client.set("key-1", 3600, "Value-1");
		client.set("key-2", 3600, "Value-2");
	}

	/**
	 * Retrieve the data from cache
	 * 
	 * @throws TimeoutException
	 * @throws InterruptedException
	 * @throws MemcachedException
	 */
	private void getCacheData() throws TimeoutException, InterruptedException, MemcachedException {
		Object obj = client.get("key-1");
		System.out.println(obj);
	}

	/**
	 * Update expiry value of key
	 * 
	 * @throws MemcachedException
	 * @throws InterruptedException
	 * @throws TimeoutException
	 */
	private void updateExpiryTime() throws TimeoutException, InterruptedException, MemcachedException {
		boolean success = client.touch("key-1", 20);
		System.out.println("Expiry time updated successfully");
		getCacheData();
	}

	/**
	 * Shutdown connection to cache
	 * 
	 * @throws IOException
	 * @throws MemcachedException
	 * @throws InterruptedException
	 * @throws TimeoutException
	 */
	private void closeConnection() throws IOException, TimeoutException, InterruptedException, MemcachedException {
		client.shutdown();
		// Fun to see that connection of application & Memcache only stopped but
		// MemCache is still running via services.msc
		connectCache();
		getCacheData();
	}

}
