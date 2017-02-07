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
 * Exception for catching Runtime Exceptions
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class JPSystemException extends RuntimeException
{

    public JPSystemException()
    {
    }

    public JPSystemException(String message)
    {
        super(message);
    }

    public JPSystemException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
