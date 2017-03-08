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

/**
 *
 * Holds List of IP address that are permitted to invoke web service
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class IPValidator
{

    private String ips[];

    public IPValidator(String ips[])
    {
        this.ips = ips;
    }

    public boolean isAllowed(String ip)
    {
        for (String ip1 : ips)
        {
            if (ip1.equals(ip))
            {
                return true;
            }
        }
        return false;
    }
}
