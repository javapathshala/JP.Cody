/*
 * File: Problem.java
 * Date: 06-Oct-2015
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
package com.jp.core.threads.forkjoin;

import java.util.Random;

/**
 * 
 * This class defines a long list of integers which defines the problem we will
 * later try to solve
 * @author Dimit Chadha
 *
 */
public class Problem {
	private final int[] list = new int[2000000];

	public Problem() {
		Random generator = new Random(19580427);
		for (int i = 0; i < list.length; i++) {
			list[i] = generator.nextInt(500000);
		}
	}

	public int[] getList() {
		return list;
	}
}
