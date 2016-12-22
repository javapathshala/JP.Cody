/*
 * File: DataMapper.java
 * Date: 22-Dec-2016
 *
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn 
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any 
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention orders
 * and to prosecute the authors of any infraction.
 * 
 * Visit us at www.javapathshala.com
 */
package com.jp.dto.mapping;

import java.lang.reflect.Method;

/**
 * @author 72010964
 */
public class DataMapper {

	String PREFIX = "get";

	String GETCLASS = "getClass";

	String IS = "is";

	String BLANK = "";


	public Object mapData(Object sourceObj, Object destObj) {
		try {
			Class<?> sourceClass = sourceObj.getClass();
			Method[] sourceMethods = sourceClass.getMethods();
			Method method = null;
			String sourceMethodName = BLANK;
			int size = sourceMethods.length;
			Object getValue = null;
			for (int i = 0; i < size; i++) {
				method = (Method) sourceMethods[i];
				sourceMethodName = method.getName();
				System.out.println(sourceMethodName);

				if ((sourceMethodName.startsWith(PREFIX) || sourceMethodName.startsWith(IS))
						&& !(GETCLASS.equalsIgnoreCase(sourceMethodName))) {
					Class<?> returnType = method.getReturnType();
					getValue = invokeGetMethodOnSource(method, sourceObj);
					populateObject(getValue, destObj, returnType, sourceMethodName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return destObj;
	}

	private Object invokeGetMethodOnSource(Method sourceMethodName, Object sourceObj) {
		Object objReturn = null;
		try {
			objReturn = sourceMethodName.invoke(sourceObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objReturn;
	}

	// private Object invokeGetMethod(Method sourceMethodName, Object sourceObj)
	// {
	// }

	private void populateObject(Object getValue, Object destObj, Class<?> returnType, String sourceMethodName) throws Exception {
		if (null == getValue) {
			destObj = null;
		} else {
			Class<?>[] objParamTypes = new Class[1];
			Object[] objParams = new Object[1];
			objParamTypes[0] = getValue.getClass();
			objParams[0] = getValue;
			String calledMethod = getSetterMethodName(sourceMethodName);
			invokeSetMethod(destObj, calledMethod, objParamTypes, objParams);
		}
	}

	private Object invokeSetMethod(Object object, String strMethodName, Class<?>[] objParamTypes, Object[] objParams) throws Exception {
		Object objReturn = null;
		try {
			Method method = null;
			Class<?> class1 = object.getClass();
			method = class1.getMethod(strMethodName, objParamTypes);
			objReturn = method.invoke(object, objParams);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return objReturn;
	}

	private String getSetterMethodName(String methodName) {
		StringBuffer sb = new StringBuffer("s");
		sb.append(methodName.substring(1));
		return sb.toString();

	}

}
