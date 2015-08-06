/*
 * File: MathServiceImplOne.java Date: 01-May-2015 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.math.impl.one;

import java.util.List;

import com.jp.math.full.core.usersession.UserSession;
import com.jp.math.full.core.usersession.UserSessionStore;
import com.jp.ws.api.MathCacheService;
import com.jp.ws.api.response.MathResponse;
import com.jp.ws.api.response.ResponseReason;
import com.jp.ws.api.response.ResponseStatus;

/**
 * @author Dimit Chadha
 */
public class CachedMathService implements MathCacheService {

	/**
	 * A reference to the singleton Agent Session store containing the map of
	 * agent sessions being managed by this local instance.
	 */
	private UserSessionStore sessionStore;

	@Override
	public MathResponse summation(String userId, List<Integer> numbers) {
		// is there already an existing session for the requested agent?
		UserSession existingSession = sessionStore.getSessionByAgentId(userId);
		int sum = 0;

		if (existingSession == null) {
			// no existing session - create new AgentSession used to track the
			// new agent being registered
			UserSession newSession = sessionStore.createNewAgentSession(userId);
			for (Integer num : numbers) {
				sum += num;
			}
		} else {

		}

		return new MathResponse(ResponseStatus.SUCCESS, ResponseReason.OK, sum + "");
	}

	/**
	 * @param sessionStore
	 *            the sessionStore to set
	 */
	public void setSessionStore(UserSessionStore sessionStore) {
		this.sessionStore = sessionStore;
	}

}
