/*
 * File: SimulateTemplate.java Date: 21-Aug-2013 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.patterns.behaviour.template;

/**
 * @author dchadha
 */
public class SimulateTemplate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CrossCompiler iphone = new IPhoneCompiler();
		iphone.crossCompile();

		CrossCompiler android = new AndroidCompiler();
		android.crossCompile();

	}

}
