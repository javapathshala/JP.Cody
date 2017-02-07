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
public class JPHaulerException extends JPBaseException
{

    
     public JPHaulerException()
    {
        super();
    }
    
     public JPHaulerException(String message)
    {
        super(message);
    }

    public JPHaulerException(Throwable cause)
    {
        super(cause);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public JPHaulerException(String message, Throwable cause)
    {
        super(message, cause);
    }
   
}
