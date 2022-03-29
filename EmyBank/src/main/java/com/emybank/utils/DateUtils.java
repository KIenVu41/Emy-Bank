package com.emybank.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtils {
	public LocalDate addDaysSkippingWeekends(LocalDate date) {
	    LocalDate result = date;
	    int addedDays = 0;
	    while (addedDays < 3) {
	        result = result.plusDays(1);
	        if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
	            ++addedDays;
	        }
	    }
	    return result;
	}
}
