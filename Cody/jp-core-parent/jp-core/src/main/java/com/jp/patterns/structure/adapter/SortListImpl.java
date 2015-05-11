/*
 * Copyright (c) JavaPathshala.com.
 * All rights reserved.
 *
 * No parts of this source code can be reproduced without written consent from
 * Java Pathshala.com. Visit us at www.javapathshala.com
 *
 * File: SortListImpl.java
 * Date: Sep 25, 2014
 */
package com.jp.patterns.structure.adapter;
import java.util.Collections;
import java.util.List;

/**
 * @author Dimit Chadha
 *
 */
public class SortListImpl implements SortListTarget {
	
	
	@Override
	public List<Integer> sortList(List<Integer> listIntegers) {
		Collections.sort(listIntegers);
		return listIntegers;
	}
}
