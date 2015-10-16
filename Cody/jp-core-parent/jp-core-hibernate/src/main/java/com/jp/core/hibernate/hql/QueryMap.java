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
package com.jp.core.hibernate.hql;

import java.util.List;

import org.hibernate.Query;

import com.jp.core.hibernate.api.DatabaseConnection;
import com.jp.core.hibernate.entities.Employee;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class QueryMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		try {
			QueryMap hqlRun = new QueryMap();
			//only one method runs at a time. Exception - nested tx
			//hqlRun.perform(databaseConnection);
			hqlRun.queryMap(databaseConnection);
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

		String tempQuery = "from Employee where employeeId > ?";
		/**
		 * if above query is written as "from Employee where employeeId > 5" or "session.createQuery("from UserDetails  where userId>" +
		 * minUerId);" then it is sql injection as value of 5 is used directly. 5or 1==1 
		 * recommended to use variable instead as below int recordId=2
		 * 
		 */
		Query query = databaseConnection.makeQuery(tempQuery);
		int recordId = 2;
		query.setInteger(0, recordId);

		// For pagination on query

		query.setFirstResult(-1); // skipping first 5 records - start point
		query.setMaxResults(10); // max records - end point

		List<Employee> records = (List<Employee>) query.list();
		System.out.println("Result Size --> " + records.size());

		printUsers(records);
		
		DatabaseConnection.closeSession();

	}

	/**
	 * @param databaseConnection
	 */
	private void queryMap(DatabaseConnection databaseConnection) {
		String tempQuery = "select new map(recordId as key,name as value) from Employee";

		Query query = databaseConnection.makeQuery(tempQuery);
		List<Employee> resultMap = (List<Employee>) query.list();
		// Show all records
		System.out.println("Query Map Results ---->>>");
		System.out.println(resultMap.size());
		System.out.println(resultMap);
		DatabaseConnection.closeSession();
	}

	/**
	 * @param users1
	 */
	private static void printUsers(List<Employee> value) {
		for (int i = 0; i < value.size(); i++) {
			Employee data = value.get(i);
			System.out.println(data);
		}
	}

}
