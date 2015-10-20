/*
 * File: ConnectionProvider.java Date: 30-Apr-2015 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.jtapi.provider;

import java.io.IOException;
import java.util.Properties;

import javax.telephony.JtapiPeer;
import javax.telephony.JtapiPeerFactory;
import javax.telephony.JtapiPeerUnavailableException;
import javax.telephony.Provider;

/**
 * @author Dimit Chadha
 */
public final class ConnectionProvider {

	private static ConnectionProvider instance;
	private static Provider provider;
	private static Properties config;

	private ConnectionProvider() {
		loadConfig();
		bootStrap();
	}

	/**
	 * 
	 */
	private void loadConfig() {
		config = new Properties();
		try {
			config.load(this.getClass().getClassLoader().getResourceAsStream("jp-avaya.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public final static synchronized Provider getConnection() {
		if (instance == null) {
			instance = new ConnectionProvider();
		}
		return provider;
	}

	/**
	 * 
	 */
	private void bootStrap() {
		try {
			JtapiPeer peer = JtapiPeerFactory.getJtapiPeer(null);
			String[] myServices = peer.getServices();

			// now instantiate our main JTAPI provider object...
			String providerString = myServices[0] + ";login=" + config.getProperty("avaya.aes.username") + ";passwd="
					+ config.getProperty("avaya.aes.pw");

			provider = peer.getProvider(providerString);
			System.out.println("Connection establish successfully...");

		} catch (JtapiPeerUnavailableException e) {
			System.out.println("Avaya AES Link initialization failed - JtapiPeer creation failed "
					+ "Resp[onseReason.INVALID_SYSTEM_CONFIGURATION" + e);
		} catch (Exception e) {
			System.out.println("Failed to instantiate JTAPI provider for " + "avaya" + "ResponseReason.COMMUNICATION_ERROR " + e);
		}
	}

}
