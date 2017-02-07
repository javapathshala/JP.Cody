/*
 * Copyright (c) Java Pathshala
 * All rights reserved.
 *
 * No parts of this source code can be reproduced without written consent from
 * Java Pathshala
 */
package com.jp.efficacy.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * Abstract Request Object for Web Service Request
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public abstract class AbstractRequest implements Serializable
{

    //<editor-fold defaultstate="collapsed" desc="Fields">
    @XmlAttribute(required = false)
    private String id;

    @XmlElement(required = false)
    private String clientCode;

    @XmlElement(required = false)
    private String timeZone;
    
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public AbstractRequest()
    {
    }

    public AbstractRequest(String clientCode, String timeZone)
    {
        this.clientCode = clientCode;
        this.timeZone = timeZone;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getClientCode()
    {
        return clientCode;
    }

    public void setClientCode(String clientCode)
    {
        this.clientCode = clientCode;
    }

    public String getTimeZone()
    {
        return timeZone;
    }

    public void setTimeZone(String timeZone)
    {
        this.timeZone = timeZone;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
    //</editor-fold>
}
