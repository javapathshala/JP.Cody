/*
 * Copyright (c) Java Pathshala
 * All rights reserved.
 *
 * No parts of this source code can be reproduced without written consent from
 * Java Pathshala
 */
package com.jp.efficacy.ws.info;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class WSBuildInfo
{

    public String buildDate;
    public String accessedTime;
    public String environment;
    public String svnRevisionNo;
    public String javaVersion;
    public String tomVersion;
    public String mode;
    public String component;
    public String clientPath;

    public String getBuildDate()
    {
        return buildDate;
    }

    public void setBuildDate(String buildDate)
    {
        this.buildDate = buildDate;
    }

    public String getAccessedTime()
    {
        return accessedTime;
    }

    public void setAccessedTime(String accessedTime)
    {
        this.accessedTime = accessedTime;
    }

    public String getEnvironment()
    {
        return environment;
    }

    public void setEnvironment(String environment)
    {
        this.environment = environment;
    }

    public String getSvnRevisionNo()
    {
        return svnRevisionNo;
    }

    public void setSvnRevisionNo(String svnRevisionNo)
    {
        this.svnRevisionNo = svnRevisionNo;
    }

    public String getJavaVersion()
    {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion)
    {
        this.javaVersion = javaVersion;
    }

    public String getTomVersion()
    {
        return tomVersion;
    }

    public void setTomVersion(String tomVersion)
    {
        this.tomVersion = tomVersion;
    }

    public String getMode()
    {
        return mode;
    }

    public void setMode(String mode)
    {
        this.mode = mode;
    }

    public String getComponent()
    {
        return component;
    }

    public void setComponent(String component)
    {
        this.component = component;
    }

    public String getClientPath()
    {
        return clientPath;
    }

    public void setClientPath(String clientPath)
    {
        this.clientPath = clientPath;
    }

}
