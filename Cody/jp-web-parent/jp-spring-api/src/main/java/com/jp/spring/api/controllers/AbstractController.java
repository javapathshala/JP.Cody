/*
 * File: AbstractController.java
 * Date: 14-Aug-2015
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention
 * orders
 * and to prosecute the authors of any infraction.
 * Visit us at www.javapathshala.com
 */
package com.jp.spring.api.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class AbstractController {

	private String successView;
	
	@RequestMapping(value = "/SessRepl.do", method = RequestMethod.GET)
	public String SessionReplication(Model model) {
		// get session id create automatically by load balancer
		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		// get date, month, year, hour, minute, second, and millisecond
		String currDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSS").format(new Date());

		model.addAttribute("msgAttribute", "Session id is " + sessionId + " current date is " + currDate);
		return getSuccessView();
	}
	

	public String getSuccessView() {
		return successView;
	}

	public void setSuccessView(String successView) {
		this.successView = successView;
	}
	
}
