/*
 * File: FormatJson.java Date: 06-Jul-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.format.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.format.jaxb.Book;

/**
 * This class translates a given object e.g {@link Book} to json format.
 * 
 * @author Dimit Chadha
 */
public class FormatJson {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FormatJson formatJson = new FormatJson();

		Book book = new Book();
		book.setIsbn("9781");
		book.setName("The Game");
		book.setAuthor("Dimit-");
		book.setPublisher("Surya-");

		formatJson.format(book);

	}

	/**
	 * @param book
	 */
	private void format(Book record) {
		ObjectMapper MAPPER = new ObjectMapper();
		try {
			System.out.println(MAPPER.writeValueAsString(record));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
