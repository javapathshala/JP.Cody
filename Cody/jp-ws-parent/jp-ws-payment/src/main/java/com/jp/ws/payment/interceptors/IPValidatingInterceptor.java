/*
 * File: IPValidatingInterceptor.java
 * Date: 28-Aug-2015
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
package com.jp.ws.payment.interceptors;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import com.jp.ws.payment.IPValidator;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class IPValidatingInterceptor extends AbstractSoapInterceptor {

	private IPValidator ipValidator;

	public IPValidatingInterceptor(IPValidator ipValidator) {
		super(Phase.RECEIVE);
		this.ipValidator = ipValidator;
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
		if (null != request) {
			// Read http header to get client IP adress
			String ip = request.getRemoteAddr();
			if (!ipValidator.isAllowed(ip)) {
				throw new SoapFault("Not Permitted", Fault.FAULT_CODE_CLIENT);

			}
		}
	}
}
