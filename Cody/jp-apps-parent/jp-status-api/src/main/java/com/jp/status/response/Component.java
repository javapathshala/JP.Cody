/*
 * File: Component.java
 * Date: 24-Apr-2015
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

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
* An {@link AbstractStatusCollection} that encapsulates a {@link List} of
 * {@link Aspect} instances.
 *
 * @author Dimit Chadha
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType
public class Component extends AbstractStatusCollection<Aspect>{

	/**
     * {@inheritDoc}
     *
     * @since 2.1
     */
    @XmlElement(name = "aspect")
    @Override
    public List<Aspect> getElements()
    {
        return super.getElements();
    }
}
