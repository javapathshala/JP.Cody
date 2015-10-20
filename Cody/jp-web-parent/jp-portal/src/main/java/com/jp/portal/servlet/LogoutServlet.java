/**
 * File: LogoutServlet.java Date: Apr 13, 2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn &amp; gain ideas from it. Its unauthorised use is
 * explicitly prohibited &amp; any addition &amp; removal of material. If want
 * to suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 **/
package com.jp.portal.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jp.status.base.StatusTracker;

/**
 * A {@link AbstractRedirectServlet} extension that invalidates the
 * {@link HttpSession} before redirecting the request to the appropriate
 * service.
 *
 * @author Dimit Chadha
 */
public class LogoutServlet extends AbstractRedirectServlet {

	private static final long serialVersionUID = -8945968531658913001L;

	/**
	 * {@inheritDoc} This implementation invalidates the {@link HttpSession}
	 * associated with the specified request and redirects the request to the
	 * appropriate portal service.
	 *
	 * @see AbstractRedirectServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 * @see HttpSession#invalidate()
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// JP Status registeraion
		register();
		StatusTracker tracker = new StatusTracker();
		// Get the current session (but do not create one if it does not exist)
		HttpSession session = req.getSession(false);
		// If a current session exists
		if (session != null) {
			// Invalidate the session (to prevent the use of the back button)
			session.invalidate();
		}
		tracker.mark();
		// Redirect the request to the service that requested the logout
		super.doGet(req, resp);
	}
	// </editor-fold>

}
