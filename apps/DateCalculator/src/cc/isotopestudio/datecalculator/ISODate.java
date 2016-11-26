package cc.isotopestudio.datecalculator;/*
 * Created by Mars on 9/27/2016.
 * Copyright ISOTOPE Studio
 */

public class ISODate {
    private int year;
    private int month;
    private int day;

    public ISODate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    void addDay() {
        day++;
        if (DateCal.getMonthDays(year, month) < day) {
            month++;
            day = 1;
        }
        if (month > 12) {
            year++;
            month = 1;
        }
    }

    void subtractDay() {
        day --;
        if (day < 1) {
            month--;
            day = DateCal.getMonthDays(year, month);
        }
        if (month < 1) {
            year--;
            month = 12;
            day = DateCal.getMonthDays(year, month);
        }
    }
}
