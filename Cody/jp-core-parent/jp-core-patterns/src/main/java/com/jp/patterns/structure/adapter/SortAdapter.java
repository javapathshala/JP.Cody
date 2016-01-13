/*
 * Copyright (c) JavaPathshala.com. All rights reserved. No parts of this source
 * code can be reproduced without written consent from Java Pathshala.com. Visit
 * us at www.javapathshala.com File: SortAdapter.java Date: Sep 25, 2014
 */
package com.jp.patterns.structure.adapter;

import java.util.Arrays;
import java.util.List;

/**
 * @author Dimit Chadha
 */
public class SortAdapter implements SortArrayClient {

	private SortListTarget sortListTarget;

	public SortAdapter(SortListTarget sortListTarget) {
		this.sortListTarget = sortListTarget;
	}

	@Override
	public Integer[] sortNumbers(Integer[] numbers) {
		List<Integer> listIntegers = Arrays.asList(numbers);
		sortListTarget.sortList(listIntegers);
		System.out.println("Getting List from [] by using adapter Pattern");
		return (Integer[]) listIntegers.toArray();
	}

}