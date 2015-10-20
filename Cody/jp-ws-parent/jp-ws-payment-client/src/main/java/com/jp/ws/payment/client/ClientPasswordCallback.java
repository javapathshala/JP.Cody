/*
 * File: ClientPasswordCallback.java
 * Date: 12-Oct-2015
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention
 * orders
 * and to prosecute the authors of any infraction.
 * Visit us at www.javapathshala.com
 */
package com.jp.ws.payment.client;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class ClientPasswordCallback implements CallbackHandler {

	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

		for (int i = 0; i < callbacks.length; i++) {

			WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];

			if (pc.getUsage() == WSPasswordCallback.USERNAME_TOKEN) {

				// you can source the username and password from
				// other sources like login context, LDAP, DB etc

				pc.setIdentifier("jpuser");
				pc.setPassword("jppass");
			}
		}
	}
}