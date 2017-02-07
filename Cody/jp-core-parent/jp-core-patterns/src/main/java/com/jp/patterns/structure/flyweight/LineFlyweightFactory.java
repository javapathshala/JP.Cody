/*
 * File: LineFlyweightFactory.java Date: 22-Aug-2013 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.patterns.structure.flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dchadha
 */
public class LineFlyweightFactory {
	private List<LineFlyweight> pool;

	public LineFlyweightFactory() {
		pool = new ArrayList<LineFlyweight>();
	}

	public LineFlyweight getLine(Color c) {
		// check if we've already created a line with this color
		for (LineFlyweight line : pool) {
			if (line.getColor().equals(c)) {
				return line;
			}
		}
		// if not, create one and save it to the pool
		LineFlyweight line = new Line(c);
		pool.add(line);
		return line;
	}

}
