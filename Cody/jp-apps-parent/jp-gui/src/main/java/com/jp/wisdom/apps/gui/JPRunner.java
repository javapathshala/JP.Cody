/*
 * File: JPRunner.java Date: 11-May-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.wisdom.apps.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Dimit Chadha
 */
public class JPRunner extends JFrame {

	private static final long serialVersionUID = -1013288470344076262L;

	private JLabel label = new JLabel("Hello from Java Pathshala!");

	public JPRunner() {
		super("Jave JNLP Example");
		this.setSize(350, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
	}

	public void addButtons() {
		label.setSize(200, 10);
		label.setLocation(20, 50);
		Container content = this.getContentPane();
		content.add(label, BorderLayout.CENTER);

		// button
		JButton button = new JButton("Visit Us");
		button.setSize(150, 30);
		button.setLocation(80, 80);
		content.add(button, BorderLayout.SOUTH);

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					URI url = new URI("http://www.javapathshala.com");
					System.out.println("ok");
					if (Desktop.isDesktopSupported()) {
						Desktop.getDesktop().browse(url);
					}
				} catch (URISyntaxException | IOException e) {
					e.printStackTrace();
				}
			}
		};

		button.addActionListener(listener);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JPRunner jpRunner = new JPRunner();
		jpRunner.addButtons();
		jpRunner.setVisible(true);
	}

}
