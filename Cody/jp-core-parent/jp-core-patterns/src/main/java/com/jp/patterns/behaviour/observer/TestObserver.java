/*
 * File: TestObserver.java Date: 04-May-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.patterns.behaviour.observer;

/**
 * @author Dimit Chadha
 */
public class TestObserver {

	public static void main(String[] args) {
		// create subject
		MyTopic topic = new MyTopic();

		// create observers
		MyObserver obj1 = new MyTopicSubscriber("Dimit");
		MyObserver obj2 = new MyTopicSubscriber("Lucky");
		MyObserver obj3 = new MyTopicSubscriber("Smylee");

		// register observers to the subject
		topic.register(obj1);
		topic.register(obj2);
		topic.register(obj3);

		// attach observer to subject
		obj1.setSubject(topic);
		obj2.setSubject(topic);
		obj3.setSubject(topic);

		// check if any update is available
		obj1.update();

		// now send message to subject
		topic.postMessage("New Message");
	}
}
