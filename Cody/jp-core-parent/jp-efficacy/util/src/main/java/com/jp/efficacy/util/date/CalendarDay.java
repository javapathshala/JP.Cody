/*
 * Copyright (c) Java Pathshala
 * All rights reserved.
 *
 * No parts of this source code can be reproduced without written consent from
 * Java Pathshala
 */
package com.jp.efficacy.util.date;

import com.jp.efficacy.util.formats.DateFormats;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is a calendar, accurate to the DAY and no further.
 * It DOES NOT understand times of day, nor should it.
 * See {@link TimeOfDay} for representing times.
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class CalendarDay
{

    private Calendar calendar;
    private static final long MILLISECONDS_IN_A_DAY = 24 * 60 * 60 * 1000;
    private static final String COLON = ":";

    /**
     * Takes years, months and days in the way you'd expect, e.g. 2016, 12, 25 is Christmas Day 2006
     *
     * @param year
     * @param month
     * @param day
     * @param timeZone default - UTC
     */
    public CalendarDay(int year, int month, int day, String timeZone)
    {
        setTimeZone(timeZone);
        this.calendar = Calendar.getInstance();
        this.calendar.clear();
        this.calendar.setLenient(true);
        this.calendar.set(year, month - 1, day, 0, 0, 0);
    }

    /**
     * Takes years, months and days in the way you'd expect, e.g. 2016, 12, 25 is Christmas Day 2006
     *
     * @param year
     * @param month
     * @param day
     */
    public CalendarDay(int year, int month, int day)
    {
        this.calendar = Calendar.getInstance();
        this.calendar.clear();
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        this.calendar.setLenient(true);
        this.calendar.set(year, month - 1, day, 0, 0, 0);
        this.calendar.set(Calendar.MILLISECOND, 0);
    }

    public CalendarDay()
    {
        this(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH) + 1,
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }

    public CalendarDay(String timeZone)
    {
        setTimeZone(timeZone);
        this.calendar = Calendar.getInstance();
    }

    public CalendarDay(String timeZone, boolean setZero)
    {
        this(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH) + 1,
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
//        this.calendar.clear();
        setTimeZone(timeZone);
        this.calendar.setLenient(true);
        this.calendar.set(Calendar.HOUR, 0);
        this.calendar.set(Calendar.MINUTE, 0);
        this.calendar.set(Calendar.SECOND, 0);
        this.calendar.set(Calendar.MILLISECOND, 0);
    }
    
    public CalendarDay(String date, String format, boolean setZero) {
        this(date, format);
        if (setZero) {
            this.calendar.set(Calendar.HOUR, 0);
            this.calendar.set(Calendar.MINUTE, 0);
            this.calendar.set(Calendar.SECOND, 0);
            this.calendar.set(Calendar.MILLISECOND, 0);
        } else {
            this.calendar.set(Calendar.HOUR, 23);
            this.calendar.set(Calendar.MINUTE, 59);
            this.calendar.set(Calendar.SECOND, 59);
            this.calendar.set(Calendar.MILLISECOND, 0);
        }
    }

    public CalendarDay(long time)
    {
        this.calendar = Calendar.getInstance();
        this.calendar.clear();
        setTimeZone(null);
        this.calendar.setLenient(true);
        this.calendar.setTimeInMillis(time);
    }

    public CalendarDay(long time, String timeZone)
    {
        this.calendar = Calendar.getInstance();
        this.calendar.clear();
        setTimeZone(timeZone);
        this.calendar.setLenient(true);
        this.calendar.setTimeInMillis(time);
    }

    public CalendarDay(String inputDate, String format)
    {
//        this.calendar = Calendar.getInstance();
//        this.calendar.clear();
//        setTimeZone(timeZone);
//        Date date = parseDate(inputDate, DateFormats.DATE_DD_MM_YYYY_SLASH);
        Date date = parseDate(inputDate, format);
        this.calendar = Calendar.getInstance();
        this.calendar.clear();
        this.calendar.setTimeInMillis(date.getTime());
    }

    public boolean isAfter(CalendarDay when)
    {
        return this.calendar.after(when.calendar);
    }

    public boolean isBefore(CalendarDay when)
    {
        return this.calendar.before(when.calendar);
    }

    public CalendarDay subtractDays(int days)
    {
        return addDays(-days);
    }

    public CalendarDay yesterday()
    {
        return this.subtractDays(1);
    }

    /**
     * Add days to CalendarDay
     *
     * @param days
     * @return
     */
    public CalendarDay addDays(int days)
    {
        Calendar newCalendar = (Calendar) calendar.clone();
        newCalendar.add(Calendar.DAY_OF_MONTH, days);
        return new CalendarDay(newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH) + 1,
                newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Number of days elapsed
     *
     * @param calendarDay
     * @return
     */
    public long daysPassedSince(CalendarDay calendarDay)
    {
        return (calendar.getTimeInMillis() - calendarDay.getTimeInMillis()) / MILLISECONDS_IN_A_DAY;
    }

    /**
     * Check whether today is holiday. verify today is in list of holidays
     * Format - MMDD
     *
     * @param holidayList
     * @return
     */
    public boolean isHoliday(List<String> holidayList)
    {
        String today = this.getDateMMDD();
        return holidayList.contains(today);
    }

    /**
     * Is Date passed is same
     *
     * @param other
     * @return
     */
    public boolean isSameDate(CalendarDay other)
    {
        if (other == null)
        {
            return false;
        }
        if (getYear() == other.getYear())
        {
            if (getMonth() == other.getMonth())
            {
                if (getDayOfMonth() == other.getDayOfMonth())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if calendar day is between BeginDay & EndDay
     *
     * @param beginCalendarDay
     * @param endCalendarDay
     * @return
     */
    public boolean isBetween(CalendarDay beginCalendarDay, CalendarDay endCalendarDay)
    {
        return (isSameDate(beginCalendarDay) || isAfter(beginCalendarDay))
                && (isBefore(endCalendarDay) || isSameDate(endCalendarDay));
    }

    public String getDateMMDD()
    {
        return formatDate(this.calendar.getTime(), DateFormats.DATE_MMDD);
    }

    public String getDateYYYYMMDD()
    {
        return formatDate(this.calendar.getTime(), DateFormats.DATE_YYYMMDD);
    }

    public String getDateMMDDYYYY()
    {
        return formatDate(this.calendar.getTime(), DateFormats.DATE_MMDDYYYY);
    }

    public String getDateDDMMYYYYDash()
    {
        return formatDate(this.calendar.getTime(), DateFormats.DATE_DD_MM_YYYY_DASH);
    }

    public String getDateYYYYMMDDDash()
    {
        return formatDate(this.calendar.getTime(), DateFormats.DATE_YYYY_MM_DD_DASH);
    }

    public String getDateTimeDDMMYYYYHHMMSSDash()
    {
        return formatDate(this.calendar.getTime(), DateFormats.DATE_TIME_DDMMYYYYHHMMSS_DASH);
    }

    public String getDate(String format)
    {
        return formatDate(this.calendar.getTime(), format);
    }

    private String formatDate(Date date, String format)
    {
        return new SimpleDateFormat(format).format(date);
    }

    private Date parseDate(String date, String format)
    {
        Date returnDate = new Date();
        try
        {
            returnDate = new SimpleDateFormat(format).parse(date);
        }
        catch (ParseException ex)
        {
            System.err.println("Unable ");
        }
        return returnDate;
    }

    public long getTimeInMillis()
    {
        return calendar.getTimeInMillis();
    }

    private int getDayOfMonth()
    {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    private int getMonth()
    {
        return calendar.get(Calendar.MONTH);
    }

    private int getYear()
    {
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Calculate Processing Time
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public String calculateProcessTime(long startTime, long endTime)
    {
        long timeTaken = endTime - startTime;
        String finalTaken = translateLongToHrsMinSec(timeTaken);
        return finalTaken;
    }

    public String calculateProcessTime(CalendarDay endDay)
    {
        long timeTaken = endDay.getTimeInMillis() - this.getTimeInMillis();
        String finalTaken = translateLongToHrsMinSec(timeTaken);
        return finalTaken;
    }

    /**
     * Convert long time to HHmmss format
     *
     * @param timeTaken
     * @return
     */
    private static String translateLongToHrsMinSec(long timeTaken)
    {
        String finalTaken = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(timeTaken),
                TimeUnit.MILLISECONDS.toMinutes(timeTaken)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeTaken)),
                TimeUnit.MILLISECONDS.toSeconds(timeTaken)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeTaken)));
        return finalTaken;
    }

    private String[] getTimeZoneList()
    {
        return TimeZone.getAvailableIDs();
    }

    private boolean validateZone(String timeZone)
    {
        String[] str = getTimeZoneList();
        return Arrays.asList(str).contains(timeZone);
    }

    private void setTimeZone(String timeZone)
    {
        if (timeZone == null)
        {
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        }
        else if (!validateZone(timeZone))
        {
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        }
        else
        {
            TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
        }
    }

    public String getLastWorkingDayAsString(List<String> holidays, String format)
    {
        String previousWorkingDate = "";
        try
        {
            Calendar calInstance = Calendar.getInstance();
            calInstance.setTime(this.calendar.getTime());
            int weekDay = 0;

            if (null == holidays)
            {
                holidays = new ArrayList();
            }

            String tempDate = "";
            do
            {
                calInstance.add(Calendar.DATE, -1);
                weekDay = calInstance.get(Calendar.DAY_OF_WEEK);
                tempDate = formatDate(calInstance.getTime(), DateFormats.DATE_MMDD);
            }
            while (weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY
                    || holidays.contains(tempDate));

            Date newDate = calInstance.getTime();
            if (null == format)
            {
                format = DateFormats.DATE_YYYY_MM_DD_DASH;
            }
            previousWorkingDate = new SimpleDateFormat(format).format(newDate);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return previousWorkingDate;
    }

    public static void main(String[] args)
    {

        CalendarDay calendarDay = new CalendarDay("9/1/2017", "EST5EDT");
        System.out.println(calendarDay.getDateYYYYMMDD());

//        CalendarDay calendarDay = new CalendarDay(2016, 12, 12, "EST5EDT");
////        CalendarDay calendarDay = new CalendarDay("America/New_York");
//        System.out.println(calendarDay.calendar.getTime().getTime());
//        System.out.println(calendarDay.getTimeInMillis());
//        List<String> holidays = new ArrayList();
//        holidays.add("1209");
//        System.out.println(calendarDay.getLastWorkingDayAsString(holidays, null));
//        CalendarDay calendarDay1 = new CalendarDay(2016, 11, 15, null);
//        System.out.println(calendarDay1.calendar.getTime());
//
//        CalendarDay calendarDay2 = new CalendarDay(2016, 12, 13, "ABC");
//        System.out.println(calendarDay2.calendar.getTime());
//
//        CalendarDay calendarDay3 = new CalendarDay(2016, 12, 13);
//        System.out.println(calendarDay3.calendar.getTime());
//
//        CalendarDay today = new CalendarDay();
//        System.out.println(today.calendar.getTime());
//
//        System.out.println(today.daysPassedSince(calendarDay1));
//
//        System.out.println(today.isSameDate(calendarDay3));
//
//        System.out.println(today.isBetween(calendarDay1, calendarDay3));
    }

}
