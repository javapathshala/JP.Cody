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
package com.jp.xss;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class CrossSiteScripting
{

    public static void main(String[] args)
    {
        String invalid = "<script>alert();</script>";
        String data = "ddddd";
        if (invalid.indexOf('<') != -1)
        {
            invalid = invalid.replaceAll("<", "&lt;");
        }
        if (invalid.indexOf('>') != -1)
        {
            invalid = invalid.replaceAll(">", "&gt;");
        }
        if (invalid.indexOf('&') != -1)
        {
            invalid = invalid.replaceAll("&", "&amp;");
        }
        if (invalid.indexOf('\"') != -1)
        {
            invalid = invalid.replaceAll("\"", "&quot;");
        }

        if (!invalid.equalsIgnoreCase(data))
        {
            System.out.println("maclious code attacked");
        }
    }
}
