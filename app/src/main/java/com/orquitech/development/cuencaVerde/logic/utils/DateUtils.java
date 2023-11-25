package com.orquitech.development.cuencaVerde.logic.utils;

import com.orquitech.development.cuencaVerde.data.model.AppDate;

import java.util.Calendar;

public class DateUtils {

    public static AppDate getCurrentDate() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        AppDate date = new AppDate();
        date.setYear(String.valueOf(year));
        date.setMonth(String.valueOf(month + 1));
        date.setDay(String.valueOf(day));

        return date;
    }
}
