/** File: Connection.java
 *  Date: Apr 21, 2015
 *
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn 
 * &amp; gain ideas from it. Its unauthorised use is explicitly prohibited &amp; any 
 * addition &amp; removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention orders
 * and to prosecute the authors of any infraction.
 * 
 * Visit us at www.javapathshala.com
 **/
package com.jp.jtapi.provider;

import javax.telephony.Address;
import javax.telephony.JtapiPeer;
import javax.telephony.JtapiPeerFactory;
import javax.telephony.Provider;
import javax.telephony.ProviderEvent;
import javax.telephony.ProviderListener;
import javax.telephony.callcenter.ACDAddress;

import com.avaya.jtapi.tsapi.adapters.ACDAddressListenerAdapter;
import com.avaya.jtapi.tsapi.adapters.ProviderListenerAdapter;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public class JTapiConnection {

	private Provider jtapiProvider;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JTapiConnection jTapiConnection = new JTapiConnection();
		jTapiConnection.establishConnection();
	}

	/**
	 * 
	 */
	private void establishConnection() {
		initJtapi();

	}

	/**
	 * 
	 */
	private void initJtapi() {
		try {
			JtapiPeer jtapiPeer = JtapiPeerFactory.getJtapiPeer(null);
			String[] myServices = jtapiPeer.getServices();
			if (myServices!=null){
				System.out.println("fff "+myServices[0]);	
			}else{
				System.out.println("null");
			}
			
			
			// initialize without relying on TSAPI.PRO being present
			String providerString = String.format(
					"%s;login=%s;passwd=%s;servers=%s:%d",
					"AVAYA#VDALVXACMR000#CSTA#VDALVXAAES001", "ctiuser",
					"Ctiuser@1", "10.195.7.68", 450);
			jtapiProvider = jtapiPeer.getProvider(providerString);
			jtapiProvider.addProviderListener(new ProviderListenerAdapter() {
				@Override
				public void providerInService(ProviderEvent event) {
					synchronized (JTapiConnection.this) {
						JTapiConnection.this.notify();
					}
				}
			});
			synchronized (this) {
				// wait until provider ready
				wait();
			}
			System.out.println("JTAPI Provider initialized and UP");

			peformACDAddressListening();
			cleanUp();

		} catch (Exception e) {

		}
	}

	/**
	 * 
	 */
	private void cleanUp() {
		if (jtapiProvider != null) {
			jtapiProvider.shutdown();

			ProviderListener[] listeners = jtapiProvider.getProviderListeners();
			if (listeners != null) {
				for (ProviderListener listener : listeners) {
					jtapiProvider.removeProviderListener(listener);
				}
			}
		}
		System.out.println("JTAPI Provider cleanup complete");

	}

	/**
	 * 
	 */
	private void peformACDAddressListening() throws Exception {
		String acdExtension = "4805594";

		Thread.sleep(2000);
		System.out.println("About to listen to Address");
		Address address = jtapiProvider.getAddress(acdExtension);
		if (address instanceof ACDAddress) {
			((ACDAddress) address)
					.addAddressListener(new TestAddressListener());
			System.out.println("Address Listener established");

			Thread.sleep(2000);
			((ACDAddress) address)
					.removeAddressListener(new TestAddressListener());
		}

		Thread.sleep(2000);

	}

	private class TestAddressListener extends ACDAddressListenerAdapter {

	}
}
