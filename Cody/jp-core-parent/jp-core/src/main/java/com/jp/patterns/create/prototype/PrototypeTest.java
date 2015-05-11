/*
 * Copyright (c) JavaPathshala.com. All rights reserved. No parts of this source
 * code can be reproduced without written consent from Java Pathshala.com. Visit
 * us at www.javapathshala.com File: PrototypeTest.java Date: Oct 1, 2014
 */
package com.jp.patterns.create.prototype;

/**
 * @author Dimit Chadha
 */
public class PrototypeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VehicleData.loadData();
		Vehicle clonedVeh = VehicleData.getVehicle("2-bike");
		System.out.println("Vehicle : " + clonedVeh.getType());
	}

}
