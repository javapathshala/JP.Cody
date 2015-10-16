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
package com.jp.core.hibernate.detached;

import org.hibernate.criterion.Restrictions;

import com.jp.core.hibernate.api.DatabaseConnection;
import com.jp.core.hibernate.entities.Employee;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class DetachedPersistence {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		try {
			DetachedPersistence secondLevelCache = new DetachedPersistence();
			secondLevelCache.perform(databaseConnection);
		} catch (RuntimeException r) {
			r.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	/**
	 * @param databaseConnection 
	 * @param databaseConnection
	 */
	private void perform(DatabaseConnection databaseConnection) {

		Employee record = databaseConnection.list(Restrictions.idEq(4), 1, null, Employee.class).get(0);
		printUsers(record);
		DatabaseConnection.closeSession();
		
		// now value object is detached as session is closed
		// before doing the next update in another session , value object value can change 
		// so next update first checks whether value object is changed or not even if detached
		//set value select-before-update="true" in hbm file or through annotation
		
		record.setName("detached name");
		
		
		databaseConnection.saveUpdate(record);
		DatabaseConnection.closeSession();
		
		//again list the updated value
		System.out.println("Updated value");
		Employee record1 = databaseConnection.list(Restrictions.idEq(4), 1, null, Employee.class).get(0);
		printUsers(record1);
		DatabaseConnection.closeSession();

	}

	/**
	 * @param users1
	 */
	private static void printUsers(Employee value) {
		System.out.println(value);
	}

}
