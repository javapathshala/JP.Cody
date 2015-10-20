/** File: PortalRegistration.java
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * An {@link AbstractPortalDataHelper} extension is responsible for adding and
 * removing a {@link PortalApplication} from a shared data source.
 * 
 * @author Dimit Chadha
 *
 */
public class PortalRegistration extends AbstractPortalDataHelper {

	private static DataSource dataSource;

	static {
		try {
			// Get the initial context
			Context context = Context.class.cast(new InitialContext()
					.lookup("java:comp/env"));
			// Lookup the JDBC data source
			dataSource = (DataSource) context.lookup("jdbc/PORTALDB");
		} catch (NamingException ne) {

		}

	}

	/**
	 * @param source
	 */
	public PortalRegistration(DataSource source) {
		super(source);
	}

	/**
	 * @param source
	 */
	public PortalRegistration() {
		super(dataSource);
	}

	/**
	 * The SQL {@link PreparedStatement} to use for inserting new
	 * {@link PortalApplication} instances in to the portal configuration
	 * database.
	 *
	 * @since 1.2
	 */
	public static final String INSERT_STATEMENT = "INSERT INTO PORTAL_APPLICATIONS (CONTEXT_PATH, DISPLAY_NAME, DESCRIPTION, IS_REPORT, REQUIRED_ROLES) VALUES (?, ?, ?, ?, ?)";
	/**
	 * The SQL {@link PreparedStatement} to use for removing an existing
	 * {@link PortalApplication} instance from the portal configuration
	 * database.
	 *
	 * @since 1.0
	 */
	public static final String DELETE_STATEMENT = "DELETE FROM PORTAL_APPLICATIONS WHERE CONTEXT_PATH=?";

	public static final String SELECT_STATEMENT = "SELECT * FROM PORTAL_APPLICATIONS ORDER BY DISPLAY_NAME";

	/**
	 * Adds the specified {@link PortalApplication} in the portal configuration
	 * database.
	 *
	 * @see #executeStatement(java.lang.String, java.lang.String[])
	 * @param application
	 *            the {@link PortalApplication} to register.
	 * @throws SQLException
	 *             if the portal configuration update fails.
	 */
	public void register(PortalApplication application) throws SQLException {
		// Insert all of the elements in the specified registration
		executeStatement(INSERT_STATEMENT, application.getContextPath(),
				application.getDisplayName(), application.getDescription(),
				application.isReport(), application.getSecurityRoles());
	}

	/**
	 * Removes the specified {@link PortalApplication} from the portal
	 * configuration database.
	 *
	 * @see #executeStatement(java.lang.String, java.lang.String[])
	 * @param application
	 *            the {@link PortalApplication} to register.
	 * @throws SQLException
	 *             if the portal configuration update fails.
	 */
	public void unregister(PortalApplication application) throws SQLException {
		// Insert delete the context path from the specified registration
		executeStatement(DELETE_STATEMENT, application.getContextPath());
	}

	/**
	 * Executes the specified statement with the specified array of parameters.
	 *
	 * @since 1.0
	 * @param statement
	 *            the SQL statement to execute with {@code IN} parameter
	 *            placeholders.
	 * @param parameters
	 *            the parameter values to inject in to the SQL statement.
	 * @throws SQLException
	 *             in the event that a {@link Connection} cannot be established
	 *             using the {@link #dataSource}, a {@link PreparedStatement}
	 *             cannot be created from the {@link Connection}, parameter
	 *             values cannot be set on the {@link PreparedStatement} or in
	 *             the event that more or less than one row is modified.
	 */
	private void executeStatement(String statement, Object... parameters)
			throws SQLException {
		Connection conn = getDataSource().getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(statement);
			try {
				for (int index = 0; index < parameters.length; index++) {
					stmt.setObject(index + 1, parameters[index]);
				}
				int modifiedRows = stmt.executeUpdate();
				if (modifiedRows != 1) {
					throw new SQLException("Unexpected modified row count: "
							+ modifiedRows);
				}
			} finally {
				stmt.close();
			}
		} finally {
			conn.close();
		}
	}

	/**
	 * Gets a {@link List} of all registered {@link PortalApplication}
	 * instances.
	 *
	 * @see Connection#prepareStatement(java.lang.String)
	 * @see DataSource#getConnection()
	 * @see PreparedStatement#executeQuery()
	 * @since 2.0.2
	 * @return a {@link List} of all registered {@link PortalApplication}
	 *         instances.
	 * @throws SQLException
	 *             if a database error occurs.
	 */
	public List<PortalApplication> list() throws SQLException {
		// Instantiate a list for registered applications
		List<PortalApplication> applicationList = new ArrayList<PortalApplication>();
		// Get a connection to the portal registration database
		Connection conn = getDataSource().getConnection();
		try {
			// Create a prepared statement from the SQL
			PreparedStatement stmt = conn.prepareStatement(SELECT_STATEMENT);
			try {
				// Execute the prepared statement query
				ResultSet results = stmt.executeQuery();
				try {
					// While more results exist
					while (results.next()) {
						// Convert the row to a Java object instance
						PortalApplication application = new PortalApplication();
						application.setContextPath(results
								.getString("CONTEXT_PATH"));
						application.setDescription(results
								.getString("DESCRIPTION"));
						application.setDisplayName(results
								.getString("DISPLAY_NAME"));
						application.setSecurityRoles(results
								.getString("REQUIRED_ROLES"));
						application.setReport(results.getBoolean("IS_REPORT"));
						// Add the object to the list
						applicationList.add(application);
					}
				} finally {
					// Close the result set
					results.close();
				}
			} finally {
				// Close the statement
				stmt.close();
			}
		} finally {
			// Close the connection
			conn.close();
		}
		// Return the list of available applications
		return applicationList;
	}
}
