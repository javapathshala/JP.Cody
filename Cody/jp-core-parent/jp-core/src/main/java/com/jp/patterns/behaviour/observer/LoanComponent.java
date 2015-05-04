/*
 * Copyright (c) JavaPathshala.com. All rights reserved. No parts of this source
 * code can be reproduced without written consent from Java Pathshala.com. Visit
 * us at www.javapathshala.com File: LoanComponent.java Date: Jul 23, 2014
 */
package com.jp.patterns.behaviour.observer;

import java.util.ArrayList;

/**
 * @author Dimit Chadha
 */
public class LoanComponent implements StatusGenerator {
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private String type;
	private float interest;
	private String bank;

	public LoanComponent(String type, float interest, String bank) {
		this.type = type;
		this.interest = interest;
		this.bank = bank;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the interest
	 */
	public float getInterest() {
		return interest;
	}

	/**
	 * @param interest
	 *            the interest to set
	 */
	public void setInterest(float interest) {
		this.interest = interest;
		notifyObservers();
	}

	/**
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * @param bank
	 *            the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);

	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer ob : observers) {
			System.out.println("Notifying Observers on change in Loan interest rate");
			ob.update(this.interest);
		}

	}

}
