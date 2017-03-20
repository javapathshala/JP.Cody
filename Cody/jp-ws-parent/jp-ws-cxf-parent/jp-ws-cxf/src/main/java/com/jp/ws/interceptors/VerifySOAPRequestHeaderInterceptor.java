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
package com.jp.ws.interceptors;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class VerifySOAPRequestHeaderInterceptor extends AbstractSoapInterceptor
{

    public VerifySOAPRequestHeaderInterceptor()
    {
        super(Phase.PRE_PROTOCOL);
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
        System.out.println(soapMessage.get(Message.ENCODING));
        
        Header sec=soapMessage.getHeader(new QName("Secured"));
       if ("aaaaaa".equals(((ElementNSImpl)sec.getObject()).getTextContent())){
           System.out.println("OK");
       }
       else{
           System.out.println("HAHAH");
       }
        
        System.out.println();
        
        for (Header header : list)
        {
            System.out.println("Header Name -->" + header.getName() + " = " + header.toString());
            
            System.out.println(header.getName().getLocalPart());
            
            header.getObject();
            System.out.println(header.getName().getNamespaceURI());
            System.out.println(header.getName() + ":" + ((ElementNSImpl)header.getObject()).getTextContent());
        }
        
        
//        if (list.isEmpty() || !list.contains("user"))
//        {
//
//            System.out.println("handleMessage: Invalid username or password for user: ");
//            //return;
//        }

//        DocumentBuilder documentBuilder = null;
//        try
//        {
//            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//        }
//        catch (ParserConfigurationException e)
//        {
//            throw new Fault(e);
//        }
//        Document document = documentBuilder.newDocument();
//
//        Element eSecure = document.createElement("Secured");
//
//        Element eUser = document.createElement("user");
//        eUser.setTextContent("java");
//
//        Element ePassword = document.createElement("password");
//        ePassword.setTextContent("pathshala");
//
//        eSecure.appendChild(eUser);
//        eSecure.appendChild(ePassword);
//        // Create Header object
//        QName qnameCredentials = new QName("Secured");
//        Header header = new Header(qnameCredentials, eSecure);
//        soapMessage.getHeaders().add(header);
//        for (Header headers : soapMessage.getHeaders())
//        {
//            System.out.println("Header Name 2-->" + headers.getName() + " = " + headers.toString());
//        }
// 
      

    }
}
