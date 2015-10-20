/*
 * File: LoggingInterceptor.java
 * Date: 27-Aug-2015
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

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.Conduit;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class HeaderInfoInterceptor extends AbstractSoapInterceptor {

	private static final Logger log = LoggerFactory.getLogger(HeaderInfoInterceptor.class);

	public HeaderInfoInterceptor() {
		super(Phase.INVOKE);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {

		// DocumentBuilder builder = null;
		// try {
		// builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		// } catch (ParserConfigurationException e) {
		// e.printStackTrace();
		// }
		// Document doc = builder.newDocument();
		//
		// Element eSecure = doc.createElement("Secured");
		//
		// Element eUser = doc.createElement("user");
		// eUser.setTextContent("myuser");
		//
		// Element ePassword = doc.createElement("password");
		// ePassword.setTextContent("password");
		//
		// eSecure.appendChild(eUser);
		// eSecure.appendChild(ePassword);
		// // Create Header object
		// QName qnameCredentials = new QName("Secured");
		// Header header = new Header(qnameCredentials, eSecure);
		// message.getHeaders().add(header);
		List<Header> list = message.getHeaders();
		if (list == null || list.size() == 0 || !list.contains("userName")) {
			// ServiceExceptionDetails ServiceExceptionDetailsArray[] = new
			// ServiceExceptionDetails[1];
			// ServiceExceptionDetails serviceExceptionDetails = new
			// ServiceExceptionDetails();
			// serviceExceptionDetails.setFaultCode("100");
			// serviceExceptionDetails.setFaultMessage("Student Name is not correct");
			// ServiceExceptionDetailsArray[0] = serviceExceptionDetails;
			// throw new Fault(new ServiceException("Fault Message",
			// ServiceExceptionDetailsArray));
			System.out.println("handleMessage: Invalid username or password for user: ");
			sendErrorResponse(message, HttpURLConnection.HTTP_BAD_REQUEST);
			//return;
		}
		for (Header headers : list) {
			
			log.info("Header Name -->{}", headers.getName() + " = " + headers.toString());
		}
	}

	/**
	 * @param message
	 * @param httpForbidden
	 */
	private void sendErrorResponse(SoapMessage message, int responseCode) {
		Message outMessage = getOutMessage(message);
		outMessage.put(Message.RESPONSE_CODE, responseCode);

		// Set the response headers
		@SuppressWarnings("unchecked")
		Map<String, List<String>> responseHeaders = (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);

		if (responseHeaders != null) {
			responseHeaders.put("WWW-Authenticate", Arrays.asList(new String[] { "Basic realm=realm" }));
			responseHeaders.put("Content-Length", Arrays.asList(new String[] { "0" }));
		}
		message.getInterceptorChain().abort();
		try {
			getConduit(message).prepare(outMessage);
			close(outMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private Message getOutMessage(Message inMessage) {
		Exchange exchange = inMessage.getExchange();
		Message outMessage = exchange.getOutMessage();
		if (outMessage == null) {
			Endpoint endpoint = exchange.get(Endpoint.class);
			outMessage = endpoint.getBinding().createMessage();
			exchange.setOutMessage(outMessage);
		}
		outMessage.putAll(inMessage);
		return outMessage;
	}

	private Conduit getConduit(Message inMessage) throws IOException {
		Exchange exchange = inMessage.getExchange();
		EndpointReferenceType target = exchange.get(EndpointReferenceType.class);
		Conduit conduit = exchange.getDestination().getBackChannel(inMessage);
		exchange.setConduit(conduit);
		return conduit;
	}

	private void close(Message outMessage) throws IOException {
		OutputStream os = outMessage.getContent(OutputStream.class);
		os.flush();
		os.close();
	}
}
