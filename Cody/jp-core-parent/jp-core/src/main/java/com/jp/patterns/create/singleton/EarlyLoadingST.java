/*
 * Copyright (c) JavaPathshala.com. All rights reserved. No parts of this source
 * code can be reproduced without written consent from Java Pathshala.com. Visit
 * us at www.javapathshala.com File: SimpleST.java Date: Sep 30, 2014
 */
package com.jp.patterns.create.singleton;

/**
 * @author Dimit Chadha
 */
public final class EarlyLoadingST {

	private static final EarlyLoadingST instance = new EarlyLoadingST();

	private EarlyLoadingST() {

	}

	public static EarlyLoadingST getInstance() {
		System.out.println("Already Created Instance " + instance);
		return instance;
	}
}
