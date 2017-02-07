/*
 * Copyright (c) Java Pathshala
 * All rights reserved.
 *
 * No parts of this source code can be reproduced without written consent from
 * Java Pathshala
 */
package com.jp.efficacy.util.date;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class DateRange
{

    private final CalendarDay start;
    private final CalendarDay end;

    public DateRange(CalendarDay start, CalendarDay end)
    {
        this.start = start;
        this.end = end;
    }

    public boolean isWithRange(CalendarDay calendarDay)
    {
        return calendarDay.isAfter(start) && calendarDay.isBefore(end);
    }
}
