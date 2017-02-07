/*
 * Copyright (c) Java Pathshala
 * All rights reserved.
 *
 * No parts of this source code can be reproduced without written consent from
 * Java Pathshala
 */
package com.jp.efficacy.util.date;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class TimeOfDay
{

    private Time time;
    private static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    private static final String GMT_TIME_FORMAT = "HH:mm:ss a (z)";
    private static final String TIME_WITHOUT_SECONDS_FORMAT = "HH:mm";

    public TimeOfDay(Calendar calendar)
    {
        this.time = new Time(calendar.getTime().getTime());
    }

    public TimeOfDay()
    {
        this.time = new Time(Calendar.getInstance().getTime().getTime());
    }

    public TimeOfDay(String timeZone)
    {
        if (timeZone == null)
        {
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        }
        else
        {
            TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
        }
        this.time = new Time(Calendar.getInstance().getTime().getTime());
    }

    public TimeOfDay(Timestamp timestamp)
    {
        this.time = new Time(timestamp.getTime());
    }

    public String asFormattedString()
    {
        return new SimpleDateFormat(DEFAULT_TIME_FORMAT).format(getDate());
    }

    public String asFormatedHourAndMinutesOnlyString()
    {
        return new SimpleDateFormat(TIME_WITHOUT_SECONDS_FORMAT).format(getDate());
    }

    public String asGMTFormattedString()
    {
        return new SimpleDateFormat(GMT_TIME_FORMAT).format(getDate());
    }

    private Date getDate()
    {
        return new Date();
    }
    
    public static void main(String[] args)
    {
        TimeOfDay tod = new TimeOfDay();
        System.out.println("TimeOfDay() : " + tod.asFormattedString());
        TimeOfDay tod1 = new TimeOfDay(Calendar.getInstance());
        System.out.println("TimeOfDay(calendar) : " + tod1.asFormattedString());
        TimeOfDay tod2 = new TimeOfDay(new Timestamp(new Date().getTime()));
        System.out.println("TimeOfDay(timestamp) : " + tod2.asFormattedString());

        TimeOfDay tod3 = new TimeOfDay("EST5EDT");
        System.out.println("TimeOfDay(timezone) : " + tod3.asFormattedString());
    }
}
