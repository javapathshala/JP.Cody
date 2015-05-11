/*
 * Copyright (c) JavaPathshala.com. All rights reserved. No parts of this source
 * code can be reproduced without written consent from Java Pathshala.com. Visit
 * us at www.javapathshala.com File: SimpleST.java Date: Sep 30, 2014
 */
package com.jp.patterns.create.singleton;

import java.io.Serializable;

/**
 * @author Dimit Chadha
 */
public final class CloneST implements Cloneable, Serializable {

	private static final long serialVersionUID = -2477143290825295956L;
	private static CloneST instance = null;

	// private CloneST() {
	//
	// }

	public CloneST() {
		if (CloneST.instance != null) {
			throw new InstantiationError("Creating of this object is not allowed.");
		}
	}

	public static CloneST getInstance() {
		if (instance == null) { // single check
			synchronized (CloneST.class) {
				if (instance == null) { // double check
					instance = new CloneST();
				}
			}
		} else {
			System.out.println("Only One instance of SimpleST Object is allowed! No Two instances can co-exists");
		}
		return instance;
	}

	public Object clone() throws CloneNotSupportedException {
		System.out.println("Cloning of singleton not allowed");
		throw new CloneNotSupportedException("Cloning of singleton not allowed");
	}

	protected Object readResolve() {
		return instance;
	}
}
