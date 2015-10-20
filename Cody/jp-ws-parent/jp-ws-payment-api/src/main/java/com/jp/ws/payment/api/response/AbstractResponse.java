/*
 * File: AbstractResponse.java Date: 05-May-2015 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.ws.payment.api.response;

/**
 * @author Dimit Chadha
 */
public abstract class AbstractResponse {

	// A high-level status code indicating fundamental success or failure of the
	// request.
	private ResponseStatus statusCode;

	/**
	 * When {@link #statusCode} is NOT {@link ResponseStatus#SUCCESS}, this
	 * reason code provides a finer-grained reason for why the request failed.
	 */
	private ResponseReason reasonCode;

	/**
	 * When {@link #statusCode} is NOT {@link ResponseStatus#SUCCESS}, this
	 * field may contain additional information about why the request failed, in
	 * the form of a textual message. If the request was successful, this field
	 * will be null.
	 */
	private String message;

	/**
	 * Default constructor, creates a blank response object.
	 */
	public AbstractResponse() {
		// do nothing.
	}

	/**
	 * Overloaded constructor, creates a new response object populated with the
	 * provided field values.
	 *
	 * @param status
	 *            the high-level status code - see {@link #statusCode}.
	 * @param reason
	 *            the low-level failure reason code - see {@link #reasonCode}.
	 */
	public AbstractResponse(ResponseStatus status, ResponseReason reason) {
		this.statusCode = status;
		this.reasonCode = reason;
	}

	/**
	 * Overloaded constructor, creates a new response object populated with the
	 * provided field values.
	 *
	 * @param status
	 *            the high-level status code - see {@link #statusCode}.
	 * @param reason
	 *            the low-level failure reason code - see {@link #reasonCode}.
	 * @param message
	 *            an textual message further describing the failure reason - see
	 *            {@link #message}.
	 */
	public AbstractResponse(ResponseStatus status, ResponseReason reason, String message) {
		this(status, reason);
		this.message = message;
	}

	/**
	 * Returns additional information about why the request failed, in the form
	 * of a textual message. Only relevant when {@link #getStatusCode} returns a
	 * value other than {@link ResponseStatus#SUCCESS}.
	 *
	 * @see #message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets or updates the additional information (in the form of a textual
	 * message) about why the API request failed.
	 *
	 * @param value
	 *            the additional failure message text string.
	 * @see #getMessage
	 */
	public void setMessage(String value) {
		this.message = value;
	}

	/**
	 * Returns the associated finer-grained failure reason code when a request
	 * has failed. Only relevant when {@link #getStatusCode} returns a value
	 * other than {@link ResponseStatus#SUCCESS}.
	 *
	 * @see #reasonCode
	 */
	public ResponseReason getReasonCode() {
		return reasonCode;
	}

	/**
	 * Sets or updates the associated finer-grained failure reason code when a
	 * request has failed.
	 *
	 * @param value
	 *            the new reason code to be set to.
	 * @see #getReasonCode
	 */
	public void setReasonCode(ResponseReason value) {
		this.reasonCode = value;
	}

	/**
	 * Returns a high-level status code indicating fundamental success or
	 * failure of the request. When a request is successful,
	 * {@link ResponseStatus#SUCCESS} will be returned. A value of
	 * {@link ResponseStatus#REQUEST_FAILURE} will be returned for all
	 * unsuccessful SupportService requests, and will possibly also be returned
	 * for unsuccessful AgentControlService requests. A value of
	 * {@link ResponseStatus#SESSION_FAILURE} will only be returned from
	 * AgentControlService requests (so wont be returned for failed
	 * SupportService requests).
	 */
	public ResponseStatus getStatusCode() {
		return statusCode;
	}

	/**
	 * Sets or updates the high-level status code indicating fundamental success
	 * or failure of the request.
	 *
	 * @param value
	 *            the new status to be set to.
	 * @see #getStatusCode
	 */
	public void setStatusCode(ResponseStatus value) {
		this.statusCode = value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AbstractResponse [statusCode=" + statusCode + ", reasonCode=" + reasonCode + ", message=" + message + "]";
	}
	
	
}
