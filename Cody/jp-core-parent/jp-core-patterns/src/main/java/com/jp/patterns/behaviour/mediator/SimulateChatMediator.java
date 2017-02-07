/*
 * File: SimulateChatMediator.java Date: 21-Aug-2013 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.patterns.behaviour.mediator;

/**
 * @author dchadha
 */
public class SimulateChatMediator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ChatMediator chatMediator = new ChatMediatorImpl();
		User user1 = new UserImpl(chatMediator, "User 1");
		User user2 = new UserImpl(chatMediator, "User 2");
		User user3 = new UserImpl(chatMediator, "User 3");
		User user4 = new UserImpl(chatMediator, "User 4");
		chatMediator.addUser(user1);
		chatMediator.addUser(user2);
		chatMediator.addUser(user3);
		chatMediator.addUser(user4);
		
		user1.send("Message From User-1");
		System.out.println("#########################");
		user2.send("Message From User-2");
	}

}
