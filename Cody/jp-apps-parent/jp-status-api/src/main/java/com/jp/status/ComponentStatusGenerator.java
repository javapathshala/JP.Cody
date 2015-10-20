/*
 * File: ComponentStatusGenerator.java Date: 23-Apr-2015 This source code is
 * part of Java Pathshala-Wisdom Being Shared. This program is protected by
 * copyright law but you are authorise to learn & gain ideas from it. Its
 * unauthorised use is explicitly prohibited & any addition & removal of
 * material. If want to suggest any changes, you are welcome to provide your
 * comments on GitHub Social Code Area. Its unauthorised use gives Java
 * Pathshala the right to obtain retention orders and to prosecute the authors
 * of any infraction. Visit us at www.javapathshala.com
 */
package com.jp.status;

import com.jp.status.response.Component;

/**
 * A {@link ComponentStatusGenerator} is responsible for generating a
 * {@link Component} summary for a single component of the running application.
 * <p/>
 * The JP Status module takes a generic "hierarchical" approach to reporting
 * the status of an application or module. In summary this Hierarchy is:
 * Application --> Component(s) --> Aspects(s).
 * <p/>
 * At the top level is the "Application" status. The basic assumption is that
 * all applications are made up of "Components" in some fashion or another, and
 * from a status reporting perspective, a "application" may only choose to have
 * one "component", but can also alternatively have as many as is necessary. The
 * overall Application status is derived by the health state reported from each
 * "Component" that makes up the application. (Note that a component needs to
 * "register" itself to be included as a status generator - see further below).
 * <p/>
 * Each "Component" is represented by an instance of a class that implements
 * this {@link ComponentStatusGenerator} interface. Much like the Application
 * Status, an individual Component's status is derived by the reported status of
 * each of that Components "Aspects". Again - a Component can choose to only
 * have one reported "aspect" or it can have as many as is necessary. The
 * derived component status is the summary of the statuses for all that
 * component's Aspects.
 * <p/>
 * At the bottom of the hierarchy is a state "aspect", and is meant to mean some
 * sort of aspect of the application that is appropriate to report status on. An
 * "aspect" might represent the health of a specific database connection, or the
 * health of a TCP connection to a PBX, or it might represent something more
 * generic like "the number of currently active users", or "the number of
 * successful transactions made by a specific piece of the application" or "the
 * number of exceptions that have occurred" (either for a specific application
 * piece or a roll-up).
 * <p/>
 * An application or component can approach the reporting of its "health state"
 * in one of 3 ways:
 * <ol>
 * <li>(The quickest) Identify the key classes (often the singletons) that have
 * the best overall view of your application or component state, and have those
 * classes <code>extend</code> the AbstractComponentStatusGenerator class. Then
 * override the AbstractComponentStatusGenerator.getComponentName() to best
 * describe the component in english, and then add calls to the
 * <code>mark()</code> method of the
 * AbstractComponentStatusGenerator.successTracker() object at various points in
 * your code where "successful transactions or operations or requests" have
 * completed, and calls to the <code>mark()</code> method of the
 * AbstractComponentStatusGenerator.failureTracker() object whenever either an
 * exception or a "failed" operation occurs.</li>
 * <li>If the key classes identified above already extend other classes, then
 * extending is not an option, so just instead <code>implement</code> this
 * <code>ComponentStatusGenerator</code> interface directly. In this case, use
 * the source code of the AbstractComponentStatusGenerator class as the basis to
 * copy/paste from and work from there.</li>
 * <li>(The most work) Create a new class (or classes) in your application that
 * implement this <code>ComponentStatusGenerator</code> interface, and have the
 * {@link #getStatus} method implementation interrogate other things in your
 * application to report status on them.</li>
 * </ol>
 * In either case above, the general concept is the implementation of the
 * {@link #getStatus} method instantiates instances of the
 * {@link com.vertexgroup.telephony.status.response.Aspect} object that have
 * their Aspect status (
 * {@link com.vertexgroup.telephony.status.response.Aspect#setStatus}) set to
 * "OK", "WARN", or "ERROR". Then in addition, a single
 * {@link com.vertexgroup.telephony.status.response.Component} object is
 * instantiated, and all the Aspect instances are attached to that Component,
 * with the Component object being returned from the overall
 * <code>getStatus{}</code> method.
 * <p/>
 * In option 1 above, the <code>AbstractComponentStatusGenerator</code>
 * effectively has 2 default "aspects" pre-created - the "successTracker" and
 * the "failureTracker", but that doesn't mean that these are one only Aspects
 * than can be reported. You can simply override the
 * AbstractComponentStatusGenerator.getStatus() method implementation (be sure
 * to call <code>this.super()</code> within to preserve the reporting of the
 * pre-canned successTracker and failureTracker aspects), and then add
 * additional "Aspect" instances to the <code>Component</code> object to report
 * other aspects of your component as necessary before the
 * <code>Component</code> object is returned.
 * <p/>
 * The final part is to "register" your various
 * <code>ComponentStatusGenerator</code> "aware" classes with the Status
 * Servlet, so that it can find them at runtime. This should be done by a call
 * to the Singleton {@link ComponentStatusGeneratorRegistry#register}
 * class/method during initialization.
 */
public interface ComponentStatusGenerator {

	/**
	 * Adds this {@link ComponentStatusGenerator} to the
	 * {@link ComponentStatusGeneratorRegistry}.
	 */
	void register();

	/**
	 * Gets a {@link Component} instance representing the status of a portion of
	 * the running application.
	 *
	 * @return a {@link Component} instance representing the status of a portion
	 *         of the running application.
	 */
	Component getStatus();

}
