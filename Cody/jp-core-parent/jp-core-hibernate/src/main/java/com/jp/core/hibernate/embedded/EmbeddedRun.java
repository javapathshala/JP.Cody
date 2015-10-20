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
package com.jp.core.hibernate.embedded;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Order;

import com.jp.core.hibernate.api.DatabaseConnection;
import com.jp.core.hibernate.entities.AbstractRecord;

/**
 * @author dimit.chadha
 */
public class EmbeddedRun<T extends AbstractRecord> {

	private Class<T> entity;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		EmbeddedRun<UserInfo> embeddedRun = new EmbeddedRun<UserInfo>(UserInfo.class);
		embeddedRun.process(embeddedRun.getEntity());
	}

	public EmbeddedRun(Class<T> clazz) {
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
	private void process(Class<T> entity) {
		try {
			DatabaseConnection databaseConnection = new DatabaseConnection();
			// displayRecords(databaseConnection, entity);
			insertRecord(databaseConnection, entity);
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
		UserInfo info = new UserInfo();
		info.setUserName("Test-User");
		info.setLastModified(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		Address address = new Address();
		address.setCountry("India");
		address.setState("Delhi");
		info.setHomeAddress(address);

		address = new Address();
		address.setState("Dallas");
		address.setCountry("USA");
		info.setOfficeAddress(address);
		
		
		// list of departments
		Department dep= new Department();
		dep.setLocation("Gu");
//		dep.setDeptId("1");
		dep.setName("ECE");
		info.addDepartment(dep);
		
		dep= new Department();
		dep.setLocation("Hu");
//		dep.setDeptId("2");
		dep.setName("CEC");
		info.addDepartment(dep);
		
		databaseConnection.persist(info);
		displayRecords(databaseConnection, entity);
	}
}
