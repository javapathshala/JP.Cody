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
public enum ErrorReason
{

    HOLIDAY("Today - {0} is a Statutory Holiday ! No Request ! No Result !"),
    
    
    DESIRED_FILE_NOT_FOUND("Desired file {0} not found for Load Date {1}"),

    
    DESIRED_FILE_PARSING_ERROR("Error while parsing request file -> {0} "),
    
    DESIRED_FILE_CREATION_ERROR("Error while creating/writing result file -> {0} "),
    
    REASON_UNDETERMINED_SEE_LOGS("Visit logs for more information");

    /**
     *
     */
    public String value;

    private ErrorReason(String value)
    {
        this.value = value;
    }
}
