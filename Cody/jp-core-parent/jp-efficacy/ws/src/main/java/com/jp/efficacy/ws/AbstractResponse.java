/*
 * Copyright (c) Java Pathshala
 * All rights reserved.
 *
 * No parts of this source code can be reproduced without written consent from
 * Java Pathshala
 */
package com.jp.efficacy.ws;

import java.io.Serializable;

/**
 * The (abstract) base class for all Web Service Response objects.
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public abstract class AbstractResponse implements Serializable
{

    /**
     * A high-level Response status indicating fundamental success or failure of the
     * request.
     */
    private ResponseStatus responseStatus;

    /**
     * When {@link #responseStatus} is NOT {@link ResponseStatus#SUCCESS}, this
     * reason code provides a finer-grained reason for why the request failed.
     */
    private ResponseReason responseReason;

    /**
     * When {@link #responseStatus} is NOT {@link ResponseStatus#SUCCESS}, this
     * field may contain additional information about why the request failed, in
     * the form of a textual message. If the request was successful, this field
     * will be null.
     */
    private String message;

    /**
     * Default constructor, creates a blank response object.
     */
    public AbstractResponse()
    {
        // do nothing.
    }

    /**
     * Overloaded constructor, creates a new response object populated with the
     * provided field values.
     *
     * @param responseStatus the high-level status code - see {@link #responseStatus}.
     * @param responseReason the low-level failure reason code - see {@link #responseReason}.
     */
    public AbstractResponse(ResponseStatus responseStatus, ResponseReason responseReason)
    {
        this.responseReason = responseReason;
        this.responseReason = responseReason;
    }

    /**
     * Overloaded constructor, creates a new response object populated with the
     * provided field values.
     *
     * @param responseStatus the high-level status code - see {@link #responseStatus}.
     * @param responseReason the low-level failure reason code - see
     *                       {@link #responseReason}.
     * @param message        an textual message further describing the failure reason -
     *                       see {@link #message}.
     */
    public AbstractResponse(ResponseStatus responseStatus, ResponseReason responseReason, String message)
    {
        this(responseStatus, responseReason);
        this.message = message;
    }

    /**
     * Returns additional information about why the request failed, in the form
     * of a textual message. Only relevant when {@link #get} returns a
     * value other than {@link ResponseStatus#SUCCESS}.
     *
     * @return
     *
     * @see #message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * Sets or updates the additional information (in the form of a textual
     * message) about why the API request failed.
     *
     * @param value the additional failure message text string.
     * @see #getMessage
     */
    public void setMessage(String value)
    {
        this.message = value;
    }

    /**
     * Returns the associated finer-grained failure reason code when a request
     * has failed. Only relevant when {@link #getResponseStatus()} returns a value
     * other than {@link ResponseStatus#SUCCESS}.
     *
     * @return
     *
     * @see #reasonCode
     */
    public ResponseReason getResponseReason()
    {
        return responseReason;
    }

    /**
     * Sets or updates the associated finer-grained failure reason code when a
     * request has failed.
     *
     * @param value the new reason code to be set to.
     * @see #getReasonCode
     */
    public void setResponseReason(ResponseReason value)
    {
        this.responseReason = value;
    }

    /**
     * Returns a high-level status code indicating fundamental success or
     * failure of the request. When a request is successful,
     * {@link ResponseStatus#SUCCESS} will be returned. A value of
     * {@link ResponseStatus#REQUEST_FAILURE} will be returned for all
     * unsuccessful SupportService requests, and will possibly also be returned
     * for unsuccessful AgentControlService requests. A value of
     * {@link ResponseStatus#SESSION_FAILURE} will only be returned from
     * AgentControlService requests (so wont be returned for failed
     * SupportService requests).
     *
     * @return
     */
    public ResponseStatus getResponseStatus()
    {
        return responseStatus;
    }

    /**
     * Sets or updates the high-level status code indicating fundamental success
     * or failure of the request.
     *
     * @param value the new status to be set to.
     * @see #getStatusCode
     */
    public void setResponseStatus(ResponseStatus value)
    {
        this.responseStatus = value;
    }
}
