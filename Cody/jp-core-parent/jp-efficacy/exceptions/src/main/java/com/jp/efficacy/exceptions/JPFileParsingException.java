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
public class JPFileParsingException extends JPHaulerException
{

    public JPFileParsingException()
    {
        super();
        super.setErorReason(ErrorReason.DESIRED_FILE_PARSING_ERROR);
    }

    public JPFileParsingException(Throwable cause)
    {
        super(cause);
        super.setErorReason(ErrorReason.DESIRED_FILE_PARSING_ERROR);
    }

    public JPFileParsingException(String message)
    {
        super(message);
        super.setErorReason(ErrorReason.DESIRED_FILE_PARSING_ERROR);
    }

    public JPFileParsingException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     *
     * @param errorReason
     * @param message
     */
    public JPFileParsingException(ErrorReason errorReason, String message)
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
    public JPFileParsingException(String message, Throwable exception, ErrorReason errorReason)
    {
        super(message, exception);
        super.setErorReason(errorReason);

    }
    public JPFileParsingException(Throwable exception, Object... parameters)
    {
        super(exception);
        super.setErorReason(ErrorReason.DESIRED_FILE_PARSING_ERROR, parameters);
    }

}
