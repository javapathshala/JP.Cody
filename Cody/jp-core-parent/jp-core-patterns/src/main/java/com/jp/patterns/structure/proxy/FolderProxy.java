/*
 * File: FolderProxy.java Date: 13-Sep-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.patterns.structure.proxy;

/**
 * @author dchadha
 */
public class FolderProxy implements IFolder {

	Folder simpleFolder;
	User user;

	public FolderProxy(User user) {
		this.user = user;
	}

	@Override
	public void performOperations() {
		if (user.getUserName().equalsIgnoreCase("testuser") && user.getPassword().equalsIgnoreCase("testpass")) {
			simpleFolder = new Folder();
			simpleFolder.performOperations();
		} else {
			System.out.println("You don't have access to this folder");
		}
	}



}
