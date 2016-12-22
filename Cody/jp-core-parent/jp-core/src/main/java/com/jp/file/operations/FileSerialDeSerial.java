/*
 * File: FileSerialDeSerial.java
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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Application is run without making serialization, program throws
 * java.io.NotSerializableException: com.jp.file.operations.FileSerialDeSerial
 * 
 * 
 * @author 72010964
 */
public class FileSerialDeSerial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7222190338367823584L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileSerialDeSerial serial = new FileSerialDeSerial();
		try {
			serialised(serial);
			deSerialised(serial);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	private static void serialised(FileSerialDeSerial serial) throws IOException {
		FileOutputStream fos = new FileOutputStream("TestFile.txt");
		ObjectOutputStream os = new ObjectOutputStream(fos);
		os.writeObject(serial);// serialization goes here
		os.close();
		System.out.println(serial);
	}

	private static void deSerialised(FileSerialDeSerial serial) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("TestFile.txt");
		ObjectInputStream is = new ObjectInputStream(fis);
		serial = (FileSerialDeSerial) is.readObject();
		is.close();
		System.out.println(serial);
	}

}
