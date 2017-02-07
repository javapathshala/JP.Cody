/*
 * File: Flight.java Date: 21-Aug-2013 This source code is part of Java
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
public class Flight implements Command {
	private ATCTower atcTower;

	public Flight(ATCTower atcTower) {
		this.atcTower = atcTower;
	}

	public void land() {
		if (atcTower.isLandingOk()) {
			System.out.println("Landing done....");
			atcTower.setLandingStatus(true);
		} else
			System.out.println("Will wait to land....");
	}

	public void getReady() {
		System.out.println("Getting ready...");
	}
}
