/*
 * Copyright (c) JavaPathshala.com. All rights reserved. No parts of this source
 * code can be reproduced without written consent from Java Pathshala.com. Visit
 * us at www.javapathshala.com File: SimpleST.java Date: Sep 30, 2014
 */
package com.jp.patterns.create.singleton;

/**
 * @author Dimit Chadha
 */
public final class DoubleCheckST {

	private static DoubleCheckST instance = null;

	private DoubleCheckST() {

	}

	public static DoubleCheckST getInstance() {
		if (instance == null) { // single check
			synchronized (DoubleCheckST.class) {
				if (instance == null) { // double check
					instance = new DoubleCheckST();
				}
			}
		} else {
			System.out.println("Only One instance of SimpleST Object is allowed! No Two instances can co-exists");
		}
		return instance;
	}
}
