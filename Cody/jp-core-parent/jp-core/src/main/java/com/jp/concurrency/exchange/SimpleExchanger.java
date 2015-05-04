/*
 * File: SimpleExchanger.java Date: 04-May-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.concurrency.exchange;

import java.util.concurrent.Exchanger;

/**
 * @author Dimit Chadha
 */
public class SimpleExchanger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// create Residence address
		Address residence = new Address();
		residence.setCity("New Delhi");
		residence.setCountry("India");

		// create Office address
		Address office = new Address();
		office.setCity("Poole");
		office.setCountry("United Kingdom");

		System.out.println("#### Address Before Exchange ##### ");
		System.out.println("Residence Address  : " + residence);
		System.out.println("Office  Address : " + office);

		Exchanger<Address> exchanger = new Exchanger<Address>();

		ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(exchanger, residence);
		Thread residenceThread = new Thread(exchangerRunnable1);
		residenceThread.setName("#Residence#");
		residenceThread.start();

		ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(exchanger, office);
		Thread officeThread = new Thread(exchangerRunnable2);
		officeThread.setName("#Office#");
		officeThread.start();
	}

}
