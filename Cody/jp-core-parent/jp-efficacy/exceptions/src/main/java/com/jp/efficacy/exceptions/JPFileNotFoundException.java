/*
 * Copyright (c) Java Pathshala
 * All rights reserved.
 *
 * No parts of this source code can be reproduced without written consent from
 * Java Pathshala
 */
package com.jp.efficacy.exceptions;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class JPFileNotFoundException extends JPHaulerException
{

    public JPFileNotFoundException()
    {
        super();
        super.setErorReason(ErrorReason.DESIRED_FILE_NOT_FOUND);
    }

    public JPFileNotFoundException(Throwable cause)
    {
        super(cause);
        super.setErorReason(ErrorReason.DESIRED_FILE_NOT_FOUND);
    }

    public JPFileNotFoundException(String message)
    {
        super(message);
        super.setErorReason(ErrorReason.DESIRED_FILE_NOT_FOUND);
    }

    public JPFileNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     *
     * @param errorReason
     * @param message
     */
    public JPFileNotFoundException(ErrorReason errorReason, String message)
    {
        super(message);
        super.setErorReason(errorReason);
    }

    /**
     *
     * @param message
     * @param exception
     * @param errorReason
     */
    public JPFileNotFoundException(String message, Throwable exception, ErrorReason errorReason)
    {
        super(message, exception);
        super.setErorReason(errorReason);

    }

    public JPFileNotFoundException(Throwable exception, Object... parameters)
    {
        super(exception);
        super.setErorReason(ErrorReason.DESIRED_FILE_NOT_FOUND, parameters);
    }

}
