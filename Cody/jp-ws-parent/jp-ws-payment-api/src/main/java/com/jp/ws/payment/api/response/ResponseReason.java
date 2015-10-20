/*
 * File: ResponseReason.java Date: 05-May-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.ws.payment.api.response;

/**
 * @author Dimit Chadha
 */
public enum ResponseReason {

	/**
	 * The request was successful.
	 */
	OK,
	/**
	 * The request failed due to a network communication failure between
	 * server-side work item service instances.
	 */
	COMMUNICATION_ERROR,
	/**
	 * The request failed because there was no current work item found for the
	 * specified work item ID.
	 */
	INVALID_INPUT,
	/**
	 * An event occurred for a work item, but the work item was not in a current
	 * state for which the event was expected.
	 */
	UNEXPECTED_EVENT_FOR,
	/**
	 * The request failed, however the nature of the failure needs to be
	 * determine by analyzing the server-side logs of the work item service.
	 */
	REASON_UNDETERMINED_SEE_LOGS;
}
