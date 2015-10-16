/*
 * File: NameQueryRun.java
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
package com.jp.core.hibernate.namedQueries;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.jp.core.hibernate.api.DatabaseConnection;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class NameQueryRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		try {
			NameQueryRun nameQueryRun = new NameQueryRun();
			// only one method runs at a time. Exception - nested tx
			nameQueryRun.perform(databaseConnection);
		} catch (RuntimeException r) {
			r.printStackTrace();
		} finally {
			System.exit(0);
		}

	}

	/**
	 * @param databaseConnection
	 */
	private void perform(DatabaseConnection databaseConnection) {
		Session session = DatabaseConnection.getSession();
		session.beginTransaction();

		Query queryById = session.getNamedQuery("NameQueryTable.byId");
		queryById.setInteger(0, 4);
		List<NameQueryTable> users = (List<NameQueryTable>) queryById.list();
		printUsers(users);

		// named native query
		Query queryByName = session.getNamedQuery("NameQueryTable.byName");
		queryByName.setString(0, "User Name-3");
		List<NameQueryTable> users1 = (List<NameQueryTable>) queryByName.list();
		printUsers(users1);

		// Criteria API
		System.out.println("#### Criteria Example ####");
		Criteria criteria = session.createCriteria(NameQueryTable.class);
		criteria.add(Restrictions.eq("userName", "User Name-3")).add(Restrictions.between("userId", 11, 23));
		criteria.addOrder(Order.desc("userId"));
		List<NameQueryTable> users2 = (List<NameQueryTable>) criteria.list();
		printUsers(users2);

		// Projections - used for aggreation functions or group
		System.out.println("#### Projection Example ####");
		Criteria criteria1 = session.createCriteria(NameQueryTable.class).setProjection(Projections.max("userId"));
		List<Integer> users3 = (List<Integer>) criteria1.list();
		System.out.println(users3.get(0));

		session.getTransaction().commit();
		DatabaseConnection.closeSession();

	}

	/**
	 * @param 
	 */
	private static void printUsers(List<NameQueryTable> users1) {
		System.out.println(users1.size());
		for (NameQueryTable usersList1 : users1) {
			System.out.println(usersList1);
		}
	}
}
