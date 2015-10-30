/*
 * File: Triangle.java
 * Date: 08-May-2013
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
package com.jp.spring.api.depend.complex;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author dimit.chadha
 */
public class Triangle implements ApplicationContextAware, BeanNameAware {

	private Point pointA;

	private Point pointB;

	private Point pointC;

	private List<Point> listTag;

	private Point autoWireA;

	private Point autoWireB;

	private Point autoWireC;

	private ApplicationContext context = null;

	/**
	 * @return the pointA
	 */
	public Point getPointA() {
		return pointA;
	}

	/**
	 * @param pointA
	 *            the pointA to set
	 */
	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}

	/**
	 * @return the pointB
	 */
	public Point getPointB() {
		return pointB;
	}

	/**
	 * @param pointB
	 *            the pointB to set
	 */
	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}

	/**
	 * @return the pointC
	 */
	public Point getPointC() {
		return pointC;
	}

	/**
	 * @param pointC
	 *            the pointC to set
	 */
	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}

	public void draw() {
		System.out.println(" Triangle Drawn Perfectly");
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Triangle [pointA=" + pointA + ", pointB=" + pointB + ", pointC=" + pointC + ", listTag=" + listTag + ", autoWireA="
				+ autoWireA + ", autoWireB=" + autoWireB + ", autoWireC=" + autoWireC + "]";
	}

	/**
	 * @return the listTag
	 */
	public List<Point> getListTag() {
		return listTag;
	}

	/**
	 * @param listTag
	 *            the listTag to set
	 */
	public void setListTag(List<Point> listTag) {
		this.listTag = listTag;
	}

	/**
	 * @return the autoWireA
	 */
	public Point getAutoWireA() {
		return autoWireA;
	}

	/**
	 * @param autoWireA
	 *            the autoWireA to set
	 */
	public void setAutoWireA(Point autoWireA) {
		this.autoWireA = autoWireA;
	}

	/**
	 * @return the autoWireB
	 */
	public Point getAutoWireB() {
		return autoWireB;
	}

	/**
	 * @param autoWireB
	 *            the autoWireB to set
	 */
	public void setAutoWireB(Point autoWireB) {
		this.autoWireB = autoWireB;
	}

	/**
	 * @return the autoWireC
	 */
	public Point getAutoWireC() {
		return autoWireC;
	}

	/**
	 * @param autoWireC
	 *            the autoWireC to set
	 */
	public void setAutoWireC(Point autoWireC) {
		this.autoWireC = autoWireC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext
	 * (org.springframework.context.ApplicationContext)
	 */
//	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

	public void getBeanAware() {
		Triangle triangle = (Triangle) context.getBean("triangle2");
		System.out.println(triangle.getPointA());
		System.out.println(triangle.getPointB());
		System.out.println(triangle.getPointC());

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.beans.factory.BeanNameAware#setBeanName(java.lang
	 * .String)
	 */
//	@Override
	public void setBeanName(String beanName) {
		System.out.println("Bean Name : " + beanName);

	}
}
