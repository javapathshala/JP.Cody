/*
 * Copyright (c) JavaPathshala.com. All rights reserved. No parts of this source
 * code can be reproduced without written consent from Java Pathshala.com. Visit
 * us at www.javapathshala.com File: Vehicle.java Date: Oct 1, 2014
 */
package com.jp.patterns.create.prototype;

/**
 * @author Dimit Chadha
 */
public abstract class Vehicle implements Cloneable {

	private String id;
	protected String type;

	abstract void buy();

	public Object clone() {
		Object cloneObj = null;
		try {
			cloneObj = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return cloneObj;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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

}
