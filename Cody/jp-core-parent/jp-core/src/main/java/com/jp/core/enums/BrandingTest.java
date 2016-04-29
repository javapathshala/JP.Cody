/*
 * File: BrandingTest.java
 * Date: 30-Apr-2016
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
package com.jp.core.enums;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class BrandingTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Branding branding = Branding.valueOf("OH");
		System.out.println(branding.isOpen());
		
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("US/Eastern"));
		System.out.println(now.get(Calendar.DAY_OF_WEEK));
//		 @see #SUNDAY
//	     * @see #MONDAY
//	     * @see #TUESDAY
//	     * @see #WEDNESDAY
//	     * @see #THURSDAY
//	     * @see #FRIDAY
//	     * @see #SATURDAY
		
//		if (Calendar.FRIDAY==now.get(Calendar.DAY_OF_WEEK)){
//			System.out.println("Its friday");
//		}
//		

	}

}
