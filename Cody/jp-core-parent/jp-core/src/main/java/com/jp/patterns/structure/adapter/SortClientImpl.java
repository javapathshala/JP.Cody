/*
 * Copyright (c) JavaPathshala.com.
 * All rights reserved.
 *
 * No parts of this source code can be reproduced without written consent from
 * Java Pathshala.com. Visit us at www.javapathshala.com
 *
 * File: SortClientImpl.java
 * Date: Sep 25, 2014
 */
package com.jp.patterns.structure.adapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Dimit Chadha
 *
 */
public class SortClientImpl implements SortArrayClient {

	@Override
	public Integer[] sortNumbers(Integer[] numbers) {
		List<Integer> asList = Arrays.asList(numbers);
		Collections.sort(asList);

		return (Integer[]) asList.toArray();
	}


}
