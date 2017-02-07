/*
 * File: ChannelIteratorImpl.java Date: 21-Aug-2013 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.patterns.behaviour.iterator;

import java.util.List;

/**
 * @author dchadha
 */
public class ChannelIteratorImpl implements ChannelIterator {

	private ChannelTypeEnum type;
	private List<Channel> channels;
	private int position;

	public ChannelIteratorImpl(ChannelTypeEnum ty, List<Channel> channelsList) {
		this.type = ty;
		this.channels = channelsList;
	}

	
	public boolean hasNext() {
		while (position < channels.size()) {
			Channel c = channels.get(position);
			if (c.getTYPE().equals(type) || type.equals(ChannelTypeEnum.ALL)) {
				return true;
			} else
				position++;
		}
		return false;
	}

	
	public Channel next() {
		Channel c = channels.get(position);
		position++;
		return c;
	}
}
