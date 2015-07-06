/*
 * File: JsonObject.java Date: 31-Jul-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.format.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author Dimit Chadha
 */
public class JsonArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
		obj.put("Novel Name", "Godaan");
		obj.put("Author", "Munshi Premchand");

		JSONArray novelDetails = new JSONArray();
		novelDetails.add("Language: Hindi");
		novelDetails.add("Year of Publication: 1936");
		novelDetails.add("Publisher: Lokmanya Press");

		obj.put("Novel Details", novelDetails);

		System.out.print(obj);

	}

}
