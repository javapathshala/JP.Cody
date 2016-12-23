/*
 * File: AnonymousInnerInterface.java Date: 31-Jul-2013 This source code is part
 * of Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.inner;

/**
 * @author dimit.chadha
 */
public class AnonymousInnerInterface {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AnonInterface an = new AnonInterface() {
			
			public void dispaly() {
				// TODO Auto-generated method stub
				
			}
		}; 
		
		AnonymousInnerInterface in=new AnonymousInnerInterface();
		
an.dispaly();
	}

}
