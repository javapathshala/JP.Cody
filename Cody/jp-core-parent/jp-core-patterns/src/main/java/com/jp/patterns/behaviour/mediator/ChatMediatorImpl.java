/*
 * File: ChatMediatorImpl.java Date: 21-Aug-2013 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.patterns.behaviour.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dchadha
 */
public class ChatMediatorImpl implements ChatMediator {
	private List<User> users;

	public ChatMediatorImpl() {
		this.users = new ArrayList<User>();
	}

	@Override
	public void sendMessage(String msg, User user) {
		for (User u : this.users) {
			// message should not be received by the user sending it
			if (u != user) {
				u.receive(msg);
			}
		}
	}

	@Override
	public void addUser(User user) {
		this.users.add(user);
	}

}
