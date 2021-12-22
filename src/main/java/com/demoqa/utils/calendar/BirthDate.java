package com.demoqa.utils.calendar;

public class BirthDate {
    private final int year;
    private final int monthNumber;
    private final String monthName;
    private final int day;

    public BirthDate(int year, int monthNumber, String monthName, int day) {
        this.year = year;
        this.monthNumber = monthNumber;
        this.monthName = monthName;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public String getMonthName() {
        return monthName;
    }

    public int getDay() {
        return day;
    }
}
