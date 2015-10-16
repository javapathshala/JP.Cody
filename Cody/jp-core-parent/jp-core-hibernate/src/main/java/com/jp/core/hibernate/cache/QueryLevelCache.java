/*
 * File: QueryLevelCache.java
 * Date: 16-Oct-2015
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

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.jp.core.hibernate.api.DatabaseConnection;
import com.jp.core.hibernate.entities.UserDetails;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class QueryLevelCache {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		try {
			QueryLevelCache queryLevelCache = new QueryLevelCache();
			queryLevelCache.perform();
		} catch (RuntimeException r) {
			r.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	/**
	 * @param databaseConnection
	 */
	@SuppressWarnings("unchecked")
	private void perform() {
		Session session = DatabaseConnection.getSession();
		session.beginTransaction();

		// Two Queries -select as query cache is different
		Query query = session.createQuery("from UserDetails user where user.recordId=2");
		query.setCacheable(true);
		List<UserDetails> users = (List<UserDetails>) query.list();
		printUsers(users);
		session.getTransaction().commit();
		DatabaseConnection.closeSession();

		// opening new session
		Session session2 = DatabaseConnection.getSession();
		session2.beginTransaction();
		Query query2 = session2.createQuery("from UserDetails user where user.recordId=2");
		// set query cache to true here also otherwise query is not cache
		query2.setCacheable(true);
		List<UserDetails> users2 = (List<UserDetails>) query2.list();
		printUsers(users2);
		session2.getTransaction().commit();
		DatabaseConnection.closeSession();


	}

	/**
	 * @param users1
	 */
	private static void printUsers(List<UserDetails> value) {
		System.out.println(value);
	}

}
