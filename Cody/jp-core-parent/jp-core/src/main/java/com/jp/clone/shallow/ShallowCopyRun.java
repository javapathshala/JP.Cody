/*
 * File: RunClone.java Date: 06-Dec-2012 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.clone.shallow;

/**
 * @author dimit.chadha
 */
public class ShallowCopyRun {

	public static void main(String... args) throws CloneNotSupportedException {

		Employee employee = new Employee();
		System.out.println("Employee Name :: " + employee.getName());
		System.out.println("Employee Designation :: " + employee.getDesignation());
		System.out.println("####################");
		System.out.println("Dept  Name :: " + employee.getDept().getDeptName());
		System.out.println("Dept ID :: " + employee.getDept().getDeptId());

		Employee cloneEm = (Employee) employee.clone();

		System.out.println("######## CLONE ############");
		System.out.println("Employee Name :: " + cloneEm.getName());
		System.out.println("Employee Designation :: " + cloneEm.getDesignation());
		System.out.println("Dept  Name :: " + cloneEm.getDept().getDeptName());
		System.out.println("Dept ID :: " + cloneEm.getDept().getDeptId());
		System.out.println("######## CLONE Updated ############");
		cloneEm.setDesignation("Technical");

		System.out.println("Employee Name :: " + cloneEm.getName());
		System.out.println("Employee Designation  normal :: " + employee.getDesignation());
		System.out.println("Employee Designation clone :: " + cloneEm.getDesignation());
		System.out.println("Dept  Name :: " + cloneEm.getDept().getDeptName());
		System.out.println("Dept ID :: " + cloneEm.getDept().getDeptId());
		System.out.println("######## CLONE Updated for dept ############");
		cloneEm.getDept().setDeptId("002");
		cloneEm.getDept().setDeptName("clone chnages");
		System.out.println("Dept  Name cloe :: " + cloneEm.getDept().getDeptName());
		System.out.println("Dept ID  clone :: " + cloneEm.getDept().getDeptId());

		System.out.println("Dept  Name :: " + employee.getDept().getDeptName());
		System.out.println("Dept ID :: " + employee.getDept().getDeptId());
		System.out.println("As this is shallow copy.. both clone & orignail dept become same ");
	}
}
