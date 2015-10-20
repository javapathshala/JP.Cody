/*
 * File: HttpHeaderInterceptor.java
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
package com.jp.ws.payment.interceptors;

import java.util.List;
import java.util.Map;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class HttpHeaderInterceptor extends AbstractPhaseInterceptor<Message> {

	public HttpHeaderInterceptor() {
		super(Phase.INVOKE);
	}

	public void handleMessage(Message message) throws Fault {
		// Get request HTTP headers
		Map<String, List<String>> headers = (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);
		try {

			List<String> token_headers=headers.get("ACCESS_TOKEN");
			if(token_headers==null || token_headers.size()==0){
				System.out.println(" No Header information");
				throw new Exception("No Header information");
			}else {
				String token = token_headers.get(0);
				if (token==null){
					System.out.println(" Token value missing");
					throw new Exception("No Header information");
				}else if (!token.equals("1234")){
					System.out.println("token invalid");
					throw new Exception("token invalid");
				}else{
					System.out.println("header ok");
				}
			}
			
		} catch (Exception ce) {
			throw new Fault(ce);
		}
	}
	
//	public void handleMessage(Message outMessage) throws Fault {
//		Map<String, List> headers = (Map<String, List>) outMessage
//				.get(Message.PROTOCOL_HEADERS);
//		try {
//			headers.put("Authorization", Collections.singletonList(BASIC
//					+ SPACE + getBase64EncodedCredentials().trim()));
//		} catch (Exception ce) {
//			throw new Fault(ce);
//		}
//	}
//
//	private String getBase64EncodedCredentials(){
//		String userName = null;
//		String password = null;
//		Iterator iterator = null != users ? users.keySet().iterator()
//				: null;
//		if (null != iterator && iterator.hasNext()) {
//			userName = iterator.next();
//			password = users.get(userName);
//		}
//		String clearCredentials = userName + COLON + password;
//		return new String(Base64.encodeBase64(clearCredentials.getBytes()));
//	}

}
