/*
 * File: ComponentStatusGeneratorRegistry.java Date: 23-Apr-2015 This source
 * code is part of Java Pathshala-Wisdom Being Shared. This program is protected
 * by copyright law but you are authorise to learn & gain ideas from it. Its
 * unauthorised use is explicitly prohibited & any addition & removal of
 * material. If want to suggest any changes, you are welcome to provide your
 * comments on GitHub Social Code Area. Its unauthorised use gives Java
 * Pathshala the right to obtain retention orders and to prosecute the authors
 * of any infraction. Visit us at www.javapathshala.com
 */
package com.jp.status;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.jp.status.response.Application;
import com.jp.status.response.Component;

/**
 * A utility class used to to manage registration of
 * {@link ComponentStatusGenerator} instances and report on their current
 * status.
 * 
 * @author Dimit Chadha
 */
public final class ComponentStatusGeneratorRegistry {

	/**
	 * The {@link List} of {@link ComponentStatusGenerator} instances used to
	 * generate a comprehensive status representation of the running
	 * application.
	 */
	private static final Set<ComponentStatusGenerator> GENERATORS = new LinkedHashSet<ComponentStatusGenerator>();

	/**
	 * The name of the running application.
	 */
	private static String applicationName;

	/**
	 * A default constructor to prevent external instantiation.
	 */
	private ComponentStatusGeneratorRegistry() {
	}

	/**
	 * Gets the name of the running application.
	 *
	 * @return the name of the running application.
	 */
	public static String getApplicationName() {
		return applicationName;
	}

	/**
	 * Sets the name of the running application.
	 *
	 * @since 1.0
	 * @param name
	 *            the name of the running application.
	 */
	public static void setApplicationName(String name) {
		synchronized (ComponentStatusGeneratorRegistry.class) {
			if (applicationName == null && name != null) {
				applicationName = name;
			}
		}
	}

	/**
	 * Adds the specified {@link ComponentStatusGenerator} to the
	 * {@link #GENERATORS list of generators}.
	 *
	 * @since 1.0
	 * @param generator
	 *            a {@link ComponentStatusGenerator} to add to the
	 *            {@link #GENERATORS list of generators}.
	 */
	public static void register(ComponentStatusGenerator generator) {
		synchronized (GENERATORS) {
			GENERATORS.add(generator);
		}
	}

	/**
	 * Aggregates the {@link Component} statuses of all registered
	 * {@link ComponentStatusGenerator} instances in to a {@link Application}
	 * representation.
	 *
	 * @return a {@link Application} instance representing the overall status of
	 *         the running application.
	 */
	public static Application getStatus() {
		// Get the components status generators for the current application
		Application status = new Application();
		// Set the application name
		status.setName(applicationName);
		synchronized (GENERATORS) {
			// Get the component list
			List<Component> list = status.getElements();
			// For each component in the list
			for (ComponentStatusGenerator generator : GENERATORS) {
				// Get the status for the component
				Component componentStatus = generator.getStatus();
				// Add the component status to the application status
				list.add(componentStatus);
			}
		}
		// Return the status information
		return status;
	}
}
