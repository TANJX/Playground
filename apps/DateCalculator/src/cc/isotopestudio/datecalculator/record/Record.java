package cc.isotopestudio.datecalculator.record;/*
 * Created by Mars on 9/28/2016.
 * Copyright ISOTOPE Studio
 */

import cc.isotopestudio.datecalculator.ISODate;

import java.util.List;

import static cc.isotopestudio.datecalculator.DateGUI.xml;

public class Record {
    public static List<String> records;

    private String name;
    private int year;
    private int month;
    private int day;

    public Record(String name, ISODate date) {
        this.name = name;
        this.year = date.getYear();
        this.month = date.getMonth();
        this.day = date.getDay();
    }

    public String getName() {
        return name;
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

    public static String getAll() {
        records = xml.getDates();
        String text = "";
        for (String record : records) {
            text += new Record(record, xml.getDateByName(record)).toString();
        }
        return text;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Record{");
        sb.append("name='").append(name).append('\'');
        sb.append(", year=").append(year);
        sb.append(", month=").append(month);
        sb.append(", day=").append(day);
        sb.append("}\n");
        return sb.toString();
    }

    public static void sort() {
    }
}
