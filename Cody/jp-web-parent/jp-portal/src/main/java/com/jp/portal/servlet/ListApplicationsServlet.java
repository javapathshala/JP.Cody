/**
 * File: ListApplicationsServlet.java Date: Apr 13, 2015 This source code is
 * part of Java Pathshala-Wisdom Being Shared. This program is protected by
 * copyright law but you are authorise to learn &amp; gain ideas from it. Its
 * unauthorised use is explicitly prohibited &amp; any addition &amp; removal of
 * material. If want to suggest any changes, you are welcome to provide your
 * comments on GitHub Social Code Area. Its unauthorised use gives Java
 * Pathshala the right to obtain retention orders and to prosecute the authors
 * of any infraction. Visit us at www.javapathshala.com
 **/
package com.jp.portal.servlet;

import java.io.IOException;
import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jp.portal.listener.PortalApplication;
import com.jp.portal.listener.PortalRegistration;

/**
 * @author Dimit Chadha
 */
public class ListApplicationsServlet extends HttpServlet {

	private static final long serialVersionUID = -2481560545215162021L;

	/**
	 * The {@link PortalRegistrar} to use when retrieving registered
	 * {@link PortalApplication}.
	 *
	 * @since 1.3
	 */
	private final PortalRegistration registration = new PortalRegistration();

	/**
	 * {@inheritDoc} This implementation loads the {@link List} of
	 * {@link PortalApplication} instances the {@link #registration} and sets it
	 * as a request attribute to the specified {@link HttpServletRequest}.
	 *
	 * @since 1.3
	 */
	@Override
	protected final void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Get the list of portal applications
			List<PortalApplication> list = registration.list();
			// Instantiate an iterator for the list of portal applications
			Iterator<PortalApplication> iterator = list.iterator();
			// While more elements exist
			while (iterator.hasNext()) {
				PortalApplication next = iterator.next();
				// If the next element is not included or the user is not
				// authorized
				if (!isUserAuthorized(req, next)) {
					// Remove the element
					iterator.remove();
				}
			}
			// Set the portal application list as a request attribute
			req.setAttribute("applicationList", list);
			// Include the applications page content
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/applications.jsp").include(req, resp);
		} catch (SQLException e) {
			throw new ServerException("Unable to load portal application list", e);
		}
	}

	/**
	 * Indicates if the remote user associated with the specified request is
	 * authorized to access the specified application.
	 *
	 * @see HttpServletRequest#isUserInRole(java.lang.String)
	 * @see PortalApplication#getSecurityRoles()
	 * @since 1.0
	 * @param req
	 *            the {@link HttpServletRequest} used to identify the remote
	 *            user and user authorizations.
	 * @param application
	 *            the {@link PortalApplication} to test for authorization.
	 * @return {@code true} if the remote user associated with the specified
	 *         {@link HttpServletRequest} authorized to access the specified
	 *         {@link PortalApplication}, {@code false} otherwise.
	 */
	private boolean isUserAuthorized(HttpServletRequest req, PortalApplication application) {
		boolean authorized = false;
		// Get the list of roles defined by the portal module
		String securityRoles = application.getSecurityRoles();
		// If the list of roles is not null
		if (securityRoles != null) {
			// Split the list of roles from its CSV
			String[] array = securityRoles.split(",");
			// For each role defined by the portal module
			for (String role : array) {
				// If the user has the apporpriate role
				authorized |= req.isUserInRole(role.trim());
			}
		}
		// Indicate the user is not authorized
		return authorized;
	}
}
