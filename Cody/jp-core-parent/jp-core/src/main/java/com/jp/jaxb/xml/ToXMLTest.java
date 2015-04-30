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

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.jp.jaxb.Book;
import com.jp.jaxb.BookStore;
import com.jp.jaxb.BookStoreType;

/**
 * @author Dimit Chadha
 */
public class ToXMLTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ToXMLTest toXmlTest = new ToXMLTest();

		Book book = createBook(1);
		toXmlTest.transform(book);
		System.out.println("###############");

		// Create Book Store

		BookStore bookStore = new BookStore();
		bookStore.setStoreName("Surya Bookstore");
		bookStore.setLocation("Frankfurt Airport");
		BookStoreType bookStoreType = new BookStoreType();
		bookStoreType.setType("Sports");
		bookStoreType.setValue("Cricket");
		bookStore.setBookStoreType(bookStoreType);

		ArrayList<Book> bookList = createBookList();
		bookStore.setBookList(bookList);

		toXmlTest.transformComplex(bookStore);

	}

	/**
	 * 
	 */
	private static Book createBook(int i) {
		Book book = new Book();
		book.setIsbn("978-" + i + 2);
		book.setName("The Game-" + i);
		book.setAuthor("Dimit-" + i);
		book.setPublisher("Surya-" + i);
		return book;
	}

	/**
	 * @return
	 */
	private static ArrayList<Book> createBookList() {
		ArrayList<Book> bookList = new ArrayList<Book>();
		bookList.add(createBook(1));
		bookList.add(createBook(2));
		bookList.add(createBook(3));
		return bookList;
	}

	/**
	 * 
	 */
	private void transform(Book book) {
		// create book
		try {
			// create JAXB context and instantiate marshaller
			JAXBContext context = JAXBContext.newInstance(Book.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// Transform to Out.
			m.marshal(book, System.out);

			// Transform to XML File
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param bookList
	 */
	private void transformComplex(BookStore bookStore) {
		// create book
		try {
			// create JAXB context and instantiate marshaller
			JAXBContext context = JAXBContext.newInstance(BookStore.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// Transform to Out.
			m.marshal(bookStore, System.out);

			// Transform to XML File
			toXMLFile(m,bookStore);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param m
	 * @param bookStore 
	 */
	private void toXMLFile(Marshaller m, BookStore bookStore) {
		Writer w = null;
		try {
			w = new FileWriter("./output/jaxb/xml/bookstore.xml");
			m.marshal(bookStore, w);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} finally {
			try {
				w.close();
			} catch (Exception e) {
			}
		}

	}

}
