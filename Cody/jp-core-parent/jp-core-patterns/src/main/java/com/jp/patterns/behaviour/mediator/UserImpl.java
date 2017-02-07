/*
 * File: UserImpl.java Date: 21-Aug-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.patterns.behaviour.mediator;

/**
 * @author dchadha
 */
public class UserImpl extends User {
	
	public UserImpl(ChatMediator mediator, String name) {
		super(mediator, name);
	}

	@Override
	public void send(String msg) {
		System.out.println(this.userName + ": Sending Message=" + msg);
		chatMediator.sendMessage(msg, this);
	}

	@Override
	public void receive(String msg) {
		System.out.println(this.userName + ": Received Message:" + msg);
	}

}
