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

import javax.servlet.http.HttpServletRequest;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class IPValidatingInterceptor extends AbstractSoapInterceptor
{

    private IPValidator ipValidator;

    public IPValidatingInterceptor(IPValidator ipValidator)
    {
        super(Phase.RECEIVE);
        this.ipValidator = ipValidator;
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault
    {
        HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
        if (null != request)
        {
            // Read http header to get client IP adress
            String ip = request.getRemoteAddr();
            if (!ipValidator.isAllowed(ip))
            {
                throw new SoapFault("Not Permitted! You don't have valid IP", Fault.FAULT_CODE_CLIENT);
            }
        }
    }
}
