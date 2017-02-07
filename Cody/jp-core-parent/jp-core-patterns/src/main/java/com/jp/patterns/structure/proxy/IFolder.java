/*
 * File: IFolder.java Date: 13-Sep-2013 This source code is part of Java
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
 * we have a folder in which you can perform various operation such as
 * copy,paste file or subfolder.In OOP terms,we have IFolder interface and
 * Folder class which provides performOperatons() method, and these are the
 * existing class and interface that we cannot change. We want to further
 * specify that only user with authorization can access it and perform
 * operations such as cut or copy files and sub folder. Read more at
 * 
 * @author dchadha
 */
public interface IFolder {
	void performOperations();
}
