/*
 * File: ChannelCollectionImpl.java Date: 21-Aug-2013 This source code is part
 * of Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.patterns.behaviour.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dchadha
 */
public class ChannelCollectionImpl implements ChannelCollection {

	private List<Channel> channelsList;

	public ChannelCollectionImpl() {
	        channelsList = new ArrayList<Channel>();
	    }

	
	public void addChannel(Channel c) {
		this.channelsList.add(c);

	}

	
	public void removeChannel(Channel c) {
		  this.channelsList.remove(c);

	}

	
	public ChannelIterator iterator(ChannelTypeEnum type) {
		 return new ChannelIteratorImpl(type, this.channelsList);
	}

}
