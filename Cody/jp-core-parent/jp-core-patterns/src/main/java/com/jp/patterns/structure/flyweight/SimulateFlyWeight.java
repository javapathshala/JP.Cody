/*
 * File: SimulateFlyWeight.java Date: 22-Aug-2013 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.patterns.structure.flyweight;

/**
 * @author dchadha
 */
public class SimulateFlyWeight {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LineFlyweightFactory factory = new LineFlyweightFactory();
		LineFlyweight redLine = factory.getLine(Color.RED);
		LineFlyweight blueLine = factory.getLine(Color.BLUE);
		redLine.draw(new Point(100, 200));
		blueLine.draw(new Point(300, 400));

		LineFlyweight blueLine2 = factory.getLine(Color.BLUE);
		blueLine2.draw(new Point(500, 600));
	}

}
