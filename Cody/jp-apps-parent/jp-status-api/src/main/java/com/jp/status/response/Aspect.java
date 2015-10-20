/*
 * File: Aspect.java Date: 24-Apr-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.status.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * An {@link AbstractStatusElement} extension intended for representing an
 * atomic monitor.
 * 
 * @author Dimit Chadha
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType
public class Aspect extends AbstractStatusElement {

	/**
	 * The textual message that elaborates on the current {@link #status}.
	 *
	 * @since 2.1
	 */
	@XmlElement
	protected String message;
	/**
	 * The current status.
	 *
	 * @since 2.1
	 */
	private StatusCode status;

	/**
	 * Gets the textual message that elaborates on the current {@link #status}.
	 *
	 * @return the textual message that elaborates on the current
	 *         {@link #status}.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the textual message that elaborates on the current {@link #status}.
	 *
	 * @param value
	 *            the textual message that elaborates on the current
	 *            {@link #status}.
	 */
	public void setMessage(String value) {
		this.message = value;
	}

	/**
	 * {@inheritDoc} This implementation returns the current {@link #status}.
	 */
	@Override
	public StatusCode getStatus() {
		return status;
	}

	/**
	 * Sets the current status.
	 *
	 * @param value
	 *            the current status.
	 */
	public void setStatus(StatusCode value) {
		this.status = value;
	}
}
