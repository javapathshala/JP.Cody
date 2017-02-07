/*
 * Copyright (c) Java Pathshala
 * All rights reserved.
 *
 * No parts of this source code can be reproduced without written consent from
 * Java Pathshala
 */
package com.jp.efficacy.ws;

/**
 *
 * The fundamental success or failure of a service public API request.
 *
 * @see AbstractResponse
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public enum ResponseStatus
{

    /**
     * Indicates the request was successful.
     */
    SUCCESS,
    /**
     * Indicates the specific request failed. This status means that only the current request failed.
     */
    REQUEST_FAILURE;
}
