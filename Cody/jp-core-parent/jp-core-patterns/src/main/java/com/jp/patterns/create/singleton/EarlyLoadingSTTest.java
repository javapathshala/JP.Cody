/*
 * Copyright (c) JavaPathshala.com. All rights reserved. No parts of this source
 * code can be reproduced without written consent from Java Pathshala.com. Visit
 * us at www.javapathshala.com File: RunSimpleST.java Date: Sep 30, 2014
 */
package com.jp.patterns.create.singleton;


/**
 * @author Dimit Chadha
 */
public class EarlyLoadingSTTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EarlyLoadingST sst1 = EarlyLoadingST.getInstance();
		System.out.println("Fisrt Instance Created::" + sst1);
		System.out.println("Creating Second Instance");
		EarlyLoadingST sst2 = EarlyLoadingST.getInstance();
		System.out.println("Second Instance can't be Created");
		System.out.println("Second Instance Not Created. 'sst2 is same as sst1' - " + sst2);
	}

}
