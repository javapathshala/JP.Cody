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
package com.jp.ws.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import com.jp.math.ws.MathEndPoint;
import com.jp.math.ws.MathService;

/**
 * @author Dimit Chadha
 */
public final class MathEndpointFactory {

	/**
	 * The name of the classpath resource that defines endpoint properties.
	 */
	public static final String BOOTSTRAP_FILE = "jp-math-client.properties";

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
	 * A singleton {@link MathEndpointFactory} instance used to load endpoint
	 * properties and create endpoint instances.
	 */
	private static final MathEndpointFactory INSTANCE = new MathEndpointFactory();

	// The {@link URL} of the WSDL that defines the API for end points created
	// by this {@link MathEndpointFactory}.
	private URL url;

	// The {@link javax.xml.Service} instance used to create end point
	// instances.
	private MathService service;

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
	private MathEndpointFactory() {
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
	public static MathEndPoint create() {
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
	private MathEndPoint createPort() {
		synchronized (this) {
			// If the service object is null
			if (this.service == null) {
				// Create a new service object
				createService();
			}
			// Return a new port from the service
			return service.getMathPort();
		}
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
			this.service = new MathService(url);
		}
	}

}
