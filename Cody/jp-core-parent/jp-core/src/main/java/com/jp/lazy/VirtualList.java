/*
 * File: VirtualList.java Date: 02-Aug-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.lazy;

import java.util.List;

/**
 * @author dimit.chadha
 */
public class VirtualList {
	private List<DomainObject> source;
	private VirtualListLoader loader;

	public VirtualList(VirtualListLoader loader) {
		this.loader = loader;
	}

	public List<DomainObject> getSource() {
		if (source == null)
			source = loader.load();
		return source;
	}

	public int size() {
		return getSource().size();
	}

	public boolean isEmpty() {
		return getSource().isEmpty();
	}
}
