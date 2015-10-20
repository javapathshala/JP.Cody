/**
 * File: PortalBrandingFilter.java
 * Date: Apr 15, 2015
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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link Filter} implementation that creates a session attribute defining the
 * look and feel of the user experience.
 * 
 * @author Dimit Chadha
 *
 */

@WebFilter(urlPatterns = "/*")
public class PortalBrandingFilter implements Filter {

	/**
	 * The name of the {@link HttpSession} attribute used to define the portal
	 * branding.
	 *
	 */
	public static final String ATTRIBUTE_NAME = "branding";
	/**
	 * The name of the {@link Cookie} used to track branding across different
	 * web application contexts.
	 *
	 */
	private static final String COOKIE_NAME = "JPSBRANDING";
	/**
	 * The branding (color scheme, images, etc.) to apply to the
	 * {@link HttpSession}.
	 *
	 */
	private static final String DEFAULT_BRANDING = "jp";

	private static final Logger LOG = LoggerFactory.getLogger(PortalBrandingFilter.class);

	/**
	 * A {@link Properties} mapping which associates server port numbers to
	 * branding attribute values.
	 *
	 * @since 1.1
	 */
	private final Properties brandings = new Properties();

	// </editor-fold>

	// <editor-fold defaultstate="collapsed"
	// desc="Filter Method Implementations">
	/**
	 * {@inheritDoc}
	 *
	 * This implementation loads the branding properties file as a resource from
	 * the {@link ClassLoader}. The contents are retained to the
	 * {@link #brandings} object for future reference.
	 *
	 * @see #getClass()
	 * @see Class#getClassLoader()
	 * @see ClassLoader#getResourceAsStream(java.lang.String)
	 * @see Properties#load(java.io.InputStream)
	 * @since 1.1
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Get the class loader for the current class
		ClassLoader loader = getClass().getClassLoader();
		try {
			// Get the branding property file from the classpath
			InputStream input = loader.getResourceAsStream("jp-branding.properties");
			try {
				// Load the properties from the input stream
				brandings.load(input);
			} finally {
				// Close the input stream
				input.close();
			}
		} catch (Exception e) {
			LOG.error("Unable to load server brandings", e);
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * This implementation sets a session attribute defining the look and feel
	 * of the user experience. This method attempts to determine if an branding
	 * attribute is already defined in a {@link Cookie} or session attribute
	 * associated with the specified request. If no branding is already defined,
	 * the server default is used.
	 *
	 * @see #getSessionBranding(javax.servlet.http.HttpServletRequest)
	 * @see #getCookieBranding(javax.servlet.http.HttpServletRequest)
	 * @see #getServerBranding(javax.servlet.http.HttpServletRequest)
	 * @see #setCookieBranding(javax.servlet.http.HttpServletResponse,
	 *      java.lang.String)
	 * @see #setSessionBranding(javax.servlet.http.HttpServletRequest,
	 *      java.lang.String)
	 * @see Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 * @since 1.1
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// If an HTTP request/response is being filtered
		if (HttpServletRequest.class.isInstance(request) && HttpServletResponse.class.isInstance(response)) {
			// Cast the request as an HTTP request
			HttpServletRequest req = HttpServletRequest.class.cast(request);
			// Cast the response as an HTTP response
			HttpServletResponse resp = HttpServletResponse.class.cast(response);
			// Get the branding defined by the session
			String branding = getSessionBranding(req);
			// If no branding is defined in the session
			if (branding == null) {
				// Get the branding associated with the request cookies
				branding = getCookieBranding(req);
				// If no branding is defined by the request
				if (branding == null) {
					// Get the branding defined by the server
					branding = getServerBranding(req);
					// Set the server branding to a new cookie
					setCookieBranding(resp, branding);
				}
				// Set cookie branding to a new session attribute
				setSessionBranding(req, branding);
			}
		}
		// Continue filtering the request
		chain.doFilter(request, response);
	}

	/**
	 * {@inheritDoc}
	 *
	 * This implementation is equivalent to a no-op.
	 *
	 * @since 1.1
	 */
	@Override
	public void destroy() {
		// Do Nothing
	}

