/*
 * File: SimpleMongo.java
 * Date: 20-Oct-2015
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
package com.jp.nosql.mongo.db;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class SimpleMongo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// To connect to mongodb server
			MongoClient mongoClient = new MongoClient("localhost", 27017);

			displayDBNames(mongoClient);

			DB db = connectToDB(mongoClient);

			DBCollection dbcol = createCollection(db);

			insertData(dbcol);

			retriveData(db);

			query(db);

			// dbcol = updateDoc(dbcol);
			//
			// retriveData(db);

		} catch (UnknownHostException uh) {
			uh.printStackTrace();
		}

	}

	private static void query(DB db) {
		DBObject query = BasicDBObjectBuilder.start().add("Who", "Dimit Chadha").get();
		DBCursor cursor = db.getCollection("testColl").find(query);
		while (cursor.hasNext()) {
			System.out.println("Query Result : " + cursor.next());
		}
	}

	private static DBCollection updateDoc(DBCollection dbcol) {
		DBCursor cursor = dbcol.find();
		BasicDBObject newO = new BasicDBObject("likes", 200);
		while (cursor.hasNext()) {
			DBObject updateDocument = cursor.next();
			dbcol.update(updateDocument, newO);
		}
		return dbcol;
	}

	private static void retriveData(DB db) {
		// get collection
		DBCollection dbCol = db.getCollection("testColl");
		System.out.println();
		DBCursor cursor = dbCol.find();
		int i = 1;
		System.out.println("Collection Size = " + dbCol.count());
		while (cursor.hasNext()) {
			System.out.println("Inserted Docu " + i);
			System.out.println(cursor.next());
			i++;
		}

	}

	private static void insertData(DBCollection dbcol) {
		BasicDBObject doc = new BasicDBObject();
		doc.append("Who", "Dimit Chadha");
		doc.append("When", 1980);
		doc.append("What", "Do not Know");
		dbcol.insert(doc);
		System.out.println("Document inserted Sucessfully");
		System.out.println("Collection Size = " + dbcol.count());

	}

	private static DBCollection createCollection(DB db) {
		DBCollection col = db.createCollection("testColl", null);
		System.out.println("Collection '" + col.getFullName() + "' created Successfully");
		System.out.println("Collection Size = " + col.count());
		return col;

	}

	private static void displayDBNames(MongoClient mongoClient) {
		List<String> dbs = mongoClient.getDatabaseNames();
		System.out.println("List of databases -->" + dbs);
	}

	private static DB connectToDB(MongoClient mongoClient) {
		// Connect to your databases
		DB db = mongoClient.getDB("jp");
		System.out.println(db.getName());
		System.out.println("Connect to database successfully");
		boolean auth = db.authenticate("username", "password".toCharArray());
		System.out.println("Authentication: " + auth);
		return db;
	}

}
