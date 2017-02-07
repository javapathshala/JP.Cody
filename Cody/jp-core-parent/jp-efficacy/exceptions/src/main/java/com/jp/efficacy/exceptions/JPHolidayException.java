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
public class JPHolidayException extends JPBaseException
{

    public JPHolidayException(String message)
    {
        super(message);
    }

    public JPHolidayException(Throwable cause)
    {
        super(cause);
    }

    public JPHolidayException(String message, Throwable cause)
    {
        super(message, cause);
    }


    /**
     *
     * @param message
     * @param exception
     * @param errorReason
     */
    public JPHolidayException(String message, Throwable exception, ErrorReason errorReason)
    {
        super(message, exception);
        super.setErorReason(errorReason);

    }

    public JPHolidayException(Throwable exception, Object... parameters)
    {
        super(exception);
        super.setErorReason(ErrorReason.HOLIDAY, parameters);
    }
}
