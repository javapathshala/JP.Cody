/*
 * File: CreateWriteRead.java
 * Date: 22-Dec-2016
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
package com.jp.file.operations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 72010964
 */
public class CreateWriteRead {

	static File file;

	static PrintWriter pw;

	static BufferedReader br;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CreatesNewFile();
			writeingData();
			readingDate();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
		}
	}

	private static void CreatesNewFile() throws IOException {
		file = new File("TestFile.txt");
		file.createNewFile();
	}

	private static void writeingData() throws FileNotFoundException {
		pw = new PrintWriter(file);
		pw.println("Dimit");
		pw.println("Chadha");
		pw.flush();
		pw.close();
		System.out.println("Data Written");
	}

	private static void readingDate() throws FileNotFoundException, IOException {
		System.out.println("Start Reading Data");

		FileReader fr = new FileReader(file);
		br = new BufferedReader(fr);
		String data;
		while ((data = br.readLine()) != null) {
			System.out.println(data);
			CharSequence c = "<script";
			if (data.contains(c)) {
				System.out.println("Scxr found");
			}
		}
	}
}
