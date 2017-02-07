package com.jp.patterns.behaviour.command;

public class RunCommandPattern {

	public static void main(String[] args) {
		System.out.println("Initializing client....");
		Remote rmt = new Remote(new LightOnCommand());
		rmt.buttonPushed();
	}
}
