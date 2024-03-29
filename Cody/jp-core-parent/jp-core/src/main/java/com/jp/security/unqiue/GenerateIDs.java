/*
 * File: GenerateIDs.java
 * Date: 09-Jan-2016
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
package com.jp.security.unqiue;

import java.rmi.server.UID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class GenerateIDs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// generate via random UUIDs
		System.out.println("UUID Value --> " + UUID.randomUUID());

		// generate via SecureRandom

		// Initialize SecureRandom. This is a lengthy operation, to be done only
		// upon initialization of the application
		try {
			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
			// generate a random number
			String randomNum = new Integer(prng.nextInt()).toString();
			System.out.println("Random number -->  " + randomNum);

			// get its digest
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] result = sha.digest(randomNum.getBytes());
			System.out.println("Message digest --> " + hexEncode(result));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// generate via UID
		// generated by this class are unique on the host on which they are
		// generated
		for (int idx = 0; idx < 10; ++idx) {
			UID userId = new UID();
			System.out.println("UID -->  " + userId);
		}
	}

	/**
	  * The byte[] returned by MessageDigest does not have a nice
	  * textual representation, so some form of encoding is usually performed.
	  *
	  * This implementation follows the example of David Flanagan's book
	  * "Java In A Nutshell", and converts a byte array into a String
	  * of hex characters.
	  *
	  * Another popular alternative is to use a "Base64" encoding.
	  */
	static private String hexEncode(byte[] aInput) {
		StringBuilder result = new StringBuilder();
		char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		for (int idx = 0; idx < aInput.length; ++idx) {
			byte b = aInput[idx];
			result.append(digits[(b & 0xf0) >> 4]);
			result.append(digits[b & 0x0f]);
		}
		return result.toString();
	}
}
