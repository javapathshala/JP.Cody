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
package com.jp.ws.api.exceptions;

import java.io.Serializable;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class ServiceException extends Exception implements Serializable
{

    private ServiceExceptionDetails faultDetails[];

    public ServiceException(ServiceExceptionDetails faultDetails[])
    {
        this.faultDetails = faultDetails;
    }

    public ServiceException(String message, ServiceExceptionDetails faultDetails[])
    {
        super(message);
        this.faultDetails = faultDetails;
    }

    public ServiceExceptionDetails[] getFaultDetails()
    {
        return faultDetails;
    }

}
