/*
 * Copyright (c) JavaPathshala.com. All rights reserved. No parts of this source
 * code can be reproduced without written consent from Java Pathshala.com. Visit
 * us at www.javapathshala.com File: VehicleData.java Date: Oct 1, 2014
 */
package com.jp.patterns.create.prototype;
import java.util.Hashtable;
import java.util.Map;

/**
 * Get concrete classes from database and store them in a Hash table
 * 
 * @author Dimit Chadha
 */
public class VehicleData {

	private static Map<String, Vehicle> vMap = new Hashtable<>();

	public static void loadData() {
		Car car = new Car();
		car.setId("1-car");
		vMap.put(car.getId(), car);

		Bike bike = new Bike();
		car.setId("2-bike");
		vMap.put(bike.getId(), car);

	}

	public static Vehicle getVehicle(String vId) {
		Vehicle veh = vMap.get(vId);
		return (Vehicle) veh.clone();
	}
}
