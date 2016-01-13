/*
 * File: AbstractComponentStatusGenerator.java Date: 24-Apr-2015 This source
 * code is part of Java Pathshala-Wisdom Being Shared. This program is protected
 * by copyright law but you are authorise to learn & gain ideas from it. Its
 * unauthorised use is explicitly prohibited & any addition & removal of
 * material. If want to suggest any changes, you are welcome to provide your
 * comments on GitHub Social Code Area. Its unauthorised use gives Java
 * Pathshala the right to obtain retention orders and to prosecute the authors
 * of any infraction. Visit us at www.javapathshala.com
 */
package com.jp.status.base;

import java.util.Date;
import java.util.List;

import com.jp.status.ComponentStatusGenerator;
import com.jp.status.ComponentStatusGeneratorRegistry;
import com.jp.status.response.Aspect;
import com.jp.status.response.Component;
import com.jp.status.response.StatusCode;

/**
 * An abstract extension of the {@link ComponentStatusGenerator} interface that
 * encapsulates registration with the {@link ComponentStatusGeneratorRegistry}
 * for this application.
 * 
 * @author Dimit Chadha
 */
public abstract class AbstractComponentStatusGenerator implements ComponentStatusGenerator {

	/**
	 * The name of the {@link Component} generated by this
	 * {@link ComponentStatusGenerator}. This value defaults to a pretty-print
	 * version of this object's {@link Class#getSimpleName() simple class name}.
	 *
	 * @since 2.4
	 */
	private final String componentName = getClass().getSimpleName().replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2");
	/**
	 * The {@link StatusTracker} instance used to track the occurrence of
	 * exceptional conditions.
	 *
	 * @since 1.0
	 */
	private final StatusTracker failureTracker = new StatusTracker();
	/**
	 * The {@link StatusTracker} instance used to track the occurrence of
	 * successfully completed transactions.
	 *
	 * @since 1.0
	 */
	private final StatusTracker successTracker = new StatusTracker(1);

	/**
	 * Gets the name of the {@link Component} generated by this
	 * {@link ComponentStatusGenerator}.
	 *
	 * @since 1.0.4
	 * @return the name of the {@link Component} generated by this
	 *         {@link ComponentStatusGenerator}.
	 */
	public String getComponentName() {
		return componentName;
	}

	/**
	 * Gets the {@link StatusTracker} instance used to track the occurrence of
	 * exceptional conditions.
	 *
	 * @since 1.0
	 * @return the {@link StatusTracker} instance used to track the occurrence
	 *         of exceptional conditions.
	 */
	public StatusTracker getFailureTracker() {
		return failureTracker;
	}

	/**
	 * Gets the {@link StatusTracker} instance used to track the occurrence of
	 * successfully completed transaction.
	 *
	 * @since 1.0
	 * @return the {@link StatusTracker} instance used to track the occurrence
	 *         of successfully completed transaction.
	 */
	public StatusTracker getSuccessTracker() {
		return successTracker;
	}

	/**
	 * {@inheritDoc} This implementation creates a {@link Component} containing
	 * {@link Aspect} representations of the {@link #failureTracker} and
	 * {@link #successTracker}.
	 *
	 * @since 1.0
	 */
	@Override
	public Component getStatus() {
		// Create a new status
		Component status = new Component();
		// Set the name of the component
		status.setName(getComponentName());
		// Get the aspect list
		List<Aspect> aspects = status.getElements();
		// Add the failure status to the list
		Aspect failureAspect = formatFailureAspect();
		aspects.add(failureAspect);
		// Add the success status to the list
		Aspect successAspect = formatSuccessAspect();
		aspects.add(successAspect);
		// Return the single status list
		return status;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see ComponentStatusGeneratorRegistry#register(com.vertexgroup.telephony.status.ComponentStatusGenerator)
	 * @since 1.0
	 */
	@Override
	public void register() {
		// Add this component to the list of generators
		ComponentStatusGeneratorRegistry.register(this);
	}

	/**
	 * Formats the {@link #failureTracker} in to a {@link Component}.
	 *
	 * @see StatusTracker#getLastMark()
	 * @since 1.0
	 * @return a {@link Component} representation of the {@link #failureTracker}
	 *         .
	 */
	private Aspect formatFailureAspect() {
		// Instantiate a new status
		Aspect aspect = new Aspect();
		// Set the name of the component
		aspect.setName("Exceptional Conditions");
		// Lock the tracker for multiple synchronized operations
		synchronized (failureTracker) {
			// Get the mark count
			int markCount = failureTracker.getMarkCount();
			// If the tracker is not marked
			if (markCount == 0) {
				// Set the unmarked status code
				aspect.setStatus(StatusCode.OK);
			} else {
				// Get the last mark
				Date lastMark = failureTracker.getLastMark();
				// Set the marked status code
				aspect.setStatus(StatusCode.ERROR);
				// Set the marked message
				aspect.setMessage(String.format("%d exceptions. Last exception occurred at %s", markCount, lastMark));
			}
		}
		// Return the aspect
		return aspect;
	}

	/**
	 * Formats the {@link #successTracker} in to a {@link Component}.
	 *
	 * @see StatusTracker#getLastMark()
	 * @since 1.0
	 * @return a {@link Component} representation of the {@link #successTracker}
	 *         .
	 */
	private Aspect formatSuccessAspect() {
		// Instantiate a new status
		Aspect aspect = new Aspect();
		// Set the name of the component
		aspect.setName("Successful Transactions");
		// Lock the tracker for multiple synchronized operations
		synchronized (successTracker) {
			// Get the mark count
			int markCount = successTracker.getMarkCount();
			// Set the unmarked status code
			aspect.setStatus(StatusCode.OK);
			// If the tracker is marked
			if (markCount != 0) {
				// Get the last mark
				Date lastMark = successTracker.getLastMark();
				// Set the marked message
				aspect.setMessage(String.format("%d transactions completed. Last transaction completed at %s", markCount, lastMark));
			}
		}
		// Return the aspect
		return aspect;
	}
}