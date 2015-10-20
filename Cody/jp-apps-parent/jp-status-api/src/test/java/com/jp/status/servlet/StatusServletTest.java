/*
 * File: StatusServletTest.java Date: 27-Apr-2015 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.status.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jp.status.ComponentStatusGeneratorRegistry;
import com.jp.status.base.AbstractComponentStatusGenerator;

/**
 * @author Dimit Chadha
 */

@Test
public class StatusServletTest {

	private static class MockStatusGenerator extends AbstractComponentStatusGenerator {
	}

	private JPStatusServlet servlet = new JPStatusServlet();

	public StatusServletTest() throws Exception {
	}

	public void doGetTestForStatus() throws IOException, ServletException {
		HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockRequest.getParameter("style")).thenReturn("xml");
		PrintWriter mockWriter = new PrintWriter(System.out);
		HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
		Mockito.when(mockResponse.getWriter()).thenReturn(mockWriter);
		servlet.doGet(mockRequest, mockResponse);
	}

	public void doGetTestForSchema() throws IOException, ServletException {
		Map mockMap = Mockito.mock(Map.class);
		Mockito.when(mockMap.containsKey("xsd")).thenReturn(Boolean.TRUE);
		HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockRequest.getParameterMap()).thenReturn(mockMap);
		PrintWriter mockWriter = new PrintWriter(System.out);
		HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
		Mockito.when(mockResponse.getWriter()).thenReturn(mockWriter);
		servlet.doGet(mockRequest, mockResponse);
	}

	public void doGetTestForStyle() throws IOException, ServletException {
		HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockRequest.getParameter("style")).thenReturn(null);
		PrintWriter mockWriter = new PrintWriter(System.out);
		HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
		Mockito.when(mockResponse.getWriter()).thenReturn(mockWriter);
		servlet.doGet(mockRequest, mockResponse);
	}

	public void doGetTestForCsv() throws IOException, ServletException {
		HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockRequest.getParameter("style")).thenReturn("json");
		PrintWriter mockWriter = new PrintWriter(System.out);
		HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
		Mockito.when(mockResponse.getWriter()).thenReturn(mockWriter);
		servlet.doGet(mockRequest, mockResponse);
	}

	@BeforeClass
	public void setUp() {
		ComponentStatusGeneratorRegistry.register(new MockStatusGenerator());
		ComponentStatusGeneratorRegistry.register(new MockStatusGenerator());
		ComponentStatusGeneratorRegistry.register(new MockStatusGenerator());
		ComponentStatusGeneratorRegistry.setApplicationName("UNIT_TEST_SERVLET");
	}
}
