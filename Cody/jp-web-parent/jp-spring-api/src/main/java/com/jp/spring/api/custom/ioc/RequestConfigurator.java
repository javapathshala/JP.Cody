/**
 * Copywrite @ Dimit Chadha
 */
package com.jp.spring.api.custom.ioc;



/**
 * @author Dimit Chadha
 * 
 */
public class RequestConfigurator {

	public void setupContainer(ContainerDI container) {
		container.add(InsertDI.class);
		container.add(InsertDI2.class);
		// container.add(TestDI.class).toUse(new TestDI());

	}

}
