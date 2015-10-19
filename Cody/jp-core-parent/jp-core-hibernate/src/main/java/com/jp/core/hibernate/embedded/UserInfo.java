/*
 * File: UserDetails.java
 * Date: 08-Sep-2015
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention
 * orders
 * and to prosecute the authors of any infraction.
 * Visit us at www.javapathshala.com
 */
package com.jp.core.hibernate.embedded;

import com.jp.core.hibernate.entities.AbstractRecord;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class UserInfo extends AbstractRecord {

	private String userName;

	/**
	 * This is value object only. Table will have extra columns defined in 
	 * Address.Simple implementation
	 */
	// @Embedded - using hbm instead.
	private Address homeAddress;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the homeAddress
	 */
	public Address getHomeAddress() {
		return homeAddress;
	}

	/**
	 * @param homeAddress the homeAddress to set
	 */
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserInfo [userName=" + userName + ", homeAddress=" + homeAddress.toString() + ", getRecordId()=" + getRecordId()
				+ ", getLastModified()=" + getLastModified() + "]";
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	

}
