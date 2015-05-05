/*
 * File: MathResponse.java Date: 05-May-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.ws.api.response;

/**
 * @author Dimit Chadha
 */
public class MathResponse extends AbstractResponse {

	private int answer;

	public MathResponse() {
		super();
	}

	public MathResponse(ResponseStatus status, ResponseReason reason, int answer) {
		this(status, reason, null, answer);
	}

	public MathResponse(ResponseStatus status, ResponseReason reason, String message, int answer) {
		super(status, reason, message);
		this.answer = answer;
	}

	/**
	 * @return the answer
	 */
	public int getAnswer() {
		return answer;
	}

	/**
	 * @param answer
	 *            the answer to set
	 */
	public void setAnswer(int answer) {
		this.answer = answer;
	}

}
