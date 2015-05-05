/*
 * File: WSTest.java Date: 05-May-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.ws.test;

import java.util.ArrayList;
import java.util.List;

import com.jp.math.ws.MathEndPoint;
import com.jp.math.ws.MathResponse;
import com.jp.ws.client.MathEndpointFactory;

/**
 * @author Dimit Chadha
 */
public class WSTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MathEndPoint endPoint = MathEndpointFactory.create();

		// For summation
		List<Integer> numberList = new ArrayList<Integer>();
		numberList.add(new Integer(2));
		numberList.add(new Integer(2));
		numberList.add(new Integer(2));

		MathResponse summation = endPoint.summation(numberList);

		System.out.println("Sum Response : ");
		System.out.println(summation.getStatusCode());
		System.out.println(summation.getReasonCode());
		System.out.println(summation.getMessage());
		System.out.println(summation.getAnswer());

		MathResponse productResponse = endPoint.multiple(numberList);
		System.out.println("Product is : ");
		System.out.println(productResponse.getStatusCode());
		System.out.println(productResponse.getReasonCode());
		System.out.println(productResponse.getMessage());
		System.out.println(productResponse.getAnswer());

	}

}
