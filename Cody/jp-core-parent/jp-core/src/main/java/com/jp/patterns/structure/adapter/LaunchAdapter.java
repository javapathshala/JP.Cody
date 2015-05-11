/*
 * Copyright (c) JavaPathshala.com. All rights reserved. No parts of this source
 * code can be reproduced without written consent from Java Pathshala.com. Visit
 * us at www.javapathshala.com File: LaunchAdapter.java Date: Sep 25, 2014
 */
package com.jp.patterns.structure.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Dimit Chadha
 */
public class LaunchAdapter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Testing Third party Functionality");
		SortListTarget sortListTarget = new SortListImpl();
		List<Integer> unList = new ArrayList<Integer>();
		unList.add(3);
		unList.add(1);
		unList.add(10);
		unList.add(5);
		unList.add(7);
		System.out.println("UnSorted List is : " + unList);
		List<Integer> sortedList = sortListTarget.sortList(unList);
		System.out.println("Sorted List is : " + sortedList);
		System.out.println("############################");

		System.out.println("Testing Client Code Functionality");
		SortArrayClient sortedArrayClient = new SortClientImpl();
		Integer[] unSortedNumbers = { 49, 34, 1, 58, 2, 3, 9 };
		System.out.println("Unsorted Array is : " + Arrays.toString(unSortedNumbers));
		Integer[] sortedNumbers = sortedArrayClient.sortNumbers(unSortedNumbers);
		System.out.println("sorted Array is : " + Arrays.toString(sortedNumbers));
		System.out.println("############################");

		// now creating calling adapter

		SortAdapter sortAdapter = new SortAdapter(sortListTarget);
		Integer[] sn = sortAdapter.sortNumbers(unSortedNumbers);
		System.out.println("Sorted Numbers using Adapter : " + Arrays.toString(sn));
	}

}
