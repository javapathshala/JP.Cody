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
package com.jp.core.hibernate.crud;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.jp.core.hibernate.api.DatabaseConnection;
import com.jp.core.hibernate.entities.AbstractRecord;
import com.jp.core.hibernate.entities.Employee;
import com.jp.core.hibernate.entities.UserDetails;

/**
 * @author dimit.chadha
 */
public class CrudRun<T extends AbstractRecord> {

	private Class<T> entity;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		CrudRun<Employee> crudRun = new CrudRun<Employee>(Employee.class);
		//CrudRun<UserDetails> crudRun = new CrudRun<UserDetails>(UserDetails.class);
		crudRun.process(args, crudRun.getEntity());
	}

	public CrudRun(Class<T> clazz) {
		this.entity = clazz;
	}

	public Class<T> getEntity() {
		return entity;
	}

	/**
	 * @param args 
	 * @param crudRun 
	 * 
	 */
	private void process(String[] args, Class<T> entity) {
		try {
			DatabaseConnection databaseConnection = new DatabaseConnection();
			switch (Integer.parseInt(args[0])) {
			// This is for select all records from table Employee
				case 1:
					displayRecords(databaseConnection, entity);
					break;
				// This is for inserting a new Data in the Table Employee
				case 2:
					insertRecord(databaseConnection, entity);
					break;
				// This is for deleting Data from the Table Employee
				case 3:
					deleteRecord(databaseConnection,entity);
					break;
				// This is for updating Data to Table Employee
				case 4:
					updateRecord(databaseConnection,entity);
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

	private <T extends AbstractRecord> void displayRecords(DatabaseConnection databaseConnection, Class<T> entity) {
		List<T> values = databaseConnection.list(null, Order.asc("recordId"), entity);
		int size = (values == null) ? 0 : values.size();
		System.out.println("Total Number of Records --> " + size);

		for (int i = 0; i < size; i++) {
			T data = (T) values.get(i);
			System.out.println(data);
		}
	}

	/**
	 * 
	 */
	private void insertRecord(DatabaseConnection databaseConnection, Class<T> entity) {
//		Employee value = new Employee();
//		insertEmp.setName("Dimit");
//		insertEmp.setAddress("Pragati Appts");
//		insertEmp.setLastModified(new Timestamp(Calendar.getInstance().getTimeInMillis()));
//		insertEmp.setSalary(2014);
		UserDetails value=new UserDetails();
		value.setUserName("Test-User");
		value.setLastModified(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		databaseConnection.persist(value);
		displayRecords(databaseConnection, entity);
	}

	/**
	 * @param databaseConnection
	 */
	private void updateRecord(DatabaseConnection databaseConnection, Class<T> entity) {
		Employee emp = databaseConnection.list(Restrictions.idEq(1), 1, null, Employee.class).get(0);
		emp.setName("Pragati2");
		databaseConnection.saveUpdate(emp);
		displayRecords(databaseConnection, entity);
	}

	/**
	 * @param databaseConnection
	 */
	private void deleteRecord(DatabaseConnection databaseConnection, Class<T> entity) {
		Employee emp = databaseConnection.list(Restrictions.idEq(1), 1, null, Employee.class).get(0);
		databaseConnection.delete(emp);
		displayRecords(databaseConnection, entity);
	}

	// private static <T extends AbstractRecord> T getTypes(Class<T> classType)
	// throws Exception {
	// T type = null;
	// try {
	// Constructor reportConstructor = classType.getConstructor();
	// type = (T) reportConstructor.newInstance();
	// } catch (IllegalAccessException | IllegalArgumentException |
	// InstantiationException | NoSuchMethodException | SecurityException
	// | InvocationTargetException ex) {
	// throw new Exception("", ex);
	// }
	// return type;
	// }
}
