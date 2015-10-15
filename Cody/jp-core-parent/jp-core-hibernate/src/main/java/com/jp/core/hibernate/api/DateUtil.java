/*
 * File: DateUtil.java
 * Date: 03-Sep-2015
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
package com.jp.core.hibernate.api;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dimit Chadha
 */
/**
*
* Utility methods for Date
*/
public final class DateUtil {

	private final static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	private static final String COLON = ":";

	/**
	 * Get the current date
	 *
	 * @return Calendar
	 */
	public static Calendar getCalendarCurrentDate() {
		// initialize the calendar to midnight to prevent
		// the current day from being rejected
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	/**
	 * Function convert date object into calendar object
	 *
	 * @param date
	 * @return cal the object of Calendar class
	 */
	public static Calendar dateToCalendar(Date date) {
		Calendar cal = null;
		if (date != null) {
			cal = Calendar.getInstance();
			cal.setTime(date);
		}
		return cal;
	}

	/**
	 * Get the Date for string date value
	 *
	 * @param dateStr
	 * @return Date object
	 */
	public static Date getDate(String dateStr) {
		DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
		Date testDate;
		try {
			testDate = df.parse(dateStr);
		} catch (ParseException e) {
			logger.error("Error:" + e.getMessage());
			testDate = new Date();
		}
		return testDate;
	}

	/**
	 * Get the Date for string date value
	 *
	 * @param dateStr
	 * @param format
	 * @return Date object
	 */
	public static Date getDate(String dateStr, String format) {
		DateFormat df = new SimpleDateFormat(format);
		Date testDate;
		try {
			testDate = df.parse(dateStr);
		} catch (ParseException e) {
			logger.error("Error:" + e.getMessage());
			testDate = new Date();
		}
		return testDate;
	}

	/**
	 * Convert Date to String, default date is current date and default format
	 * is yyyy/MM/dd
	 *
	 * @param format
	 * @param date
	 * @return String date
	 */
	public static String getDateToString(String format, Date date) {
		if (null == format) {
			format = DATE_TIME_FORMAT;
		}
		if (date == null) {
			date = Calendar.getInstance().getTime();
		}
		DateFormat formatter = new SimpleDateFormat(format);
		String strDate = formatter.format(date);
		return strDate;
	}

	/**
	 * Convert with the given date format
	 *
	 * @param date
	 * @param formatter
	 * @return string
	 */
	public static String formatDate(Date date, String formatter) {
		if (date == null) {
			date = new Date();
		}
		DateFormat dateFormat = null;
		if (formatter == null) {
			dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
		} else {
			dateFormat = new SimpleDateFormat(formatter);
		}
		return dateFormat.format(date);
	}

	/**
	 * Convert with the given date format
	 *
	 * @return string
	 */
	public static String formatCurrentDate() {
		Date date = new Date();
		DateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
		return formatter.format(date);
	}

	/**
	 * Convert with the given date format
	 *
	 * @return string
	 */
	public static Date formatCurrentDateToDate() {
		Date date = new Date();
		DateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
		return getDate(formatter.format(date));
	}

	/**
	 *
	 * @param formatter
	 * @param time
	 * @return
	 */
	private static String translateLongToDate(String formatter, long time) {
		Date date = new Date(time);
		SimpleDateFormat format = new SimpleDateFormat(formatter);
		return format.format(date);
	}

	/**
	 * Convert long time to HHmmss format
	 *
	 * @param timeTaken
	 * @return
	 */
	private static String translateLongToHrsMinSec(long timeTaken) {
		String finalTaken = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(timeTaken),
				TimeUnit.MILLISECONDS.toMinutes(timeTaken) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeTaken)),
				TimeUnit.MILLISECONDS.toSeconds(timeTaken) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeTaken)));
		return finalTaken;
	}

	/**
	 * Calculate Harvester Process Time
	 *
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String calculateProcessTime(long startTime, long endTime) {
		logger.info("Harvester End Time is {}", translateLongToDate(DATE_TIME_FORMAT, endTime));
		long timeTaken = endTime - startTime;
		String finalTaken = translateLongToHrsMinSec(timeTaken);
		logger.info("Harvester Process took {} Hrs", finalTaken);
		return finalTaken;
	}

	/**
	 *
	 * @param seconds
	 * @return
	 */
	public static String translateSecondsToHrMinSec(int seconds) {
		int hr = seconds / 3600;
		int rem = seconds % 3600;
		int mn = rem / 60;
		int sec = rem % 60;
		String hrStr = (hr < 10 ? "0" : "") + hr;
		String mnStr = (mn < 10 ? "0" : "") + mn;
		String secStr = (sec < 10 ? "0" : "") + sec;
		return new StringBuffer(hrStr).append(COLON).append(mnStr).append(COLON).append(secStr).append(" Hrs").toString();
	}

	/**
	 * Format the calendar Object to required format
	 *
	 * @param calendar
	 * @param format
	 * @return
	 */
	public static String formatDate(Calendar calendar, String format) {
		if (format == null) {
			format = DATE_TIME_FORMAT;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(calendar.getTime());
	}

	/**
	 *
	 * @param currentAge
	 * @param oldAge
	 * @return
	 */
	public static int compareDates(Date currentAge, Date oldAge) {
		return (int) ((currentAge.getTime() - oldAge.getTime()) / (1000 * 60 * 60 * 24));
	}
}
