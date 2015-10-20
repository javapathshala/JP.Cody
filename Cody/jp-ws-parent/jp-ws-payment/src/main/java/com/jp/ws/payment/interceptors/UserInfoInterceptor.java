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

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NodeList;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class UserInfoInterceptor extends AbstractSoapInterceptor {

	private static final Logger log = LoggerFactory.getLogger(UserInfoInterceptor.class);

	private SAAJInInterceptor saajIn = new SAAJInInterceptor();

	public UserInfoInterceptor() {
		super(Phase.PRE_PROTOCOL);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		SOAPMessage doc = message.getContent(SOAPMessage.class);
		if (doc == null) {
			saajIn.handleMessage(message);
			doc = message.getContent(SOAPMessage.class);
		}
		SOAPHeader header = null;
		try {
			header = doc.getSOAPHeader();
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		if (header != null) {
			NodeList nodes = header.getElementsByTagNameNS("http://jp.com/payment/ws", "Username");
			if (nodes != null && nodes.item(0) != null) {
				String user = nodes.item(0).getTextContent();
				log.info("User --> {}", user);
			}
		}

		// if you want to read more http header messages, just use get method to
		// obtain from HttpServletRequest.
		HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
		if (null != request) {
			// Read http header to get client IP adress
			String addr = request.getRemoteAddr();
			// Read http header to get HeaderNames
			Enumeration enums = request.getHeaderNames();
			// Read http header to get cookie/
			Cookie[] cookies = request.getCookies();

			// mac address
			String mac = getMACAddress();

			log.info("User Information --> {},{},{},{},{}", addr, enums, cookies, request.getServerName(), mac);
		}

		// device check

		// String deviceType = "unknown";
		// if (device.isNormal()) {
		// deviceType = "normal";
		// } else if (device.isMobile()) {
		// deviceType = "mobile";
		// } else if (device.isTablet()) {
		// deviceType = "tablet";
		// }
		// log.info("Hello " + deviceType + " browser!");

	}

	// return current client mac address
	private String getMACAddress() {

		InetAddress ip;
		StringBuilder sb = new StringBuilder();

		try {

			ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());

			NetworkInterface network = NetworkInterface.getByInetAddress(ip);

			byte[] mac = network.getHardwareAddress();

			System.out.print("Current MAC address : ");

			for (int i = 0; i < mac.length; i++) {

				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));

			}
			System.out.println(sb.toString());

		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (SocketException e) {

			e.printStackTrace();
		}
		return sb.toString();
	}
}
