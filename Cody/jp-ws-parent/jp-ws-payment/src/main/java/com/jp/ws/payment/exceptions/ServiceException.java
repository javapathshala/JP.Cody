/*
 * File: ServiceException.java
 * Date: 09-Oct-2015
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
package com.jp.ws.payment.exceptions;

import java.io.Serializable;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.interceptor.Fault;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class ServiceException extends Exception implements Serializable {

	


	/**
	 * 
	 */
	private static final long serialVersionUID = 6202675623806469362L;
	private ServiceExceptionDetails faultDetails[];

	public ServiceException(ServiceExceptionDetails faultDetails[]) {
		this.faultDetails = faultDetails;
	}
//
	public ServiceException(String message, ServiceExceptionDetails faultDetails[]) {
		super(message);
		this.faultDetails = faultDetails;
	}

	public ServiceExceptionDetails[] getFaultDetails() {
		return faultDetails;
	}

}
