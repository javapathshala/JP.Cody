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
public class WSDownload implements ResourceDownload
{

    @Context
    private MessageContext context;

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
