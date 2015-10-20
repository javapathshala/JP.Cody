/*
 * File: JPEndpointFactory.java Date: 04-May-2015 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.ws.payment.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;

import com.jp.payment.ws.PaymentEndPoint;
import com.jp.payment.ws.PaymentService;

/**
 * @author Dimit Chadha
 */
public final class PaymentEndpointFactory {

	/**
	 * The name of the classpath resource that defines endpoint properties.
	 */
	public static final String BOOTSTRAP_FILE = "jp-payment-client.properties";

	/**
	 * The property name which defines the file portion of the WSDL {@link URL}.
	 */
	private static final String WSDL_FILE = "wsdl";
	/**
	 * The property name which defines the host portion of the WSDL {@link URL}.
	 */
	private static final String WSDL_HOST = "host";
	/**
	 * The property name which defines the protocol portion of the WSDL
	 * {@link URL}.
	 */
	private static final String WSDL_PROTOCOL = "protocol";
	/**
	 * The property name which defines the port portion of the WSDL {@link URL}.
	 */
	private static final String WSDL_PORT = "port";
	/**
	 * A singleton {@link PaymentEndpointFactory} instance used to load endpoint
	 * properties and create endpoint instances.
	 */
	private static final PaymentEndpointFactory INSTANCE = new PaymentEndpointFactory();

	// The {@link URL} of the WSDL that defines the API for end points created
	// by this {@link MathEndpointFactory}.
	private URL url;

	// The {@link javax.xml.Service} instance used to create end point
	// instances.
	private PaymentService service;

	/**
	 * Creates a new {@link ChroniclerEndpointFactory} instance. This
	 * implementation loads the {@link #BOOTSTRAP_FILE} and constructs the
	 * {@link #url} of the WSDL which defines the {@link #service} API.
	 *
	 * @see ClassLoader#getResource(java.lang.String)
	 * @see Properties#load(java.io.InputStream)
	 * @see URL#URL(java.lang.String, java.lang.String, int, java.lang.String)
	 * @since 1.0
	 */
	private PaymentEndpointFactory() {
		// Create a new properties object
		Properties properties = new Properties();
		// Get the bootstrap properties file from the classpath
		InputStream input = getClass().getClassLoader().getResourceAsStream(BOOTSTRAP_FILE);
		try {
			// If the bootstrap properties file was found on the classpath
			if (input != null) {
				try {
					// Load the bootstrap properties file in to the properties
					// object
					properties.load(input);
				} finally {
					// Close the handle to the bootstrap properties file
					input.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			// LOG.error("Unable to load bootstrap file: {}", BOOTSTRAP_FILE,
			// e);
		}
		// Get the WSDL protocol property
		String urlProtocol = properties.getProperty(WSDL_PROTOCOL);
		// Get the WSDL host property
		String urlHost = properties.getProperty(WSDL_HOST);
		// Get the WSDL port property
		String port = properties.getProperty(WSDL_PORT);
		// Parse the WSDL property as an integer
		int urlPort = Integer.parseInt(port);
		// Get the WSDL file property
		String urlFile = properties.getProperty(WSDL_FILE);
		try {
			// Construct the WSDL URL
			this.url = new URL(urlProtocol, urlHost, urlPort, urlFile);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			// LOG.error("Unable to form {} URL", BOOTSTRAP_FILE, e);
		}
	}

	/**
	 * Creates a new {@link ChroniclerEndpoint} instance using the
	 * {@link ChroniclerEndpointFactory} singleton.
	 *
	 * @see #createPort()
	 * @since 1.0
	 * @return a new {@link ChroniclerEndpoint} instance using the
	 *         {@link ChroniclerEndpointFactory} singleton.
	 */
	public static PaymentEndPoint create() {
		// Create a new port using the singleton instance
		return INSTANCE.createPort();
	}

	/**
	 * Creates a new {@link ChroniclerEndpoint} using the {@link #service}
	 * associated with this {@link ChroniclerEndpointFactory}.
	 *
	 * @see #createService()
	 * @see SessionService#getSessionPort()
	 * @since 1.0
	 * @return a {@link ChroniclerEndpoint} instance.
	 */
	private PaymentEndPoint createPort() {
		PaymentEndPoint port = null;
		synchronized (this) {
			// If the service object is null
			if (this.service == null) {
				// Create a new service object
				//http basic authentication
				Authenticator.setDefault(new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("jpws", "jpws".toCharArray());
					}
				});



				createService();
				port = service.getPaymentPort();
			}
		}
			
			BindingProvider bindingProvider = (BindingProvider) port;
			Map<String, Object> requestContext = bindingProvider.getRequestContext();
			
			//Another option for http basic authentication
			/**
			requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url.toString());
	        requestContext.put(BindingProvider.PASSWORD_PROPERTY, "jpws");
	        requestContext.put(BindingProvider.USERNAME_PROPERTY, "jpws");
			 **/

			// Creating HTTP headers
			Map<String, List<String>> headers = new HashMap<String, List<String>>();
			headers.put("ACCESS_TOKEN", Arrays.asList("1234"));
			requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
			
		
			//Secuirty via WS* 
			Client client = ClientProxy.getClient(port);
			Endpoint cxfEndpoint = client.getEndpoint();
			Map<String, Object> outProps = new HashMap<String, Object>();
			outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
			outProps.put(WSHandlerConstants.USER, "jpuser");
			outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_DIGEST);
			outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ClientPasswordCallback.class.getName());
			WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
			cxfEndpoint.getOutInterceptors().add(wssOut);

			return port;
		 
	}

	/**
	 * Creates the {@link #service} instance responsible for creating endpoint
	 * instances. <strong>WARNING: </strong> The {@link #service} field will be
	 * set to a {@code null} value in the event the service WSDL is not
	 * accessible via the {@link #url} associated with this
	 * {@link ChroniclerEndpointFactory}.
	 *
	 * @see ChroniclerService#ChroniclerService(java.net.URL)
	 * @since 1.0
	 */
	private void createService() {
		synchronized (this) {
			// Create a new service object from the WSDL URL
			this.service = new PaymentService(url);
		}
	}

}
