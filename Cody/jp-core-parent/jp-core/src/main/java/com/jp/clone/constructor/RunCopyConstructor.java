/*
 * File: RunCopyConstructor.java Date: 29-Jul-2013 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.clone.constructor;

/**
 * @author dimit.chadha
 */
public class RunCopyConstructor {

	/**
	 * 
	 */
	public RunCopyConstructor() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Person person1 = new Person(new Car(), "Ben");
		Person person2 = new Person(person1);
		// Copy constructor. This is used to create a copy of person1 object.
		System.out.println(person1);
		System.out.println(person2);
		// But if the variable "car" in Person class is actually an instance of
		// a subclass then you will get the wrong answer; what is called a
		// slice. Check below example and you will understand what I meant by a
		// problem. Let�s say you have a subclass of Car - "Mitsubishi".
		
		Person person3 = new Person(new Mitsubishi(), "Ben");
        Person person4 = new Person(person1);
        System.out.println(person3);
        System.out.println(person4);

	}

}
