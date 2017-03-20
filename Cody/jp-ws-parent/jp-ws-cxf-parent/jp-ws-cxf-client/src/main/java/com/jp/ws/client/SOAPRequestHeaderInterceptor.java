/*
 * Copyright (c) Java Pathshala.
 * All rights reserved.
 *
 * This program is protected by copyright law but you are authorise to learn
 * & gain ideas from it. Its unauthorised use is explicitly prohibited &
 * any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction.
 * www.javapathshala.com
 */
package com.jp.ws.client;

import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class SOAPRequestHeaderInterceptor extends AbstractSoapInterceptor
{

    public SOAPRequestHeaderInterceptor()
    {
        super(Phase.PRE_LOGICAL);
    }

//    public SOAPRequestHeaderInterceptor()
//    {
//        super(Phase.PRE_INVOKE);
//    }

    @Override
    public void handleMessage(SoapMessage soapMessage) throws Fault
    {
        
        
        List<Header> list = soapMessage.getHeaders();
        System.out.println(soapMessage.getVersion());
        list.forEach((headers) ->
        {
            System.out.println("Header Name -->" + headers.getName() + " = " + headers.toString());
        });
//        if (list.isEmpty() || !list.contains("user"))
//        {
//
//            System.out.println("handleMessage: Invalid username or password for user: ");
//            //return;
//        }

        DocumentBuilder documentBuilder = null;
        try
        {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }
        catch (ParserConfigurationException e)
        {
            throw new Fault(e);
        }
        Document document = documentBuilder.newDocument();

        Element eSecure = document.createElement("Secured");

        Element eUser = document.createElement("user");
        eUser.setTextContent("java");

        Element ePassword = document.createElement("password");
        ePassword.setTextContent("pathshala");

        eSecure.appendChild(eUser);
        eSecure.appendChild(ePassword);
        // Create Header object
        QName qnameCredentials = new QName("Secured");
        Header header = new Header(qnameCredentials, eSecure);
        soapMessage.getHeaders().add(header);
        for (Header headers : soapMessage.getHeaders())
        {
            System.out.println("Header Name 2-->" + headers.getName() + " = " + headers.toString());
        }
 
      

    }
}
