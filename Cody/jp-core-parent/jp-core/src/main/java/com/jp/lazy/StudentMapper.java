/*
 * File: StudentMapper.java Date: 02-Aug-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.lazy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dimit.chadha
 */
public class StudentMapper {
	public static class CourseLoader implements VirtualListLoader {
		private Long id;

		public CourseLoader(Long id) {
			this.id = id;
		}

		public List<DomainObject> load() {
			// Load the course using the
			// student id supplier. Now returning dummy
			List<DomainObject> courses = new ArrayList<DomainObject>();
			Course c1 = new Course();
			c1.setId(1L);
			c1.setName("Mgmt");
			courses.add(c1);
			Course c2 = new Course();
			c2.setId(2L);
			c2.setName("IT");
			courses.add(c2);
			return courses;
		}
	}

	protected DomainObject doLoad(Long id) {
		Student result = new Student(id);
		result.setCourses(new VirtualList(new CourseLoader(id)));
		return result;
	}
}
