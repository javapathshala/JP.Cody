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
public class HttpRequestHeaderInterceptor extends AbstractPhaseInterceptor<Message>
{

    public HttpRequestHeaderInterceptor()
    {
        super(Phase.PRE_INVOKE);
    }

    @Override
    public void handleMessage(Message message) throws Fault
    {
        // Get request HTTP headers
        Map<String, List<String>> headers = (Map<String, List<String>>)(message.getExchange().getInMessage().get(Message.PROTOCOL_HEADERS));
        try
        {
            List<String> token_headers = headers.get("ACCESS_TOKEN");
            if (token_headers == null || token_headers.isEmpty())
            {
                System.out.println(" No Header information");
                throw new Exception("No Header information");
            }
            else
            {
                String token = token_headers.get(0);
                if (token == null)
                {
                    System.out.println(" Token value missing");
                    throw new Exception("No Header information");
                }
                else if (!token.equals("1234"))
                {
                    System.out.println("token invalid");
                    throw new Exception("token invalid");
                }
                else
                {
                    System.out.println("header ok");
                }
            }
        }
        catch (Exception ce)
        {
            throw new Fault(ce);
        }
    }
}
