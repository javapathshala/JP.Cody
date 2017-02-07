/*
 * Copyright (c) Java Pathshala
 * All rights reserved.
 *
 * No parts of this source code can be reproduced without written consent from
 * Java Pathshala
 */
package com.jp.efficacy.exceptions;

import java.text.MessageFormat;
import java.util.Date;


/**
 * Base Class for all types of exceptions for voice architecture
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public abstract class JPBaseException extends Exception
{

    private Date timeStamp;
    private String programmerComments;
    private String originalStackTrace;
    private Throwable originalException;
    private String errorCode;
    private String severity;
    private String message;

    private ErrorReason erorReason;

    public JPBaseException()
    {
        super();
    }

    public JPBaseException(String message)
    {
        super(message);
    }

    public JPBaseException(Throwable cause)
    {
        super(cause);
    }

    public JPBaseException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public JPBaseException(ErrorReason errorReason, Throwable exception, String... parameters)
    {
        super(exception);

    }

    public Date getTimeStamp()
    {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public String getProgrammerComments()
    {
        return programmerComments;
    }

    public void setProgrammerComments(String programmerComments)
    {
        this.programmerComments = programmerComments;
    }

    public String getOriginalStackTrace()
    {
        return originalStackTrace;
    }

    public void setOriginalStackTrace(String originalStackTrace)
    {
        this.originalStackTrace = originalStackTrace;
    }

    public Throwable getOriginalException()
    {
        return originalException;
    }

    public void setOriginalException(Throwable originalException)
    {
        this.originalException = originalException;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getSeverity()
    {
        return severity;
    }

    public void setSeverity(String severity)
    {
        this.severity = severity;
    }

    /**
     *
     * @return
     */
    public ErrorReason getErorReason()
    {
        return erorReason;
    }

    /**
     *
     * @param erorReason
     */
    public void setErorReason(ErrorReason erorReason)
    {
        this.erorReason = erorReason;
    }

    /**
     *
     * @param erorReason
     * @param aa
     */
    public void setErorReason(ErrorReason erorReason, Object... aa)
    {
        this.erorReason = erorReason;
        message = MessageFormat.format(erorReason.value, aa);
    }

    public String getReason()
    {
        return message;
    }

}
