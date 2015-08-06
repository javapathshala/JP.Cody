/*
 * File: UserSession.java Date: 05-Aug-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.math.full.core.usersession;

/**
 * @author Dimit Chadha
 */
public class UserSession {

	private final String sessionId;
	private final String userId;
	

	/**
	 * Instantiates a new AgentSession object with the provided unique
	 * session-id.
	 * 
	 * @param sessId
	 *            the session ID is mandatory, an AgentSession cant exist
	 *            without an ID.
	 * @param agentIdentifier
	 *            the agent-id (usually windows login-id) of the agent being
	 *            tracked by this session.
	 * @param client
	 *            the 3-alpha client code of the client the call center agent is
	 *            servicing.
	 * @param additionalUserIdentifier
	 *            an (optional) additional piece of information useful from an
	 *            operations perspective in identifying the agent being tracked
	 *            by this session.
	 */
	public UserSession(String sessId, String userId) {
		// populate all the primitives
		this.sessionId = sessId;
		this.userId = userId;

		// create and set the initial state snapshot
		// CoreAgentStateInfo firstSnapshot = new CoreAgentStateInfo();
		// firstSnapshot.setAgentState(AgentState.UNKNOWN);
		// updateState(firstSnapshot);
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

}
