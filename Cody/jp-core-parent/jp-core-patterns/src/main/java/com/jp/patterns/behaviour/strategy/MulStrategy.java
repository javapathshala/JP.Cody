/**
 * 
 */
package com.jp.patterns.behaviour.strategy;

/**
 * @author dimit.chadha
 * 
 */
public class MulStrategy implements Strategy {

	
	public int operation(int a, int b) {
		System.out.println("Calling Multiple Strategy Operation ()");
		return a * b;
	}

}