	/**
	 * Gets the branding attribute from the {@link HttpSession} associated with
	 * the specified request.
	 *
	 * @see HttpServletRequest#getSession(boolean)
	 * @see HttpSession#getAttribute(java.lang.String)
	 * @since 1.1
	 * @param request
	 *            the request from which the branding session attribute value
	 *            should be retrieved.
	 * @return the value of the branding attribute defined in the
	 *         {@link HttpSession} associated with the specified
	 *         {@link HttpServletRequest}, or {@code null} if no branding
	 *         attribute is defined.
	 */
	private String getSessionBranding(HttpServletRequest request) {
		String branding = null;
		// Get the session for the specified request
		HttpSession session = request.getSession(false);
		// If a session already exists
		if (session != null) {
			// Get the branding attribute from the session
			Object brandingAttribute = session.getAttribute(branding);
			// If the branding attribute exists
			if (brandingAttribute != null) {
				// Cast the branding attribute as a string
				branding = String.class.cast(brandingAttribute);
			}
		}
		return branding;
	}

	/**
	 * Sets the branding attribute to the {@link HttpSession} associated with
	 * the specified response.
	 *
	 * @see HttpServletRequest#getSession()
	 * @see HttpSession#setAttribute(java.lang.String, java.lang.Object)
	 * @param request
	 *            the {@link HttpServletResponse} to which the branding
	 *            attribute should be set.
	 * @param branding
	 *            the value of the branding attribute to set.
	 */
	private void setSessionBranding(HttpServletRequest request, String branding) {
		// Get the session for the specified request
		HttpSession session = request.getSession();
		// Set the branding attribute to the session
		session.setAttribute(ATTRIBUTE_NAME, branding);
	}

	/**
	 * Gets the branding attribute from a {@link Cookie} associated with the
	 * specified request.
	 *
	 * @see Cookie#getName()
	 * @see Cookie#getValue()
	 * @see HttpServletRequest#getCookies()
	 * @since 1.1
	 * @param request
	 *            the request from which the branding {@link Cookie} value
	 *            should be retrieved.
	 * @return the value of the branding attribute defined in the {@link Cookie}
	 *         associated with the specified {@link HttpServletRequest}, or
	 *         {@code null} if no branding {@link Cookie} is defined.
	 */
	private String getCookieBranding(HttpServletRequest request) {
		String branding = null;
		// Get the array of cookies for the specified request
		Cookie[] cookies = request.getCookies();
		// If the array of cookies is not null
		if (cookies != null) {
			// For each cookie
			for (Cookie cookie : cookies) {
				// If the cookie name matches the name of the branding cookie
				if (COOKIE_NAME.equals(cookie.getName())) {
					// Use the cookie value
					branding = cookie.getValue();
					// Prevent processing of any more cookies
					break;
				}
			}
		}
		// Return the cookie value
		return branding;
	}

	/**
	 * Sets the branding attribute to a new {@link Cookie} on the specified
	 * response. In order to maintain the user experience across multiple web
	 * applications, the {@link Cookie} path is set so the {@link Cookie} will
	 * be resubmitted if context path changes.
	 *
	 * @see Cookie#Cookie(java.lang.String, java.lang.String)
	 * @see Cookie#setPath(java.lang.String)
	 * @see HttpServletResponse#addCookie(javax.servlet.http.Cookie)
	 * @since 1.1
	 * @param response
	 *            the {@link HttpServletResponse} to which the branding
	 *            attribute should be set.
	 * @param branding
	 *            the value of the branding attribute to set.
	 */
	private void setCookieBranding(HttpServletResponse response, String branding) {
		// Create a new cookie for tracking the branding
		Cookie cookie = new Cookie(COOKIE_NAME, branding);
		// Set the path of the cookie to be server-wide (to support resubmission
		// across contexts)
		cookie.setPath("/");
		// Add the cookie to the response
		response.addCookie(cookie);
	}

	/**
	 * Gets the branding attribute based on the local port of the specified
	 * request.
	 *
	 * @see HttpServletRequest#getLocalPort()
	 * @see Properties#getProperty(java.lang.String, java.lang.String)
	 * @since 1.1
	 * @param request
	 *            the request from which the local port number should be
	 *            retrieved.
	 * @return the server branding mapped to the local port of the specified
	 *         {@link HttpServletRequest}, or the {@link #DEFAULT_BRANDING} if
	 *         no mapping exists.
	 */
	private String getServerBranding(HttpServletRequest request) {
		// Get the port that serviced the request
		int port = request.getLocalPort();
		// Get the branding associated with the port servicing the request
		String branding = brandings.getProperty(String.valueOf(port), DEFAULT_BRANDING);
		// Return the branding
		return branding;
	}

}
