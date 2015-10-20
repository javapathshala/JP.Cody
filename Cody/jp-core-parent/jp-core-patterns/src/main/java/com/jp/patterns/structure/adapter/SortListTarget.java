/*
 * Copyright (c) JavaPathshala.com.
 * All rights reserved.
 *
 * No parts of this source code can be reproduced without written consent from
 * Java Pathshala.com. Visit us at www.javapathshala.com
 *
 * File: SortListTarget.java
 * Date: Sep 24, 2014
 */
package com.jp.patterns.structure.adapter;

import java.util.List;

/**
 * @author Dimit Chadha
 *
 */
public interface SortListTarget {
	List<Integer> sortList(List<Integer> listIntegers);
}
