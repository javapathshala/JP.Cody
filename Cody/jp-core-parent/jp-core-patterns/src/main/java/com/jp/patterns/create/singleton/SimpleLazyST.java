/*
 * Copyright (c) JavaPathshala.com. All rights reserved. No parts of this source
 * code can be reproduced without written consent from Java Pathshala.com. Visit
 * us at www.javapathshala.com File: SimpleST.java Date: Sep 30, 2014
 */
package com.jp.patterns.create.singleton;

/**
 * @author Dimit Chadha
 */
public final class SimpleLazyST {

	private static SimpleLazyST instance = null;

	private SimpleLazyST() {

	}

	public static synchronized SimpleLazyST getInstance() {
		if (instance == null) {
			instance = new SimpleLazyST();
		} else {
			System.out.println("Only One instance of SimpleST Object is allowed! No Two instances can co-exists");
		}
		return instance;
	}
}
