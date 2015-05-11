/*
 * Copyright (c) JavaPathshala.com. All rights reserved. No parts of this source
 * code can be reproduced without written consent from Java Pathshala.com. Visit
 * us at www.javapathshala.com File: RunSimpleST.java Date: Sep 30, 2014
 */
package com.jp.patterns.create.singleton;

/**
 * @author Dimit Chadha
 */
public class CloneSTTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Creating SingleTon Pattern Instance");

		try {
			CloneST stp1 = CloneST.getInstance();
			System.out.println("Fisrt Instance Created::" + stp1);
			System.out.println("Creating Second Instance by clonning");

			System.out.println("#########");
			System.out.println("Try Creating singleton object by serialization");
			System.out.println("#########");
			System.out.println("Try Creating singleton object by Reflection");
			try {
				Class<CloneST> refClass = (Class<CloneST>) Class.forName("com.jp.pattern.create.singleton.CloneST");
				CloneST refInstance = refClass.newInstance();
				System.out.println("Second Instance is created using reflection -> " + refInstance);

				CloneST clonedObject = (CloneST) stp1.clone();
				System.out.println("Second Instance is created using clonning -> " + clonedObject);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		} catch (CloneNotSupportedException e) {
			System.out.println("Clone of Object Restricted");
			e.printStackTrace();
		}
	}

}
