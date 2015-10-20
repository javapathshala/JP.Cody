/*
 * File: AbstractStatusElement.java Date: 23-Apr-2015 This source code is part
 * of Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.status.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * The most basic status representation object.
 * 
 * @author Dimit Chadha
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType
public abstract class AbstractStatusElement {

	/**
	 * The logical name of this {@link AbstractStatusElement}.
	 *
	 * @since 2.1
	 */
	@XmlAttribute(required = true)
	protected String name;

	/**
	 * Gets the logical name of this {@link AbstractStatusElement}.
	 *
	 * @return the logical name of this {@link AbstractStatusElement}.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the logical name of this {@link AbstractStatusElement}.
	 *
	 * @since 2.1
	 * @param value
	 *            logical name of this {@link AbstractStatusElement}.
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Gets the {@link StatusCode} that summarizes the status of this
	 * {@link AbstractStatusElement}.
	 *
	 * @return the {@link StatusCode} that summarizes the status of this
	 *         {@link AbstractStatusElement}.
	 */
	@XmlAttribute(required = true)
	public abstract StatusCode getStatus();
}
