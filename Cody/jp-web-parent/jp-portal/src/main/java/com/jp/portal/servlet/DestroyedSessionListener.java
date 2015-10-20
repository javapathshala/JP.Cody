/** File: DestroyedSessionListener.java
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
package com.jp.portal.servlet;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class DestroyedSessionListener implements HttpSessionListener {
	/**
	 * {@inheritDoc}
	 *
	 * This implementation is equivalent to a no-op.
	 *
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// Do nothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * This implementation removes the session ID of the {@link HttpSession}
	 * associated with the specified {@link HttpSessionEvent}.
	 *
	 * @see SessionAuditor#remove(java.lang.String)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// Get the session associated with the event
		HttpSession session = event.getSession();
		// Record the logout request (or session cleanup by container)
		SessionAuditor.INSTANCE.remove(session.getId());
	}
}
