/*
 * File: Course.java Date: 02-Aug-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.lazy;

/**
 * @author dimit.chadha
 */
public class Student extends DomainObject {

	private Long id;
	private VirtualList courses;

	public Student(Long id) {
		this.id = id;
	}

	public VirtualList getCourses() {
		return courses;
	}

	public void setCourses(VirtualList courses) {
		this.courses = courses;
	}
}
