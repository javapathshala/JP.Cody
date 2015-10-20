/*
 * File: CalulatePayment.java
 * Date: 26-Aug-2015
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
package com.jp.payment.cal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jp.ws.payment.api.PaymentService;
import com.jp.ws.payment.api.response.PaymentResponse;
import com.jp.ws.payment.api.response.ResponseReason;
import com.jp.ws.payment.api.response.ResponseStatus;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class CalulatePayment implements PaymentService {

	private static final Logger log = LoggerFactory.getLogger(CalulatePayment.class);

	@Override
	public PaymentResponse calculatePayment(String itemCode, String itemName, int quantity, int price) {
		PaymentResponse paymentResponse = new PaymentResponse(itemCode, itemName, quantity, price);
		int amount = price * quantity;
		paymentResponse.setAmount(amount);
		paymentResponse.setReasonCode(ResponseReason.OK);
		paymentResponse.setStatusCode(ResponseStatus.SUCCESS);
		paymentResponse.setMessage("Payment Process executed");
		log.info("Response is --> {}", paymentResponse);
		return paymentResponse;
	}

}
