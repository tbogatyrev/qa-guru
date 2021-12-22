package com.demoqa.utils;

import com.demoqa.utils.calendar.BirthDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class StringUtils {

    public static String getBirthDateLocatorString(BirthDate birthDate) {

        LocalDate date = LocalDate.of(birthDate.getYear(), birthDate.getMonthNumber(), birthDate.getDay());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy", new Locale("en"));
        return date.format(formatter).replace(String.valueOf(birthDate.getDay()), birthDate.getDay() + "th");
    }
}
