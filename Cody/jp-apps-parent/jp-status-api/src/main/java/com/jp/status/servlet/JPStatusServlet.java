/*
 * File: JPStatusServlet.java Date: 23-Apr-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.status.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import com.jp.status.ComponentStatusGeneratorRegistry;
import com.jp.status.io.StatusWriter;
import com.jp.status.response.Application;

/**
 * An {@link HttpServlet} extension that renders the current application status.
 * <p/>
 * This {@link HttpServlet} maps itself to the URI pattern {@code /status}. By
 * default the response content is HTML (for operations staff during
 * troubleshooting). Adding an {@code style} query parameter will transform the
 * XML response in to other formats.
 * 
 * @author Dimit Chadha
 */
@ServletSecurity(@HttpConstraint())
@WebServlet(urlPatterns = "/status")
public class JPStatusServlet extends HttpServlet {

	private static final long serialVersionUID = 2200158414192860931L;
	// <editor-fold defaultstate="collapsed" desc="Fields">
	/**
	 * A {@link StatusWriter} used to generate and write the current status.
	 *
	 * @since 1.0
	 */
	private final StatusWriter statusWriter = new StatusWriter();

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Constructors">
	/**
	 * Creates a new {@link StatusServlet} instance.
	 *
	 * @since 1.0
	 * @throws JAXBException
	 *             as defined in {@link StatusWriter#StatusWriter()}
	 * @throws TransformerException
	 *             as defined in {@link StatusWriter#StatusWriter()}
	 */
	public JPStatusServlet() throws JAXBException, TransformerException {
		super();
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed"
	// desc="Servlet Method Implementations">
	/**
	 * Generates an XML response representing the current status. The XML schema
	 * document used to govern the response can be retrieved by supplying a
	 * request parameter of {@code xsd}.
	 *
	 * @see StatusWriter#reportSchema(java.io.Writer)
	 * @see StatusWriter#reportStatus(com.vertexgroup.telephony.status.response.Application,
	 *      java.io.Writer)
	 * @see StatusWriter#reportStatus(com.vertexgroup.telephony.status.response.Application,
	 *      java.io.Writer, java.lang.String)
	 * @since 1.0.1
	 * @throws IOException
	 *             if an I/O error occurs.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Set the character encoding
		resp.setCharacterEncoding("UTF-8");
		// Get the response stream
		Writer responseWriter = resp.getWriter();
		// If an XSD parameter is present
		if (req.getParameterMap().containsKey("xsd")) {
			// Set the content type
			resp.setContentType("text/xml");
			// Display the schema
			statusWriter.reportSchema(responseWriter);
		} else {
			// Report the status
			Application status = ComponentStatusGeneratorRegistry.getStatus();
			// Get the style parameter from the request
			String styleSheet = req.getParameter("style");
			// If no style parameter is present
			if (styleSheet == null || styleSheet.isEmpty()) {
				styleSheet = "html";
			}
			// Determine the classpath location of the XSL resource
			String xslResource = String.format("vts-status-api/%s.xsl", styleSheet);
			// Write the content to the response, using the style sheet for
			// transformation
			statusWriter.reportStatus(status, responseWriter, xslResource);
		}
		// Close the output
		responseWriter.close();
	}

	/**
	 * {@inheritDoc} This implementation sets the name of {@link ServletContext}
	 * to the {@link ComponentStatusGeneratorRegistry#applicationName}.
	 *
	 * @since 1.0
	 * @throws ServletException
	 *             if an exception occurs that interrupts the servlet's normal
	 *             operation.
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// Delegate initialization to the super class
		super.init(config);
		// Get the servlet context
		ServletContext context = config.getServletContext();
		// Get the name of the servlet context
		String name = context.getServletContextName();
		// Set the context name to the reporter
		ComponentStatusGeneratorRegistry.setApplicationName(name);
	}
}
