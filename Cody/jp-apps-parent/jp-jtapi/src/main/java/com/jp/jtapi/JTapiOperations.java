/*
 * File: JTapiOperations.java Date: 30-Apr-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.jtapi;

import javax.telephony.Address;
import javax.telephony.Call;
import javax.telephony.Connection;
import javax.telephony.InvalidArgumentException;
import javax.telephony.InvalidPartyException;
import javax.telephony.InvalidStateException;
import javax.telephony.MethodNotSupportedException;
import javax.telephony.PrivilegeViolationException;
import javax.telephony.Provider;
import javax.telephony.ResourceUnavailableException;
import javax.telephony.Terminal;

import com.jp.jtapi.provider.ConnectionProvider;

/**
 * @author Dimit Chadha
 */
public class JTapiOperations {

	private static Provider provider;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JTapiOperations operations = new JTapiOperations();

		// Establish Connection
		provider = ConnectionProvider.getConnection();

		// Print all the available addresses of the system.
		operations.addreess();

		// Dial
		Call call = operations.dial("4805594", "1053021");

		// Call Details
		operations.getCallDetails(call);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Disconnect Call now ...");
		// Disconnect Call
		operations.disconnectCall(call);

		System.out.println("...............");
		System.out.println("Call details after disconnect");
		operations.getCallDetails(call);

		// Print all the available terminals of the system.
		// operations.terminals();

		// Shutdown connection
		operations.cleanUp();

	}

	/**
	 * 
	 */
	private void addreess() {
		try {
			Address[] addresses = provider.getAddresses();
			System.out.println("Avaialable Addresses : ");
			for (int i = 0; i < addresses.length; i++) {
				System.out.println("Address : " + addresses[i].getName());
			}
			System.out.println("About to listen to Address");
			Address address = provider.getAddress("1053006");
			System.out.println("Address Listened: " + address.getName());

		} catch (ResourceUnavailableException e) {
			e.printStackTrace();
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param string
	 * @param string2
	 */
	private Call dial(String source, String destination) {
		Call call = null;
		try {
			Address address = provider.getAddress(source);
			Terminal myTerm = address.getTerminals()[0];

			System.out.println("Calling from: " + source + " to: " + destination);

			call = provider.createCall();
			call.connect(myTerm, address, destination);

		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		} catch (ResourceUnavailableException e) {
			e.printStackTrace();
		} catch (InvalidStateException e) {
			e.printStackTrace();
		} catch (PrivilegeViolationException e) {
			e.printStackTrace();
		} catch (MethodNotSupportedException e) {
			e.printStackTrace();
		} catch (InvalidPartyException e) {
			e.printStackTrace();
		}
		return call;
	}

	/**
	 * @param call
	 */
	private void getCallDetails(Call call) {
		Connection[] connections = call.getConnections();
		if (call.getConnections() != null) {
			System.out.println("Call Connections: " + call.getConnections().length);
			for (int i = 0; i < connections.length; i++) {
				System.out.println("***************");
				System.out.println("Connection[" + i + "] details: ");
				System.out.println("Connection State: " + connStateToString(connections[i].getState()));
				System.out.println("Address: " + connections[i].getAddress().getName());
				System.out.println("Call State: " + callStateToString(connections[i].getCall().getState()));
			}
		} else {
			System.out.println("Null Connections");
		}
	}

	private static String connStateToString(int code) {

		String result = null;

		switch (code) {
			case Connection.ALERTING:
				result = "ALERTING";
				break;
			case Connection.CONNECTED:
				result = "CONNECTED";
				break;
			case Connection.DISCONNECTED:
				result = "DISCONNECTED";
				break;
			case Connection.FAILED:
				result = "FAILED";
				break;
			case Connection.IDLE:
				result = "IDLE";
				break;
			case Connection.INPROGRESS:
				result = "INPROGRESS";
				break;
			case Connection.UNKNOWN:
				result = "UNKNOWN";
				break;
			default:
				result = "UNKOWN";
				break;
		}
		return result;
	}

	private static String callStateToString(int state) {

		String result = null;

		switch (state) {
			case Call.ACTIVE:
				result = "ACTIVE";
				break;
			case Call.IDLE:
				result = "IDLE";
				break;
			case Call.INVALID:
				result = "INVALID";
				break;

			default:
				result = "UNKNOWN";
				break;
		}

		return result;
	}

	/**
	 * @param call
	 */
	private void disconnectCall(Call call) {
		Connection[] connections = call.getConnections();
		for (int i = 0; i < connections.length; i++) {
			try {
				if (checkConnectionEligibleForDisconnect(connections[i])) {
					connections[i].disconnect();
					System.out.println("Call Disconnected");
				}
			} catch (PrivilegeViolationException e) {
				e.printStackTrace();
			} catch (ResourceUnavailableException e) {
				e.printStackTrace();
			} catch (MethodNotSupportedException e) {
				e.printStackTrace();
			} catch (InvalidStateException e) {
				e.printStackTrace();
			}
		}
	}

	private static boolean checkConnectionEligibleForDisconnect(Connection connection) {
		boolean result;
		switch (connection.getState()) {
			case Connection.CONNECTED:
				result = true;
				break;
			case Connection.ALERTING:
				result = true;
				break;
			case Connection.INPROGRESS:
				result = true;
				break;
			case Connection.FAILED:
				result = true;
				break;

			default:
				result = false;
				break;
		}
		return result;
	}

	/**
	 * 
	 */
	private void terminals() {
		try {
			Terminal[] terminals = provider.getTerminals();
			System.out.println("Avaialable Terminals : ");
			for (int i = 0; i < terminals.length; i++) {
				System.out.println("Terminal : " + terminals[i].getName());
			}
		} catch (ResourceUnavailableException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */
	private void cleanUp() {
		provider.shutdown();
		System.out.println("Link Shutdown now...");

	}
}
