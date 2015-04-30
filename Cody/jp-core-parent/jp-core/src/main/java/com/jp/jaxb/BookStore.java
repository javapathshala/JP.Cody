/*
 * File: BookStore.java Date: 27-Apr-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.jaxb;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Dimit Chadha
 */
// This statement means that class "Bookstore.java" is the root-element of our
// example
@XmlRootElement(namespace = "com.jp.bookStore")
@XmlType(propOrder = { "storeName", "location", "bookStoreType", "bookList" })
public class BookStore {

	// XmLElementWrapper generates a wrapper element around XML representation
	@XmlElementWrapper(name = "bookList")
	@XmlElement(name = "book")
	private ArrayList<Book> bookList;

	private String storeName;
	private String location;
	private BookStoreType bookStoreType;

	public void setBookList(ArrayList<Book> bookList) {
		this.bookList = bookList;
	}

	public ArrayList<Book> getBooksList() {
		return bookList;
	}

	public String getStoreName() {
		return storeName;
	}

	@XmlElement
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the bookStoreType
	 */
	@XmlElement
	public BookStoreType getBookStoreType() {
		return bookStoreType;
	}

	/**
	 * @param bookStoreType
	 *            the bookStoreType to set
	 */
	public void setBookStoreType(BookStoreType bookStoreType) {
		this.bookStoreType = bookStoreType;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BookStore [bookList=" + bookList + ", storeName=" + storeName + ", location=" + location + ", bookStoreType="
				+ bookStoreType + "]";
	}

}
