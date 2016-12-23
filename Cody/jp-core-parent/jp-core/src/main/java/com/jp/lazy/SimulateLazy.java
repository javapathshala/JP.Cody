/*
 * File: SimulateLazy.java Date: 02-Aug-2013 This source code is part of Java
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
public class SimulateLazy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StudentMapper studentMapper = new StudentMapper();
		Student student = (Student) studentMapper.doLoad(1L);
		VirtualList courseVL = student.getCourses();
		List<DomainObject> courses = courseVL.getSource();
		for (DomainObject course : courses) {
			Course c = (Course) course;
			System.out.println(c.getId());
			System.out.println(c.getName());
		}
	}

}
