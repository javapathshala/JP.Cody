/** File: AbstractPortalDataHelper.java
 *  Date: Apr 13, 2015
 *
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn 
 * &amp; gain ideas from it. Its unauthorised use is explicitly prohibited &amp; any 
 * addition &amp; removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention orders
 * and to prosecute the authors of any infraction.
 * 
 * Visit us at www.javapathshala.com
 **/
package com.jp.portal.listener;

import javax.sql.DataSource;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class AbstractPortalDataHelper {

	/**
	 * The {@link DataSource} to use for executing SQL statements.
	 *
	 * @since 1.1
	 */
	private final DataSource dataSource;

	
	
	/**
	 * Creates a new {@link AbstractPortalDataHelper} connected to the specified
	 * {@link DataSource}.
	 *
	 * @param source
	 *            the {@link DataSource} to use for executing SQL statements.
	 */
	public AbstractPortalDataHelper(DataSource source) {
		this.dataSource = source;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

}
