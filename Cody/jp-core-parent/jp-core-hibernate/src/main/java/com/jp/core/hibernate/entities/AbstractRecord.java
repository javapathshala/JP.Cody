/*
 * File: AbstractRecord.java
 * Date: 01-Sep-2015
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention
 * orders
 * and to prosecute the authors of any infraction.
 * Visit us at www.javapathshala.com
 */
package com.jp.core.hibernate.entities;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public abstract class AbstractRecord {

	/**
	 * The universally unique identifier of this record.
	 *
	 * @since 1.0
	 */
	private int recordId;

	/**
	 * Generic identifier hold record modification date & time
	 *
	 * @since 1.0
	 */
	private Timestamp lastModified;

	/**
	 * Gets the universally unique identifier of this record.
	 *
	 * @since 1.0
	 * @return the universally unique identifier of this record.
	 */
	public int getRecordId() {
		return recordId;
	}

	/**
	 * Sets the universally unique identifier of this record.
	 *
	 * @since 1.0
	 * @param value the universally unique identifier of this record.
	 */
	public void setRecordId(int value) {
		this.recordId = value;
	}

	/**
	 * Gets Generic identifier hold record modification date & time
	 *
	 * @return last modified date for the record
	 * @since 1.0
	 */
	public Date getLastModified() {
		return lastModified;
	}

	/**
	 * Sets the universally unique identifier of this record.
	 *
	 * @param lastModified the universally identifier of this record.
	 *
	 * @since 1.0
	 */
	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * This implementation tests the class and {@link #recordId} of the
	 * specified instance for equality.
	 * </p>
	 *
	 * @return
	 * @since 1.0
	 */
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		// If the specified object of the same class
		if (getClass().isInstance(obj)) {
			// Cast the specified object
			AbstractRecord other = getClass().cast(obj);
			// Compare the record ID of this record with that of the specified
			// record
			result = Objects.equals(this.recordId, other.recordId);
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * This implementation returns the hash code of the {@link #recordId}.
	 * </p>
	 *
	 * @return
	 * @since 1.0
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(this.recordId);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AbstractRecord [recordId=" + recordId + ", lastModified=" + lastModified + "]";
	}
	
	

}
