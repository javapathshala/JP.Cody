package com.jp.patterns.behaviour.command;

public class Remote {
	
	Command command;

	public Remote(Command comm) {
		this.command = comm;
		System.out.println("Initializing Remote...");
	}
	public void buttonPushed() {
		command.execute();
	}
}
