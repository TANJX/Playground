package cc.isotopestudio.datecalculator.record;/*
 * Created by Mars on 9/28/2016.
 * Copyright ISOTOPE Studio
 */

import cc.isotopestudio.datecalculator.xml.XMLImpl;

import java.util.List;

public class Record {
    public static List<Record> records;

    private String name;
    private int year;
    private int month;
    private int day;

    public Record(String name, int year, int month, int day) {
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
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
        try {
            records = XMLImpl.getRecords();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String text = "";
        for (Record record : records) {
            text += record.toString();
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
