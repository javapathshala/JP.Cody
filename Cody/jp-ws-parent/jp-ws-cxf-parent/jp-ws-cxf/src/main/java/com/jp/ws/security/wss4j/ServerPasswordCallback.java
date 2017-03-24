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
package com.jp.ws.security.wss4j;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.wss4j.common.ext.WSPasswordCallback;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class ServerPasswordCallback implements CallbackHandler
{

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException
    {
        for (int i = 0; i < callbacks.length; i++)
        {
            WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
            // You must set a password for the user, WSS4J would compare
            // the password with the password sent by client, if they match
            // message will be processed. Any mismatch in password will
            // result in a SOAP Fault.
            if (pc.getUsage() == WSPasswordCallback.USERNAME_TOKEN)
            {
                if (pc.getIdentifier().equalsIgnoreCase("jpuser"))
                {
                    pc.setPassword("jppass");
                }
            }
        }
    }
}
