/**
 * 
 */
package com.jp.patterns.behaviour.strategy;

/**
 * @author dimit.chadha
 * 
 */
public class AddStrategy implements Strategy {

	public int operation(int a, int b) {
		System.out.println("Calling Add Strategy Operation ()");
		return a + b;
	}

}
