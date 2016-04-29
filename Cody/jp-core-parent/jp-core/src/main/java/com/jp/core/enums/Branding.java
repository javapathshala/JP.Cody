/*
 * File: Branding.java
 * Date: 30-Apr-2016
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
package com.jp.core.enums;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * 
 * 
 * @author Dimit Chadha
 *
 */
public enum Branding {

	KY("US/Eastern", 7, 0, 19, 0,8,0,12,0), 
	MD("US/Eastern", 7, 0, 19, 0,8,0,12,0),
	OH("US/Eastern", 7, 0, 19, 0,8,0,12,0), 
	PA("US/Eastern", 7, 0, 19, 0,8,0,12,0),
	VA("US/Eastern", 7, 0, 19, 0,8,0,12,0);

	private final TimeZone timeZone;
	private final int openHour;
	private final int openMinute;
	private final int closeHour;
	private final int closeMinute;
	
	private final int openHourSat;
	private final int openMinuteSat;
	private final int closeHourSat;
	private final int closeMinuteSat;

	private Branding(String timeZoneID, int openHour, int openMinute, int closeHour, int closeMinute,int openHourSat, int openMinuteSat, int closeHourSat, int closeMinuteSat) {
		this.timeZone = TimeZone.getTimeZone(timeZoneID);
		this.openHour = openHour;
		this.openMinute = openMinute;
		this.closeHour = closeHour;
		this.closeMinute = closeMinute;
		this.openHourSat = openHourSat;
		this.openMinuteSat = openMinuteSat;
		this.closeHourSat = closeHourSat;
		this.closeMinuteSat = closeMinuteSat;
	}

	public Calendar getCurrentTime()
    {
        return Calendar.getInstance(timeZone);
    }

    public TimeZone getTimeZone()
    {
        return timeZone;
    }
    
	public boolean isOpen() {
		Calendar now = getCurrentTime();
		boolean flag = true;
		if (Calendar.SATURDAY == now.get(Calendar.DAY_OF_WEEK)) {
			System.out.println("Its friday");
			flag = (openHourSat < now.get(Calendar.HOUR_OF_DAY) && now.get(Calendar.HOUR_OF_DAY) < closeHourSat)
					|| (openHourSat == now.get(Calendar.HOUR_OF_DAY) && openMinuteSat <= now.get(Calendar.MINUTE))
					|| (closeHourSat == now.get(Calendar.HOUR_OF_DAY) && now.get(Calendar.MINUTE) < closeMinuteSat);
		} else {
			flag = (openHour < now.get(Calendar.HOUR_OF_DAY) && now.get(Calendar.HOUR_OF_DAY) < closeHour)
					|| (openHour == now.get(Calendar.HOUR_OF_DAY) && openMinute <= now.get(Calendar.MINUTE))
					|| (closeHour == now.get(Calendar.HOUR_OF_DAY) && now.get(Calendar.MINUTE) < closeMinute);

		}
		return flag;
	}
    
    
}
