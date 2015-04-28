/*
 * File: ToXMLTest.java Date: 27-Apr-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.jaxb.xml;

import java.io.FileReader;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * @author Dimit Chadha
 */
public class ToJavaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ToJavaTest toJavaTest = new ToJavaTest();
		toJavaTest.XMLToObject();
	}

	private void XMLToObject() {
		try {
			System.out.println("Output from our XML File: ");
			JAXBContext context = JAXBContext.newInstance(BookStore.class);
			Unmarshaller um = context.createUnmarshaller();
			BookStore bookstore = (BookStore) um.unmarshal(new FileReader("./output/jaxb/xml/bookstore.xml"));
			System.out.println(bookstore);
			for (int i = 0; i < bookstore.getBooksList().toArray().length; i++) {
				System.out
						.println("Book " + (i + 1) + ": " + bookstore.getBooksList().get(i).getName() + " from "
								+ bookstore.getBooksList().get(i).getAuthor() + " with publisher "
								+ bookstore.getBooksList().get(i).getPublisher());
			}

		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}

	}

}
