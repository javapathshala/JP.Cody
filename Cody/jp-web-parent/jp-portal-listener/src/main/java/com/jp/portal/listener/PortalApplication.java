/**
 * File: PortalApplication.java Date: Apr 13, 2015 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn &amp; gain ideas from it. Its unauthorised
 * use is explicitly prohibited &amp; any addition &amp; removal of material. If
 * want to suggest any changes, you are welcome to provide your comments on
 * GitHub Social Code Area. Its unauthorised use gives Java Pathshala the right
 * to obtain retention orders and to prosecute the authors of any infraction.
 * Visit us at www.javapathshala.com
 **/
package com.jp.portal.listener;

/**
 * An object model for defining the format of a portal web application.
 * 
 * @author Dimit Chadha
 */
public class PortalApplication {

	/**
	 * The context path of the portal application relative to the server root.
	 */
	private String contextPath;
	/**
	 * /** A textual description of the portal application.
	 */
	private String description;
	/**
	 * The friendly name of the portal application.
	 */
	private String displayName;
	/**
	 * A flag indicating whether or not the portal application is a report.
	 */
	private boolean report;
	/**
	 * A comma-separated list of security roles that permit access to the portal
	 * application.
	 */
	private String securityRoles;
	/**
	 * A flag indicating whether or not the portal application is a service.
	 */
	private boolean service;

	/**
	 * @return the contextPath
	 */
	public String getContextPath() {
		return contextPath;
	}

	/**
	 * @param contextPath
	 *            the contextPath to set
	 */
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName
	 *            the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the report
	 */
	public boolean isReport() {
		return report;
	}

	/**
	 * @param report
	 *            the report to set
	 */
	public void setReport(boolean report) {
		this.report = report;
	}

	/**
	 * @return the securityRoles
	 */
	public String getSecurityRoles() {
		return securityRoles;
	}

	/**
	 * @param securityRoles
	 *            the securityRoles to set
	 */
	public void setSecurityRoles(String securityRoles) {
		this.securityRoles = securityRoles;
	}

	/**
	 * @return the service
	 */
	public boolean isService() {
		return service;
	}

	/**
	 * @param service
	 *            the service to set
	 */
	public void setService(boolean service) {
		this.service = service;
	}

	public void setReport(String value) {
		setReport(Boolean.parseBoolean(value));
	}

}
