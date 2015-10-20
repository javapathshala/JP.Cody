/*
 * Copyright (c) JavaPathshala.com. All rights reserved. No parts of this source
 * code can be reproduced without written consent from Java Pathshala.com. Visit
 * us at www.javapathshala.com File: StatusGenerator.java Date: Jul 23, 2014
 */
package com.jp.patterns.behaviour.observer;

/**
 * @author Dimit Chadha
 */
public interface StatusGenerator {
	public void registerObserver(Observer observer);

	public void removeObserver(Observer observer);

	public void notifyObservers();

}
