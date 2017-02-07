/*
 * Copyright (c) Java Pathshala
 * All rights reserved.
 *
 * No parts of this source code can be reproduced without written consent from
 * Java Pathshala
 */
package com.jp.efficacy.ws.info;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.apache.cxf.jaxrs.ext.MessageContext;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class WSInfo implements BuildInfoResource, ResourceDownload
{

    @Context
    private MessageContext context;

    @Override
    public Response list()
    {
        HttpServletRequest req = context.getHttpServletRequest();

        WSBuildInfo wsBuildInfo = new WSBuildInfo();
        wsBuildInfo.setAccessedTime(new Date().toString());
        wsBuildInfo.setEnvironment(req.getServerName());
        wsBuildInfo.setJavaVersion(System.getProperty("java.version"));
        wsBuildInfo.setTomVersion(context.getServletContext().getServerInfo());
        ServletContext application = context.getServletConfig().getServletContext();
        InputStream inputStream = application.getResourceAsStream("/META-INF/MANIFEST.MF");
        try
        {
            Manifest manifest = new Manifest(inputStream);
            Attributes attributes = manifest.getMainAttributes();

            wsBuildInfo.setSvnRevisionNo(attributes.getValue("buildVersion"));
            wsBuildInfo.setMode(attributes.getValue("mode"));
            wsBuildInfo.setBuildDate(attributes.getValue("buildDate"));
            wsBuildInfo.setComponent(attributes.getValue("component"));
        }
        catch (IOException ex)
        {
            System.out.println("error");
        }
        Response.ResponseBuilder responseBuilder = Response.ok(wsBuildInfo);
        return responseBuilder.build();
    }

    @Override
    public Response download() throws Exception
    {
        HttpServletRequest req = context.getHttpServletRequest();
        String fileName = "\\\\vdalvwappp022.vertex.client\\d$\\.m2\\repository\\com\\vertexgroup\\JP\\";
        try
        {
            ServletContext application = context.getServletConfig().getServletContext();
            InputStream inputStream = application.getResourceAsStream("/META-INF/MANIFEST.MF");
            Manifest manifest = new Manifest(inputStream);
            Attributes attributes = manifest.getMainAttributes();
            fileName = fileName + attributes.getValue("clientPath");
            System.out.println(fileName);

        }
        catch (IOException ex)
        {
            throw new Exception(ex);
        }
        File file = new File(fileName);
        ResponseBuilder responseBuilder = Response.ok((Object) file);
        responseBuilder.header("Content-Disposition", "attachment; filename=vts-contact-health-client-1.0-SNAPSHOT.jar");
        return responseBuilder.build();
    }
}
