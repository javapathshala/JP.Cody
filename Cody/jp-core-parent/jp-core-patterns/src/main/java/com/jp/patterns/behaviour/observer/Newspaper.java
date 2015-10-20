/*
 * Copyright (c) JavaPathshala.com. All rights reserved. No parts of this source
 * code can be reproduced without written consent from Java Pathshala.com. Visit
 * us at www.javapathshala.com File: Newspaper.java Date: Jul 23, 2014
 */
package com.jp.patterns.behaviour.observer;

/**
 * @author Dimit Chadha
 */
public class Newspaper implements Observer {
	@Override
	public void update(float interest) {
		System.out.println("Newspaper: Interest Rate updated, new Rate is: " + interest);
	}

}
