/*
 * File: Channel.java Date: 21-Aug-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.patterns.behaviour.iterator;

/**
 * @author dchadha
 */
public class Channel {
	private double frequency;
	private ChannelTypeEnum TYPE;

	public Channel(double freq, ChannelTypeEnum type) {
		this.frequency = freq;
		this.TYPE = type;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	public ChannelTypeEnum getTYPE() {
		return TYPE;
	}

	public void setTYPE(ChannelTypeEnum tYPE) {
		TYPE = tYPE;
	}

	@Override
	public String toString() {
		return "Channel [frequency=" + this.frequency + ", TYPE=" + this.TYPE + "]";
	}

}
