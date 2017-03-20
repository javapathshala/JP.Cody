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
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.endpoint.Client;

/**
 * @author Dimit Chadha
 */
public final class MathEndpointFactory
{

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

    private static final String USER_ID = "user";
    private static final String PASSWORD = "password";
    private static final String ACCESS_TOKEN = "access_token";
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

    private Properties properties = new Properties();

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
    private MathEndpointFactory()
    {
        // Create a new properties object

        // Get the bootstrap properties file from the classpath
        InputStream input = getClass().getClassLoader().getResourceAsStream(BOOTSTRAP_FILE);
        try
        {
            // If the bootstrap properties file was found on the classpath
            if (input != null)
            {
                try
                {
                    properties.load(input);
                }
                finally
                {
                    input.close();
                }
            }
        }
        catch (IOException e)
        {
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

        try
        {
            // Construct the WSDL URL
            this.url = new URL(urlProtocol, urlHost, urlPort, urlFile);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new {@link ChroniclerEndpoint} instance using the
     * {@link ChroniclerEndpointFactory} singleton.
     *
     * @see #createPort()
     * @since 1.0
     * @return a new {@link ChroniclerEndpoint} instance using the
     * {@link ChroniclerEndpointFactory} singleton.
     */
    public static MathEndPoint create()
    {
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
    private MathEndPoint createPort()
    {
        MathEndPoint mathEndPoint = null;
        synchronized (this)
        {
            // If the service object is null
            if (this.service == null)
            {
                //1. Http basic authentication
                Authenticator.setDefault(new Authenticator()
                {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(properties.getProperty(USER_ID), properties.getProperty(PASSWORD).toCharArray());
                    }
                });
                // Create a new service object
                createService();
                mathEndPoint = service.getMathPort();
            }

            //2. Added ACCESS_TOKEN to http request header
            BindingProvider bindingProvider = (BindingProvider) mathEndPoint;
            Map<String, Object> requestContext = bindingProvider.getRequestContext();
            Map<String, List<String>> headers = new HashMap<>();
            headers.put("ACCESS_TOKEN", Arrays.asList(properties.getProperty(ACCESS_TOKEN)));
            requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

            // Get the underlying Client object from the proxy object of service interface
            Client proxy = (Client)ClientProxy.getClient(mathEndPoint);
            proxy.getOutInterceptors().add(new SOAPRequestHeaderInterceptor());
            // Create a list for holding all SOAP headers
//            List<Header> headersList = new ArrayList<Header>();
//
//            Header testSoapHeader1;
//            try
//            {
//                testSoapHeader1 = new Header(new QName("uri:singz.ws.sample", "soapheader1"), "SOAP Header Message 1", new JAXBDataBinding(String.class));
//                Header testSoapHeader2 = new Header(new QName("uri:singz.ws.sample", "soapheader2"), "SOAP Header Message 2", new JAXBDataBinding(String.class));
//
//                headersList.add(testSoapHeader1);
//                headersList.add(testSoapHeader2);
//            }
//            catch (JAXBException ex)
//            {
//                Logger.getLogger(MathEndpointFactory.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            // Add SOAP headers to the web service request
//            proxy.getRequestContext().put(Header.HEADER_LIST, headersList);
            // Return a new port from the service
            return mathEndPoint;
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
    private void createService()
    {
        synchronized (this)
        {
            // Create a new service object from the WSDL URL
            this.service = new MathService(url);
        }
    }

}
