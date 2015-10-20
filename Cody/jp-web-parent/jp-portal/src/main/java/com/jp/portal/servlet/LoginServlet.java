/**
 * File: LoginServlet.java Date: Apr 13, 2015 This source code is part of Java
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
 * A {@link AbstractRedirectServlet} extension that associates an
 * {@link HttpSession} to a remote user before redirecting the request to the
 * appropriate service.
 * 
 * @author Dimit Chadha
 */
public class LoginServlet extends AbstractRedirectServlet {

	private static final long serialVersionUID = 8787534904466005060L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// jp-status registeration
		register();
		StatusTracker tracker = new StatusTracker();
		tracker.mark();
		// Get the current session
		HttpSession session = req.getSession();
		// Record the login request
		SessionAuditor.INSTANCE.put(session.getId(), req.getRemoteUser());
		// Redirect the request to the service that requested the login
		super.doGet(req, resp);
	}
}
