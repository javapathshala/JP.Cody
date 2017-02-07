/*
 * Copyright (c) Java Pathshala
 * All rights reserved.
 *
 * No parts of this source code can be reproduced without written consent from
 * Java Pathshala
 */
package com.jp.efficacy.ws;

/**
 * A lower-level failure reason code returned from public API requests, indicating the reason why the request
 * failed.
 *
 * @see AbstractResponse
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public enum ResponseReason
{
    /**
     * The request was successful.
     */
    OK,
    /**
     * Request has invalid parameters i.e. nulls
     */
    INVALID_REQUEST_PARAMETERS,
    /**
     * No Client configurations found
     */
    CLIENT_NOT_REGISTERED,
    /**
     * The request failed, however the nature of the failure needs to be
     * determine by analyzing the server-side logs of the work item service.
     */
    REASON_UNDETERMINED_SEE_LOGS;

}
