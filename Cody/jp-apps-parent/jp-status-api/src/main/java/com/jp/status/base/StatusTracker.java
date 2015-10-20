/*
 * File: StatusTracker.java Date: 24-Apr-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.status.base;

import java.util.Date;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @author Dimit Chadha
 */
public class StatusTracker {

	/**
	 * The default amount of time, in milliseconds, that marks are retained on a
	 * {@link StatusTracker} instance.
	 */
	public static final long DEFAULT_RETENTION = TimeUnit.MINUTES.toMillis(15L);

	/**
	 * A {@link Deque} of {@link Date}s representing all the timestamps that
	 * this {@link StatusTracker} was marked.
	 */
	private final Deque<Date> buffer = new LinkedList<>();
	/**
	 * The amount of time, in milliseconds, that marks are retained on this
	 * {@link StatusTracker}.
	 */
	private final long retentionPeriod;
	/**
	 * The number of marks to retain, regardless of the {@link #retentionPeriod}
	 * .
	 */
	private final int minimumSize;

	/**
	 * Creates a {@link StatusTracker} with a default retention period.
	 *
	 * @see #StatusTracker(long)
	 */
	public StatusTracker() {
		this(DEFAULT_RETENTION);
	}

	/**
	 * Creates a {@link StatusTracker} with the specified retention period and
	 * the specified minimum size.
	 *
	 * @param size
	 *            number of marks to retain, regardless of the
	 *            {@link #retentionPeriod}.
	 */
	public StatusTracker(int size) {
		this(DEFAULT_RETENTION, size);
	}

	/**
	 * Creates a {@link StatusTracker} with the specified retention period with
	 * no minimum size.
	 *
	 * @param period
	 *            the amount of time, in milliseconds, that marks are retained
	 *            on this {@link StatusTracker}.
	 */
	public StatusTracker(long period) {
		this(period, 0);
	}

	/**
	 * Creates a {@link StatusTracker} with the specified retention period and
	 * the specified minimum size.
	 *
	 * @param period
	 *            the amount of time, in milliseconds, that marks are retained
	 *            on this {@link StatusTracker}.
	 * @param size
	 *            number of marks to retain, regardless of the
	 *            {@link #retentionPeriod}.
	 */
	public StatusTracker(long period, int size) {
		this.retentionPeriod = period;
		if (size < 0) {
			throw new IllegalArgumentException("Illegal Capacity: " + size);
		}
		this.minimumSize = size;
	}

	/**
	 * Gets the most recent {@link Date} this {@link StatusTracker} was marked.
	 *
	 * @see #trim()
	 * @see Deque#peekLast()
	 * @since 1.0
	 * @return the most recent {@link Date} this {@link StatusTracker} was
	 *         marked.
	 */
	public Date getLastMark() {
		synchronized (buffer) {
			trim();
			return buffer.peekLast();
		}
	}

	/**
	 * Gets the number of times this {@link StatusTracker} has been marked
	 * within the specified {@link #retentionPeriod}.
	 *
	 * @see #trim()
	 * @see Deque#size()
	 * @return the number of times this {@link StatusTracker} has been marked
	 *         within the specified {@link #retentionPeriod}.
	 */
	public int getMarkCount() {
		synchronized (buffer) {
			trim();
			return buffer.size();
		}
	}

	/**
	 * Indicates if this this {@link StatusTracker} has been marked within the
	 * specified {@link #retentionPeriod}.
	 *
	 * @since 1.0
	 * @return {@code true} if this this {@link StatusTracker} has been marked
	 *         within the specified {@link #retentionPeriod}, {@code false}
	 *         otherwise.
	 */
	public boolean isDirty() {
		return getLastMark() != null;
	}

	/**
	 * Marks this {@link StatusTracker} as dirty.
	 *
	 * @since 1.0
	 */
	public void mark() {
		synchronized (buffer) {
			buffer.offer(new Date());
			trim();
		}
	}

	/**
	 * Removes all marks that are older than the specified
	 * {@link #retentionPeriod}.
	 *
	 * @see Date#getTime()
	 * @see Deque#iterator()
	 * @see Iterator#hasNext()
	 * @see Iterator#next()
	 * @see Iterator#remove()
	 * @since 1.0
	 */
	private void trim() {
		long rolloffTime = System.currentTimeMillis() - retentionPeriod;
		synchronized (buffer) {
			Iterator<Date> itr = buffer.iterator();
			while (buffer.size() > minimumSize && itr.next().getTime() < rolloffTime) {
				itr.remove();
			}
		}
	}
}
