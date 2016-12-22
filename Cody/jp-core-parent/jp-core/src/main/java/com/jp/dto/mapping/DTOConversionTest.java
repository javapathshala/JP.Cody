/*
 * File: DTOConversionTest.java
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
public class DTOConversionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LocationDTO locationDTO = new LocationDTO();
		locationDTO.setAddress(null);
		locationDTO.setCity("city");
		locationDTO.setCountry("India");
		locationDTO.setActive(true);
		CustomerDTO customer = new CustomerDTO();
		customer.setAge(451);
		customer.setName("dimit");
		customer.setAmount(new BigDecimal(4));
		locationDTO.setCustomerDTO(customer);
		LocationWebDTO locationWebDTO = new LocationWebDTO();
		try {
			DataMapper dtoConversion = new DataMapper();
			locationWebDTO = (LocationWebDTO) dtoConversion.mapData(locationDTO, locationWebDTO);
			System.out.println("locationWebDTO locationWebDTO");
			System.out.println(locationWebDTO.getAddress());
			System.out.println(locationWebDTO.getCity());
			System.out.println(locationWebDTO.getCountry());
			System.out.println(locationWebDTO.isActive());
			// CustomerDTO cust = locationWebDTO.getCustomerDTO();
			// System.out.println(cust.getAge());
			// System.out.println(cust.getName());
			// System.out.println(cust.getAmount());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
