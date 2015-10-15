/*
 * File: BasicRun.java
 * Date: 16-Apr-2013
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
package com.jp.core.hibernate.run;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.jp.core.hibernate.api.DatabaseConnection;
import com.jp.core.hibernate.entities.Employee;

/**
 * @author dimit.chadha
 */
public class CrudRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CrudRun crudRun = new CrudRun();
		crudRun.process(args);
	}

	/**
	 * @param args 
	 * 
	 */
	private void process(String[] args) {
		try {

			DatabaseConnection databaseConnection = new DatabaseConnection();
			switch (Integer.parseInt(args[0])) {
			// This is for select all records from table Employee
				case 1:
					displayRecords(databaseConnection);
					break;
				// This is for inserting a new Data in the Table Employee
				case 2:
					insertRecord(databaseConnection);
					break;
				// This is for deleting Data from the Table Employee
				case 3:
					deleteRecord(databaseConnection);
					break;
				// This is for updating Data to Table Employee
				case 4:
					updateRecord(databaseConnection);
					break;
				default:
					System.out.println("Enter Something");
					break;
			}
		} catch (RuntimeException r) {
			r.printStackTrace();
		} finally {
			DatabaseConnection.closeSession();
			System.exit(0);
		}
	}

	private void displayRecords(DatabaseConnection databaseConnection) {
		List<Employee> employees = databaseConnection.list(null, Order.asc("name"), Employee.class);// (List<Employee>)
																									// databaseConnection.createQuery("FROM Employee").list();
		System.out.println("Total Number of Records --> " + employees.size());
		for (int i = 0; i < employees.size(); i++) {
			Employee emp = (Employee) employees.get(i);
			System.out.println(emp);
		}
	}

	/**
	 * 
	 */
	private void insertRecord(DatabaseConnection databaseConnection) {
		Employee insertEmp = new Employee();
		insertEmp.setName("Dimit");
		insertEmp.setAddress("Pragati Appts");
		insertEmp.setLastModified(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		insertEmp.setSalary(2014);
		databaseConnection.persist(insertEmp);
		displayRecords(databaseConnection);
	}

	/**
	 * @param databaseConnection
	 */
	private void updateRecord(DatabaseConnection databaseConnection) {
		Employee emp = databaseConnection.list(Restrictions.idEq(1), 1, null, Employee.class).get(0);
		emp.setName("Pragati2");
		databaseConnection.saveUpdate(emp);
		displayRecords(databaseConnection);
	}

	/**
	 * @param databaseConnection
	 */
	private void deleteRecord(DatabaseConnection databaseConnection) {
		Employee emp = databaseConnection.list(Restrictions.idEq(1), 1, null, Employee.class).get(0);
		databaseConnection.delete(emp);
		displayRecords(databaseConnection);
	}

}
