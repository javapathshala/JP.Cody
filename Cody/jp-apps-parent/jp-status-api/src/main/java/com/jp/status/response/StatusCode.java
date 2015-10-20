/*
 * File: StatusCode.java
 * Date: 23-Apr-2015
 *
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn 
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any 
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention orders
 * and to prosecute the authors of any infraction.
 * 
 * Visit us at www.javapathshala.com
 */
package com.jp.status.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * A constrained set of status monitoring states.
 * 
 * @author Dimit Chadha
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlEnum
@XmlType
public enum StatusCode {

	//<editor-fold defaultstate="collapsed" desc="Values">
    /**
     * Indicates that the monitored elements has is in a degraded state.
     *
     * @since 2.1
     */
    ERROR(2),
    /**
     * Indicates that the monitored elements is in a failed state.
     *
     * @since 2.1
     */
    FATAL(3),
    /**
     * Indicates that the monitored elements is in a ideal state.
     *
     * @since 2.1
     */
    OK(0),
    /**
     * Indicates that the monitored elements is in a acceptable state.
     *
     * @since 2.1
     */
    WARN(1);
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    /**
     * The numerical representation of the {@link StatusCode} proportionate to
     * how degraded the status is.
     *
     * @since 2.1
     */
    private final int priority;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Creates a new {@link StatusCode} instance.
     *
     * @since 2.1
     * @param value the {@link #priority} of the {@link StatusCode}.
     */
    private StatusCode(int value)
    {
        this.priority = value;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    /**
     * Gets the numerical representation of the {@link StatusCode}.
     *
     * @since 2.1
     * @return the numerical representation of the {@link StatusCode}.
     */
    public int getPriority()
    {
        return priority;
    }
    //</editor-fold>
}
