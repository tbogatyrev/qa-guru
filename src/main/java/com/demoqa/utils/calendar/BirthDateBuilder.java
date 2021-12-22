package com.demoqa.utils.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class BirthDateBuilder {
    private int year;
    private int monthNumber;
    private int day;
    private LocalDate date;

    public BirthDateBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    public BirthDateBuilder setMonthNumber(int monthNumber) {
        this.monthNumber = monthNumber;
        return this;
    }

    public BirthDateBuilder setDay(int day) {
        this.day = day;
        return this;
    }

    public BirthDate build() {
        this.date = LocalDate.of(year, monthNumber, day);
        return new BirthDate(year, monthNumber, getNameOfMonth(), day);
    }

    private DayOfWeek getDayOfWeek() {
        return date.getDayOfWeek();
    }

    private String getNameOfMonth() {
        return date.format(DateTimeFormatter.ofPattern("MMMM", new Locale("en")));
    }
}
