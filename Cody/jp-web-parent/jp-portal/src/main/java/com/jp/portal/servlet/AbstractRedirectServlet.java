/**
 * File: AbstractRedirectServlet.java Date: Apr 13, 2015 This source code is
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jp.status.ComponentStatusGenerator;
import com.jp.status.ComponentStatusGeneratorRegistry;
import com.jp.status.response.Aspect;
import com.jp.status.response.Component;
import com.jp.status.response.StatusCode;





/**
 * An {@link HttpServlet} extension that redirects a request to a referring
 * service.
 * 
 * @author Dimit Chadha
 */
public abstract class AbstractRedirectServlet extends HttpServlet implements ComponentStatusGenerator {

	private static final long serialVersionUID = 1320830932428990706L;

	/**
	 * This implementation redirects the request to the service specified by a
	 * request parameter. If no parameter is specified, the request is forwarded
	 * to the portal menu page.
	 */

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get the service redirection parameter
		String redirectURL = req.getParameter("service");
		// If no service redirection is defined
		if (redirectURL == null) {
			// Forward the request to the portal menu page
			req.getRequestDispatcher("/WEB-INF/jsp/menu.jsp").forward(req, resp);
		} else {
			// Build the redirection URL
			String service = String.format("%s/", redirectURL);
			// Send the redirect
			resp.sendRedirect(service);
		}
	}

	/**
	 * Registers this object as an object that can be interrogated for health
	 * state (implementation of the jp-status-api). {@inheritDoc}
	 */
	@Override
	public void register() {
		// Add this component to the list of generators
		ComponentStatusGeneratorRegistry.register(this);
	}

	/**
	 * {@inheritDoc}. Returns a Component object to the health-status framework,
	 * describing the current state of the Cluster as viewed by this instance.
	 *
	 * @return
	 */
	@Override
	public Component getStatus() {
		// Create a Component object to hold the state we are going to report
		Component component = new Component();
		component.setName("Java Pathshala Portal ");
		List<Aspect> aspects = component.getElements();
		aspects.addAll(createAspectList());
		return component;
	}

	/**
	 * Create List of aspects to be added to Component
	 *
	 * @return
	 */
	private List<Aspect> createAspectList() {
		List<Aspect> aspectsList = new ArrayList<>();
		Aspect aspect = new Aspect();
		aspect.setName("Latest Status");
		aspect.setMessage("Portal running fine at " + new Date());
		aspect.setStatus(StatusCode.OK);
		aspectsList.add(aspect);
		return aspectsList;
	}

}
