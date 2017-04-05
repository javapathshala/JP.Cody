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

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

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

    private String getMACAddress()
    {

        InetAddress ip;
        StringBuilder sb = new StringBuilder();

        try
        {

            ip = InetAddress.getLocalHost();
            System.out.println("Current IP address : " + ip.getHostAddress());

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            System.out.print("Current MAC address : ");

            for (int i = 0; i < mac.length; i++)
            {

                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));

            }
            System.out.println(sb.toString());

        }
        catch (UnknownHostException e)
        {

            e.printStackTrace();

        }
        catch (SocketException e)
        {

            e.printStackTrace();
        }
        return sb.toString();
    }
}
