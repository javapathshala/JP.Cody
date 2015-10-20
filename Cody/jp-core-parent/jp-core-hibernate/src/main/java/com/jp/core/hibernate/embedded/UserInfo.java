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

import java.util.HashSet;
import java.util.Set;

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
	 * case of two address objects, so 2nd one is office address
	 * Now problem here is how do hiberate generate columns for this address object as they are already created. 
	 * Solution : Need to override columns names for officeAdress which is of same type as homeaddress.
	 * 
	 */

	// @Embedded
	// @AttributeOverrides({ @AttributeOverride(name = "city", column =
	// @Column(name = "OFFICE_CITY")),
	// @AttributeOverride(name = "state", column = @Column(name =
	// "OFFICE_STATE")), })
	private Address officeAddress;

	/**
	 * Case where we have multiple address objects - like 10 residence address u have lived.
	 * So create a different table for holding residence address.Should be not embeddable
	 * So taking object as department
	 * 
	 */
	// @ElementCollection
	private Set<Department> departmentList = new HashSet<Department>();

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

	/**
	 * @return the officeAddress
	 */
	public Address getOfficeAddress() {
		return officeAddress;
	}

	/**
	 * @param officeAddress the officeAddress to set
	 */
	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}

	/**
	 * @return the departmentList
	 */
	public Set<Department> getDepartmentList() {
		return departmentList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserInfo [userName=" + userName + ", homeAddress=" + homeAddress + ", officeAddress=" + officeAddress + ", departmentList="
				+ departmentList + ", getRecordId()=" + getRecordId() + ", getLastModified()=" + getLastModified() + "]";
	}

	/**
	 * @param departmentList the departmentList to set
	 */
	public void setDepartmentList(Set<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public void addDepartment(Department address) {
		departmentList.add(address);

	}

}
