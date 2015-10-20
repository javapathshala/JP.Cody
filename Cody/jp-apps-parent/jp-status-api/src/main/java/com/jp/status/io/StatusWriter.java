/*
 * File: StatusWriter.java Date: 23-Apr-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.status.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.jp.status.response.Application;

/**
 * A means for marshalling {@link Application} instances to I/O sources.
 * 
 * @author Dimit Chadha
 */
public class StatusWriter {

	// <editor-fold defaultstate="collapsed" desc="Fields">
	/**
	 * A {@link Marshaller} used to render {@link Application} objects in XML.
	 *
	 * @since 1.0
	 */
	private final Marshaller marshaller;
	/**
	 * The {@link TransformerFactory} used to generate {@link Transformer}
	 * instances that apply XSLT.
	 *
	 * @since 1.0
	 */
	private final TransformerFactory factory;

	/**
	 * Creates a new {@link StatusWriter} instance and configures the associated
	 * {@link #marshaller} instance.
	 *
	 * @see JAXBContext#createMarshaller()
	 * @see JAXBContext#newInstance(java.lang.Class[])
	 * @see Marshaller#setProperty(java.lang.String, java.lang.Object)
	 * @since 1.0
	 * @throws JAXBException
	 *             if a {@link Application} {@link JAXBContext} cannot be
	 *             created or a error occurs configuring the {@link Marshaller}
	 * @throws TransformerException
	 *             if a {@link TransformerFactory} implementation is not known
	 *             or cannot be instantiated.
	 */
	public StatusWriter() throws JAXBException, TransformerException {
		super();
		// Create the transformer factory
		factory = TransformerFactory.newInstance();
		// Create the marshaller
		marshaller = JAXBContext.newInstance(Application.class).createMarshaller();
		// Set the character encoding
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		// Enable formatted output
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	}

	/**
	 * Renders the XML schema document used to govern {@link Application}
	 * responses to the specified {@link Writer}.
	 *
	 * @see ClassLoader#getResourceAsStream(java.lang.String)
	 * @see Scanner
	 * @since 1.0
	 * @param output
	 *            the {@link Writer} to append with the XML schema document.
	 * @throws IOException
	 *             if an I/O error occurs appending the specified {@link Writer}
	 *             .
	 */
	public void reportSchema(Writer output) throws IOException {
		// Get the schema file as an resource from the current classloader
		InputStream schemaDocument = getClass().getClassLoader().getResourceAsStream("META-INF/xsd/jp-status-api.xsd");
		// Wrap the resource with a scanner
		try (Scanner scan = new Scanner(schemaDocument)) {
			// While more input lines exist in the input stream
			while (scan.hasNextLine()) {
				// Write the next line of the schema document to the output
				output.write(String.format("%s%n", scan.nextLine()));
			}
		}
	}

	/**
	 * Gets the {@link Application} status and renders it to the specified
	 * {@link Writer}. The {@link Application} status is serialized using a JAXB
	 * {@link Marshaller} instance.
	 *
	 * @see Marshaller#marshal(java.lang.Object, java.io.Writer)
	 * @since 1.0
	 * @param output
	 *            the {@link Writer} to append with the current
	 *            {@link Application} status.
	 * @throws IOException
	 *             if a {@link JAXBException} is generated when attempting to
	 *             marshal the {@link Application} status.
	 */
	public void reportStatus(Application status, Writer output) throws IOException {
		try {
			// Marshaller instances are _NOT_ inherintly thread safe
			synchronized (marshaller) {
				// Marshal the application status
				marshaller.marshal(status, output);
			}
		} catch (JAXBException e) {
			throw new IOException("Unable to marshal application status", e);
		}
	}

	/**
	 * Gets the {@link Application} status and renders it to the specified
	 * {@link Writer}. The {@link Application} status is serialized using a JAXB
	 * {@link Marshaller} instance and the resulting XML is transformed using a
	 * {@link Transformer}.
	 *
	 * @see Transformer#transform(javax.xml.transform.Source,
	 *      javax.xml.transform.Result)
	 * @see TransformerFactory#newTransformer(javax.xml.transform.Source)
	 * @param output
	 *            the {@link Writer} to append with the current
	 *            {@link Application} status.
	 * @param xslResource
	 *            the name of the classpath resource to use as an XSLT style
	 *            sheet.
	 * @throws IOException
	 *             if a {@link JAXBException} or {@link TransformerException} is
	 *             generated when attempting to marshal the {@link Application}
	 *             status.
	 */
	public void reportStatus(Application status, Writer output, String xslResource) throws IOException {
		// Wrap the specified output with a result
		Result outputResult = new StreamResult(output);
		InputStream styleSheet = null;
		try {
			// Create an XML source for transformation
			Source xmlSource = new JAXBSource(marshaller, status);
			// Get the style sheet as an resource from the current classloader
			styleSheet = getClass().getClassLoader().getResourceAsStream(xslResource);
			// Wrap the style sheet with a source
			Source xslSource = new StreamSource(styleSheet);
			// Create a new transformer instance
			Transformer transformer = factory.newTransformer(xslSource);
			// Marshaller instances are _NOT_ inherintly thread safe
			synchronized (marshaller) {
				// Transform the marshaller output to the result
				transformer.transform(xmlSource, outputResult);
			}
		} catch (JAXBException e) {
			throw new IOException("Unable to stream application status", e);
		} catch (TransformerException e) {
			throw new IOException("Unable to transform application status", e);
		} finally {
			// If the style sheet is not null
			if (styleSheet != null) {
				// Close the style sheet
				styleSheet.close();
			}
		}
	}

}
