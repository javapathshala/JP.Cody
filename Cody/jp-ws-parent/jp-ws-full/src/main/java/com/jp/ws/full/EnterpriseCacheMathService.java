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
package com.jp.ws.full;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.jp.ws.api.MathCacheService;
import com.jp.ws.api.response.MathResponse;

/**
 * @author Dimit Chadha
 */
@WebService(serviceName = "CacheMathService", portName = "CacheMathPort", name = "CacheMathEndPoint", targetNamespace = "http://jp.com/cachemath/ws")
// http://localhost:8080/jp-ws-full/svc/ws/Math?wsdl
public class EnterpriseCacheMathService implements MathCacheService {

	private MathCacheService delegate;

	/**
	 * @param delegate
	 *            the delegate to set
	 */
	@WebMethod(exclude = true)
	public void setDelegate(MathCacheService delegate) {
		this.delegate = delegate;
	}

	@Override
	public MathResponse summation(@WebParam(name = "userId") String userId, @WebParam(name = "numberList") List<Integer> numbers) {
		return delegate.summation(userId, numbers);
	}

}
