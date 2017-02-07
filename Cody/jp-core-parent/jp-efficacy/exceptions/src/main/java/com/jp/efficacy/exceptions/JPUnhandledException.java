/*
 * Copyright (c) Java Pathshala
 * All rights reserved.
 *
 * No parts of this source code can be reproduced without written consent from
 * Java Pathshala
 */
package com.jp.efficacy.exceptions;

/**
 * Wraps a checked exception that was not explicitly dealt with.
 * Generally not a good thing - try and deal with exceptions as they happen. This should signify something drastic
 * has gone wrong - a bug!
 * When catching the generic java.lang.Exception, don't just construct a new UnhandledException as the caught exception
 * may already be a RuntimeException or another UncaughtException, resulting in very deep exceptions. Instead use the
 * {
 *
 * @see #wrap()} method which will only wrap it if necessary.
 * <p>
 * <pre>
 * try {
 *   // some stuff
 * } catch (Exception e) {
 *   throw UnhandledException.wrap(e);
 * }
 * </pre></p>
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class JPUnhandledException extends RuntimeException
{

    private JPUnhandledException(Throwable cause)
    {
        super(cause);
    }

    public static RuntimeException wrap(Throwable throwable)
    {
        if (throwable instanceof RuntimeException)
        {
            return (RuntimeException) throwable;
        }
        else
        {
            return new JPUnhandledException(throwable);
        }
    }
}
