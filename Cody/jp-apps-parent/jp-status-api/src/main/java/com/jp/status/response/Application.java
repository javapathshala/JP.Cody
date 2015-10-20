/*
 * File: Application.java Date: 23-Apr-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.status.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * An {@link AbstractStatusCollection} extension that encapsulates a
 * {@link List} of {@link Component} instances.
 * 
 * @author Dimit Chadha
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType
public class Application extends AbstractStatusCollection<Component> {

	@XmlElement(name = "component")
	@Override
	public List<Component> getElements() {
		return super.getElements();
	}
}
