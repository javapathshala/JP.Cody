/*
 * File: DatabaseConnection.java
 * Date: 03-Sep-2015
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
package com.jp.core.hibernate.api;

import java.io.PrintStream;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class DatabaseConnection extends AbstractDatabaseService {

	private static final Logger log = LoggerFactory.getLogger(DatabaseConnection.class);
	static {
		try {
			Configuration configuration = new Configuration().configure();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			log.error("Exception in Configuration " + ex);
			ex.printStackTrace();
		System.exit(0);
		
		}
	}

	// /**
	// * @return
	// */
	// public static SessionFactory getSessionFactory() {
	// return sessionFactory;
	// }

	public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

	/**
	 * @return
	 * @throws HibernateException
	 */
	public static Session getSession() throws HibernateException {
		Session s = (Session) session.get();
		// Open a new Session, if this Thread has none yet
		if (s == null) {
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}

	/**
	 * @throws HibernateException
	 */
	public static void closeSession() throws HibernateException {
		Session s = (Session) session.get();
		session.set(null);
		session.remove();
		if (s != null)
			s.close();
		System.out.println("Session Closed");
	}
}
