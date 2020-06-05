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

//import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
//import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import javax.xml.namespace.QName;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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

    @Override
    public void handleMessage(SoapMessage soapMessage) throws Fault
    {
        Header sec = soapMessage.getHeader(new QName("Secured"));
        if (sec == null)
        {
            throw new SoapFault("Request SOAP Header not found in request ", new Throwable(), new QName("Secured"));
        }
        else
        {
            Element elementNSImpl = (Element) sec.getObject();
            // obtain all Nodes tagged 'user' or 'password'
            NodeList userIdNode = elementNSImpl.getElementsByTagNameNS("*", "user");
            NodeList passwordNode = elementNSImpl.getElementsByTagNameNS("*", "password");
            String userId = userIdNode.item(0).getChildNodes().item(0).getNodeValue();
            String password = passwordNode.item(0).getChildNodes().item(0).getNodeValue();
            if (!"java".equals(userId) || !"pathshala".equals(password))
            {
                throw new SoapFault("Invalid Request SOAP Header found ", new Throwable(), new QName("Secured"));
            }
        }
    }
}
