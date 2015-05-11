/*
 * Copyright (c) JavaPathshala.com. All rights reserved. No parts of this source
 * code can be reproduced without written consent from Java Pathshala.com. Visit
 * us at www.javapathshala.com File: DemandHolderST.java Date: Oct 1, 2014
 */
package com.jp.patterns.create.singleton;

/**
 * @author Dimit Chadha
 */
public class DemandHolderST {

	private DemandHolderST() {

	}

	private static class DemandHolderInnerST {
		public static final DemandHolderST INSTANCE = new DemandHolderST();
	}
	
	public static DemandHolderST getInstance(){
		return DemandHolderInnerST.INSTANCE;
	}

}
