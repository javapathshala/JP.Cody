/*
 * Copyright (c) Java Pathshala
 * All rights reserved.
 *
 * No parts of this source code can be reproduced without written consent from
 * Java Pathshala
 */
package com.jp.efficacy.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public final class CommonUtil
{

    /**
     *
     * @param value that needs to be tested for not-null or not-empty String
     * @return true if the String has at least one non white space character.
     */
    public static boolean isNotNull(String value)
    {
        return value != null && value.trim().length() > 0;
    }

    /**
     *
     * @param value that needs to be tested for null or empty String
     * @return true if the String does not have any non white space character.
     */
    public static boolean isNull(String value)
    {
        return !isNotNull(value);
    }

    /**
     * Check collection for null & Empty
     *
     * @param collection
     * @return
     */
    public static boolean IsCollectionNotNull(Collection<?> collection)
    {
        return collection != null && !collection.isEmpty();
    }

    /**
     *
     * @param value
     * @param delim
     * @return
     */
    public static String[] convertCommaToArray(String value, String delim)
    {
        return value.split(delim);
    }

    public static List<?> convertStringToList(String... values)
    {
        return Arrays.asList(values);
    }
}
