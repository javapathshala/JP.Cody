/*
 * File: Solver.java
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

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class Solver extends RecursiveAction {

	private int[] list;
	public long result;

	public Solver(int[] array) {
		this.list = array;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.concurrent.RecursiveAction#compute()
	 */
	@Override
	protected void compute() {
		if (list.length == 1) {
			result = list[0];
		} else {
			int midpoint = list.length / 2;
			int[] l1 = Arrays.copyOfRange(list, 0, midpoint);
			int[] l2 = Arrays.copyOfRange(list, midpoint, list.length);
			Solver s1 = new Solver(l1);
			Solver s2 = new Solver(l2);
			//forkJoin(s1, s2);
			result = s1.result + s2.result;
		}

	}

}
