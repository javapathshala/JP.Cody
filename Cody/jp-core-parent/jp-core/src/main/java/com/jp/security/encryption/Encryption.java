/*
 * File: Encryption.java
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
package com.jp.security.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class Encryption {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		// create a key generator based upon the Blowfish cipher
		KeyGenerator keygenerator = KeyGenerator.getInstance("Blowfish");

		// create a key
		SecretKey secretkey = keygenerator.generateKey();

		// create a cipher based upon Blowfish
		Cipher cipher = Cipher.getInstance("Blowfish");

		// initialise cipher to with secret key
		cipher.init(Cipher.ENCRYPT_MODE, secretkey);

		// get the text to encrypt
		String inputText = JOptionPane.showInputDialog("Input your message: ");

		// encrypt message
		byte[] encrypted = cipher.doFinal(inputText.getBytes());
		System.out.println(new String(encrypted));

		// re-initialise the cipher to be in decrypt mode
		cipher.init(Cipher.DECRYPT_MODE, secretkey);

		// decrypt message
		byte[] decrypted = cipher.doFinal(encrypted);

		// and display the results
		JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Enter text for Encryption: " + new String(encrypted) + "\n"
				+ "Decryted tag: " + new String(decrypted));

		// end example
		System.exit(0);

	}

}
