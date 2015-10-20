/*
 * File: AbstractStatusCollection.java Date: 23-Apr-2015 This source code is
 * part of Java Pathshala-Wisdom Being Shared. This program is protected by
 * copyright law but you are authorise to learn & gain ideas from it. Its
 * unauthorised use is explicitly prohibited & any addition & removal of
 * material. If want to suggest any changes, you are welcome to provide your
 * comments on GitHub Social Code Area. Its unauthorised use gives Java
 * Pathshala the right to obtain retention orders and to prosecute the authors
 * of any infraction. Visit us at www.javapathshala.com
 */
package com.jp.status.response;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Dimit Chadha
 */
public class AbstractStatusCollection<T extends AbstractStatusElement> extends AbstractStatusElement {

	/**
	 * The {@link List} of {@link AbstractStatusElement} instances to aggregate.
	 */
	private final List<T> elements = new LinkedList<T>();

	/**
	 * Gets the {@link List} of {@link AbstractStatusElement} instances to
	 * aggregate.
	 *
	 * @return the {@link List} of {@link AbstractStatusElement} instances to
	 *         aggregate.
	 */
	public List<T> getElements() {
		return elements;
	}

	/**
	 * @return the highest priority {@link StatusCode} found in the {@link List}
	 *         of {@link #elements}.
	 */
	@Override
	public StatusCode getStatus() {
		StatusCode status = StatusCode.OK;
		// For each status in the collection
		for (T element : elements) {
			// If the aspect status is more degraded than the component status
			if (element.getStatus().getPriority() > status.getPriority()) {
				// Override the component status with the status of the aspect
				status = element.getStatus();
			}
		}
		// For each status in the list
		return status;
	}

}
