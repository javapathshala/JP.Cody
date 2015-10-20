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

import com.jp.payment.ws.PaymentEndPoint;
import com.jp.payment.ws.PaymentResponse;
import com.jp.ws.payment.client.PaymentEndpointFactory;

/**
 * @author Dimit Chadha
 */
public class WSPaymentTest {

	/**
	 * @param args																																															
	 */
	public static void main(String[] args) {
		PaymentEndPoint endPoint = PaymentEndpointFactory.create();

		PaymentResponse response = endPoint.calculatePayment("1", "Belt", 2, 12);

		System.out.println("Payment Response : ");
		System.out.println(response.getStatusCode());
		System.out.println(response.getReasonCode());
		System.out.println(response.getMessage());
		System.out.println(response.getAmount());
		System.out.println(response.getItemCode());
		System.out.println(response.getItemName());

	}

}
