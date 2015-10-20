/*
 * Copyright (c) JavaPathshala.com. All rights reserved. No parts of this source
 * code can be reproduced without written consent from Java Pathshala.com. Visit
 * us at www.javapathshala.com File: Bike.java Date: Oct 1, 2014
 */
package com.jp.patterns.create.prototype;

/**
 * @author Dimit Chadha
 */
public class Bike extends Vehicle {

	public Bike() {
		type = "Royal Bike";
	}

	@Override
	void buy() {
		System.out.println("I am ready to buy " + type);
	}

}
