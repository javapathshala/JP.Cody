/*
 * File: EnterpriseMathService.java Date: 30-Apr-2015 This source code is part
 * of Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.ws.payment;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.soap.SOAPFault;

import org.apache.cxf.BusFactory;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.ReadHeadersInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.w3c.dom.Element;

import com.jp.ws.payment.api.PaymentService;
import com.jp.ws.payment.api.response.PaymentResponse;

/**
 * @author Dimit Chadha
 */
@WebService(serviceName = "PaymentService", portName = "PaymentPort", name = "PaymentEndPoint", targetNamespace = "http://jp.com/payment/ws")
// http://localhost:90/jp-ws-payment/svc/ws/Payment?wsdl
public class EnterprisePaymentService implements PaymentService {

	private PaymentService delegate;

	/**
	* Sets the {@link PaymentService} instance to delegate requests to.
	*
	* @since 1.0
	* @param value the {@link PaymentService} instance to delegate
	* requests to.
	*/
	@WebMethod(exclude = true)
	public void setDelegate(PaymentService value) {
		this.delegate = value;
	}

	@Override
	public PaymentResponse calculatePayment(String itemCode, String itemName, int quantity, int price) {
//		 Message message = PhaseInterceptorChain.getCurrentMessage();
//
//	        SoapMessage soapMessage = (SoapMessage) message;
//	        List<Header> list = soapMessage.getHeaders();
//	        for (Header header : list) {
//	            System.out.println("Country: "+((Element)header.getObject()).getTextContent());
//	        }
	
		return delegate.calculatePayment(itemCode, itemName, quantity, price);
	}

}
