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
public class JPFileCreationException extends JPHaulerException
{

    public JPFileCreationException()
    {
        super();
        super.setErorReason(ErrorReason.DESIRED_FILE_PARSING_ERROR);
    }

    public JPFileCreationException(Throwable cause)
    {
        super(cause);
        super.setErorReason(ErrorReason.DESIRED_FILE_PARSING_ERROR);
    }

    public JPFileCreationException(String message)
    {
        super(message);
        super.setErorReason(ErrorReason.DESIRED_FILE_PARSING_ERROR);
    }

    public JPFileCreationException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     *
     * @param errorReason
     * @param message
     */
    public JPFileCreationException(ErrorReason errorReason, String message)
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
    public JPFileCreationException(String message, Throwable exception, ErrorReason errorReason)
    {
        super(message, exception);
        super.setErorReason(errorReason);
    }

    public JPFileCreationException(Throwable exception, Object... parameters)
    {
        super(exception);
        super.setErorReason(ErrorReason.DESIRED_FILE_CREATION_ERROR, parameters);
    }

}
