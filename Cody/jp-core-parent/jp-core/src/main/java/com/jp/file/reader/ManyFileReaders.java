/*
 * File: ManyFileReaders.java
 * Date: 15-Oct-2015
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention
 * orders
 * and to prosecute the authors of any infraction.
 * Visit us at www.javapathshala.com
 */
package com.jp.file.reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class ManyFileReaders {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ManyFileReaders manyFileReaders = new ManyFileReaders();
		//manyFileReaders.processBufferReader();
		manyFileReaders.processStreamReader("BigFile.csv");
	}

	/**
	 * 
	 */
	private void processBufferReader() {
		BufferedReader br = null;
		try {

			String sCurrentLine;
			/**FileReader (for text files) should usually be wrapped in a
			 BufferedFileReader. This saves up data so you can deal with it a
			 line at a time or whatever instead of character by character
			(which usually isn't much use).
			 * 
			 */
			br = new BufferedReader(new FileReader("smallFile.txt"));
			System.out.println("File Contents are  -->");
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 
	 */
	private void processStreamReader(String fileName) {
		try {
			// Use this for reading the data.
			byte[] buffer = new byte[1000];

			FileInputStream inputStream = new FileInputStream(fileName);

			// read fills buffer with data and returns
			// the number of bytes read (which of course
			// may be less than the buffer size, but
			// it will never be more).
			int total = 0;
			int nRead = 0;
			System.out.println("##### From Stream Reader #####");
			while ((nRead = inputStream.read(buffer)) != -1) {
				// Convert to String so we can display it.
				// Of course you wouldn't want to do this with
				// a 'real' binary file.
				System.out.println(new String(buffer));
				total += nRead;
			}

			// Always close files.
			inputStream.close();

			System.out.println("Read " + total + " bytes");
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
	}

}
