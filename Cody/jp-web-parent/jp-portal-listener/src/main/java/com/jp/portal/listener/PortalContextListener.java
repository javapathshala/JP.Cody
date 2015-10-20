/**
 * File: PortalContextListener.java
 * Date: Apr 13, 2015
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn
 * &amp; gain ideas from it. Its unauthorised use is explicitly prohibited &amp;
 * any
 * addition &amp; removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention
 * orders
 * and to prosecute the authors of any infraction.
 * Visit us at www.javapathshala.com
 **/
package com.jp.portal.listener;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class PortalContextListener implements ServletContextListener {

	private static final Logger LOG = LoggerFactory.getLogger(PortalContextListener.class);
	/**
	 * A {@link PortalApplication} representation of the {@link ServletContext}
	 * that instantiates this {@link ServletContextListener}.
	 * 
	 * @since 1.0
	 */
	private PortalApplication application;
	/**
	 * The {@link PortalRegistration} database connector to use when registering
	 * and unregistering this {@link PortalApplication}.
	 *
	 * @since 1.0
	 */
	private final PortalRegistration registration;

	/**
	 * Instantiates a new {@link PortalContextListener}. The portal
	 * configuration {@link DataSource} is retrieved from the environment
	 * {@link Context}.
	 *
	 * @see Context#lookup(java.lang.String)
	 * @see InitialContext#InitialContext()
	 * @see PortalRegistration#PortalRegistration(javax.sql.DataSource)
	 * @throws NamingException
	 *             if a naming exception is encountered.
	 */
	public PortalContextListener() throws NamingException {
		// Get the initial context
		Context context = Context.class.cast(new InitialContext().lookup("java:comp/env"));
		// Lookup the JDBC data source
		DataSource dataSource = (DataSource) context.lookup("jdbc/PORTALDB");
		// Create a new registration using the data source
		registration = new PortalRegistration(dataSource);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// Get the servlet context that initiated the event
		ServletContext context = sce.getServletContext();
		// Adapt the servlet context in to a portal registration
		setPortalApplication(context);
		try {
			// Register this servlet context
			registration.register(application);
		} catch (SQLException e) {
			LOG.error("Unable to register portal application", e);
		}
	}

	/**
	 * This implementation unregisters the {@link ServletContext} from the
	 * {@link PortalRegistration}.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			// Unregister this servlet context
			registration.unregister(application);
		} catch (SQLException e) {
			LOG.error("Unable to unregister portal application", e);
		}

	}

	/**
	 * Adapts the specified {@link ServletContext} in to a
	 * {@link PortalApplication} object.
	 *
	 * @see ServletContext#getContextPath()
	 * @see ServletContext#getInitParameter(java.lang.String)
	 * @see ServletContext#getServletContextName()
	 * @param context
	 *            the {@link ServletContext} in which this
	 *            {@link ServletContextListener} is running.
	 */
	private void setPortalApplication(ServletContext context) {
		application = new PortalApplication();
		// Set the context path
		application.setContextPath(context.getContextPath());
		// Set the description
		application.setDescription(context.getInitParameter("JP-PORTAL-DESCRIPTION"));
		// Set the display name
		application.setDisplayName(context.getServletContextName());
		// Set the security roles
		application.setSecurityRoles(context.getInitParameter("JP-PORTAL-ROLES"));
		// Set the reporting flag
		application.setReport(context.getInitParameter("JP-PORTAL-REPORT"));
	}

}
