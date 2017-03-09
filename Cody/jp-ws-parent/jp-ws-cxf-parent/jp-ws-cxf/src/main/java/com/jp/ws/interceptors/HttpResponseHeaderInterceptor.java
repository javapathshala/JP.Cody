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

import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class HttpResponseHeaderInterceptor extends AbstractPhaseInterceptor<Message>
{

    public HttpResponseHeaderInterceptor()
    {
        super(Phase.SEND);
    }

    @Override
    public void handleMessage(Message message) throws Fault
    {
        // Get request HTTP headers
        Map<String, List<String>> headers = (Map<String, List<String>>) message.getExchange().getOutMessage().get(Message.PROTOCOL_HEADERS);
        pushHeader(headers);
    }

    private void pushHeader(Map<String, List<String>> headers)
    {
        headers.put("KEY", Collections.singletonList(getBase64EncodedCredentials().trim()));
    }

    private String getBase64EncodedCredentials()
    {
        String userName = "java";
        String password = "pathshala";

        String credentials = userName + ":" + password;

        return new String(Base64.getEncoder().encode(credentials.getBytes()));
    }

}
