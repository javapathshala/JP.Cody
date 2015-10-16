/*
 * File: SecondLevelCache.java
 * Date: 15-Oct-2015
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
package com.jp.core.hibernate.cache;

import org.hibernate.Session;

import com.jp.core.hibernate.api.DatabaseConnection;
import com.jp.core.hibernate.entities.UserDetails;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class SecondLevelCache {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		try {
			SecondLevelCache secondLevelCache = new SecondLevelCache();
			secondLevelCache.perform();
		} catch (RuntimeException r) {
			r.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	/**
	 * @param databaseConnection
	 */
	private void perform() {
		Session session = DatabaseConnection.getSession();
		session.beginTransaction();

		UserDetails value = (UserDetails) session.get(UserDetails.class, 2);
		printUsers(value);
		session.getTransaction().commit();
		DatabaseConnection.closeSession();

		session = DatabaseConnection.getSession();
		session.beginTransaction();

		UserDetails value1 = (UserDetails) session.get(UserDetails.class, 2);
		printUsers(value1);
		session.getTransaction().commit();
		DatabaseConnection.closeSession();

	}

	/**
	 * @param users1
	 */
	private static void printUsers(UserDetails value) {
		System.out.println(value);
	}

}
