/*
 * File: CustomerDTO.java
 * Date: 22-Dec-2016
 *
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn 
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any 
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention orders
 * and to prosecute the authors of any infraction.
 * 
 * Visit us at www.javapathshala.com
 */
package com.jp.dto.mapping;

import java.math.BigDecimal;

/**
 * @author 72010964
 */
public class CustomerDTO {

	private String name;

	private int age;

	private BigDecimal amount;

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public int getAge() {
		return age;
	}

	
	public void setAge(int age) {
		this.age = age;
	}

	
	public BigDecimal getAmount() {
		return amount;
	}

	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	
	
}
