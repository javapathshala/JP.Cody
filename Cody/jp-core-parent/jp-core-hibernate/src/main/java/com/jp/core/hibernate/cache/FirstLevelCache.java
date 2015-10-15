/*
 * File: FirstLevelCache.java
 * Date: 08-Sep-2015
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
import com.jp.core.hibernate.entities.Employee;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class FirstLevelCache {
	public static void main(String[] args) {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		try {
			FirstLevelCache firstLevelCache = new FirstLevelCache();
			Session session = DatabaseConnection.getSession();
			firstLevelCache.perform(databaseConnection, session);
		} catch (RuntimeException r) {
			r.printStackTrace();
		} finally {
			DatabaseConnection.closeSession();
			System.exit(0);
		}

	}

	/**
	 * @param session 
	 * 
	 */
	private void perform(DatabaseConnection databaseConnection, Session session) {
		session.beginTransaction();
		Employee record = (Employee) session.get(Employee.class, 4);
		if (record == null) {
			System.out.println("No Records Found");
			System.out.println("Trying once again...");
		} else {
			System.out.println(record);
			System.out.println("######");
			System.out.println("There will be only one select statement,Until session is closed. So by default we have session persist");
			Employee record1 = (Employee) session.get(Employee.class, 4);
			System.out.println("######");
			System.out.println(record1);
		}
		session.getTransaction().commit();
	}

}
