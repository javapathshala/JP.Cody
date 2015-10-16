/*
 * File: AbstractDatabase.java
 * Date: 01-Sep-2015
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

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jp.core.hibernate.entities.AbstractRecord;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public abstract class AbstractDatabaseService {

	private static final Logger log = LoggerFactory.getLogger(AbstractDatabaseService.class);

	// The name of the database property that represents a primary key of the
	// table.
	protected static final String RECORD_ID = "recordId";

	// The name of the database property that that sorts the table data.
	protected static final String LAST_MODIFIED = "lastModified";

	/**
	 * The {@link SessionFactory} to be used to generate Hibernate
	 * {@link Session} instances.
	 *
	 * @since 1.0
	 */
	protected static SessionFactory sessionFactory;

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Gets a singular object instance based on the specified {@link Criterion}.
	 *
	 *
	 * @param <T> the type of object to retrieve.
	 * @param crit the {@link Criterion} used to limit results.
	 * @param order the (optional) {@link Order} in which results should be
	 * sorted. If specified, the a non-unique {@link Criterion} may be specified
	 * and the first element of the resulting list will be returned.
	 * @param clazz the entity {@link Class} of object to retrieve.
	 * @return a singular object instance based on the specified
	 * {@link Criterion}.
	 * @since 1.0
	 */
	public <T> T get(Criterion crit, Order order, Class<T> clazz) {
		log.debug(String.format("Getting singular %s instance", clazz.getSimpleName()));
		// Create a local variable for tracking the return value
		T returnValue = null;
		try {
			// Get a database session
			Session session = getSessionFactory().getCurrentSession();
			try {
				// Begin a database transaction
				session.beginTransaction();
				// Create a criteria for all control types
				Criteria criteria = session.createCriteria(clazz);
				// Limit the results to only those controls which match the
				// criterion
				criteria.add(crit);
				// If the order is defined
				if (order != null) {
					// Order the results accordingly
					criteria.addOrder(order);
					// Limit the maximum number of results
					criteria.setMaxResults(1);
				}
				// Assign the unique result of the criteria to the return value
				returnValue = clazz.cast(criteria.uniqueResult());
			} finally {
				// Close the session
				session.close();
			}
			// Update the transaction tracker to use the success tracker
		} catch (HibernateException e) {
			log.error(String.format("Unable to get singular %s instance", clazz.getSimpleName()), e);
		} finally {
			// Mark the status tracker
		}
		// Return the list of controls
		return returnValue;
	}

	/**
	 * Gets a {@link List} of object instances based on the specified
	 * {@link Criterion}.
	 *
	 * @param <T> the type of object to retrieve.
	 * @param crit the {@link Criterion} used to limit results.
	 * @param order the (optional) {@link Order} in which results should be
	 * sorted.
	 * @param clazz the entity {@link Class} of objects to retrieve.
	 * @return a {@link List} of object instances based on the specified
	 * {@link Criterion}.
	 *
	 * @since 1.0
	 */
	public <T> List<T> list(Criterion crit, Order order, Class<T> clazz) {
		return list(crit, 0, order, clazz);
	}

	/**
	 * Gets a singular object instance based on the specified {@link Criterion}.
	 *
	 *
	 *
	 * @param <T> the type of object to retrieve.
	 * @param crit the {@link Criterion} used to limit results.
	 * @param maxResults max results desired out of query
	 * @param order the (optional) {@link Order} in which results should be
	 * sorted. If specified, the a non-unique {@link Criterion} may be specified
	 * and the first element of the resulting list will be returned.
	 * @param clazz the entity {@link Class} of object to retrieve.
	 * @return a singular object instance based on the specified
	 * {@link Criterion}.
	 *
	 * @since 1.0
	 */
	public <T> List<T> list(Criterion crit, int maxResults, Order order, Class<T> clazz) {
		log.debug(String.format("Listing %s instances", clazz.getSimpleName()));
		// Get the failure transaction tracker
		// Create a local variable for tracking the return value
		List<T> returnValue = null;
		try {
			// Get a database session
			Session session = getSessionFactory().getCurrentSession();
			try {
				// Begin a database transaction
				Transaction tx=session.beginTransaction();
				// Create a criteria for all control types
				Criteria criteria = session.createCriteria(clazz);
				// If a criteria is defined
				if (crit != null) {
					// Limit the results to only those controls which match the
					// criterion
					criteria.add(crit);
				}
				// If the order is defined
				if (order != null) {
					// Order the results accordingly
					criteria.addOrder(order);
				}
				if (maxResults > 0) {
					criteria.setMaxResults(maxResults);
				}
				// Get the list of controls
				returnValue = criteria.list();
				markTransaction(tx);
			} finally {
				// Close the session
				//session.close();
				
			}
			// Update the transaction tracker to use the success tracker
		} catch (HibernateException e) {
			log.error(String.format("Unable to list %s instances", clazz.getSimpleName()), e);
		} finally {
			// Mark the status tracker
		}
		// Return the list of controls
		return returnValue;
	}

	/**
	 * Gets a {@link List} of object instances based on the specified
	 * {@link Criterion}.
	 *
	 * @param <T> the type of object to retrieve.
	 * @param crit the {@link Criterion} used to limit results.
	 * @param order the (optional) {@link Order} list in which results should be
	 * sorted.
	 * @param clazz the entity {@link Class} of objects to retrieve.
	 * @return a {@link List} of object instances based on the specified
	 * {@link Criterion}.
	 *
	 * @since 1.0
	 */
	public <T> List<T> listOrder(Criterion crit, List<Order> order, Class<T> clazz) {
		log.debug(String.format("Listing %s instances", clazz.getSimpleName()));
		// Get the failure transaction tracker
		// Create a local variable for tracking the return value
		List<T> returnValue = null;
		try {
			// Get a database session
			Session session = getSessionFactory().getCurrentSession();
			try {
				// Begin a database transaction
				session.beginTransaction();
				// Create a criteria for all control types
				Criteria criteria = session.createCriteria(clazz);
				// If a criteria is defined
				if (crit != null) {
					// Limit the results to only those controls which match the
					// criterion
					criteria.add(crit);
				}
				// If the order is defined
				if (order != null) {
					for (Order o : order) {
						// Order the results accordingly
						criteria.addOrder(o);
					}
				}
				// Get the list of controls
				returnValue = criteria.list();
			} finally {
				// Close the session
				session.close();
			}
			// Update the transaction tracker to use the success tracker
		} catch (HibernateException e) {
			log.error(String.format("Unable to list %s instances", clazz.getSimpleName()), e);
		} finally {
			// Mark the status tracker
		}
		// Return the list of controls
		return returnValue;
	}

	/**
	 * Gets the {@link List} of primary key values for all active
	 * {@link AbstractRecord} instances.
	 *
	 *
	 * @param <T> the type of {@link AbstractRecord} to retrieve.
	 * @param crit the {@link Criterion} used to limit results.
	 * @param order the (optional) {@link Order} in which results should be
	 * sorted.
	 * @param clazz the entity {@link Class} of objects to retrieve.
	 * @return a {@link List} of object instances based on the specified
	 * {@link Criterion}.
	 *
	 * @since 1.0
	 */
	public <T extends AbstractRecord> List<String> listKeys(Criterion crit, Order order, Class<T> clazz) {
		log.debug(String.format("Listing active %s keys", clazz.getSimpleName()));
		// Get the failure transaction tracker
		// Create a local variable for tracking the return value
		List<String> returnValue = null;
		try {
			// Get a database session
			Session session = sessionFactory.getCurrentSession();
			try {
				// Begin a database transaction
				session.beginTransaction();
				// Create a criteria for all control types
				Criteria criteria = session.createCriteria(clazz);
				// If a criteria was specified
				if (crit != null) {
					// Limit the results to only those records which are enabled
					criteria.add(crit);
				}
				// Limit the results to only the primary key fields
				criteria.setProjection(Projections.id());
				// Get the list of primary keys
				returnValue = criteria.list();
			} finally {
				// Close the session
				session.close();
			}
			// Update the transaction tracker to use the success tracker
		} catch (HibernateException e) {
			log.error(String.format("Unable to list active %s keys", clazz.getSimpleName()), e);
		} finally {
			// Mark the status tracker
		}
		// Return the list of controls
		return returnValue;
	}

	/**
	 * Insert a new record into database
	 *
	 * @param <T> the type of {@link AbstractRecord} in context.
	 * @param record the entity {@link Class} of objects in context.
	 * @since 1.0
	 */
	public <T extends AbstractRecord> void persist(T record) {
		// Get a database session
		Session session = getSessionFactory().getCurrentSession();
		try {
			// Begin a database transaction
			Transaction transaction = session.beginTransaction();
			try {
				// Create the record
				session.persist(record);
				// Commit the transaction
				transaction.commit();
			} finally {
				markTransaction(transaction);
			}
		} finally {
			// If the session is open
			if (session.isOpen()) {
				// Close the session
				session.close();
			}
		}
	}

	/**
	 * Update an existing record into database.
	 *
	 * @param <T> the type of {@link AbstractRecord} in context.
	 * @param record the entity {@link Class} of objects in context.
	 * @since 1.0
	 */
	public <T extends AbstractRecord> void saveUpdate(T record) {
		// Get a database session
		Session session = getSessionFactory().getCurrentSession();
		try {
			// Begin a database transaction
			Transaction transaction = session.beginTransaction();
			try {
				// Create the record
				session.saveOrUpdate(record);
				// Commit the transaction
				transaction.commit();
			} finally {
				markTransaction(transaction);
			}
		} finally {
			// If the session is open
			if (session.isOpen()) {
				// Close the session
				//session.close();
			}
		}
	}

	/**
	 * Delete an existing record
	 *
	 * @param <T> the type of {@link AbstractRecord} in context.
	 * @param record the entity {@link Class} of objects in context.
	 * @since 1.0
	 */
	public <T extends AbstractRecord> void delete(T record) {
		// Get a database session
		Session session = getSessionFactory().getCurrentSession();
		try {
			// Begin a database transaction
			Transaction transaction = session.beginTransaction();
			try {
				// Delete the record
				session.delete(record);
				// Commit the transaction
				transaction.commit();
			} finally {
				markTransaction(transaction);
			}
		} finally {
			// If the session is open
			if (session.isOpen()) {
				// Close the session
				session.close();
			}
		}
	}

	/**
	 * Mark in context transaction for status
	 *
	 * @param transaction
	 */
	protected void markTransaction(Transaction transaction) {
		// If the transaction was committed
		if (transaction.wasCommitted()) {
			// Mark the success tracker
			// getSuccessTracker().mark();
			log.info("Trnx completed sucessfully");
		} else {
			log.error("Error in refreshing..");
			// Mark the failure tracker
			// getFailureTracker().mark();
			// Roll back the transaction
			transaction.rollback();
		}
	}

}
