package cc.isotopestudio.datecalculator.record;
/*
 * Created by Mars on 9/28/2016.
 * Copyright ISOTOPE Studio
 */

import cc.isotopestudio.datecalculator.DateCal;
import cc.isotopestudio.datecalculator.ISODate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static cc.isotopestudio.datecalculator.DateGUI.xml;

public class Record {
    private static final List<Record> records = new ArrayList<>();

    private final String name;
    private final int year;
    private final int month;
    private final int day;
    private final ISODate date;

    private Record(String name, ISODate date) {
        this.name = name;
        this.date = date;
        this.year = date.getYear();
        this.month = date.getMonth();
        this.day = date.getDay();
    }

    public String getName() {
        return name;
    }

    public ISODate getDate() {
        return date;
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

    public static List<Record> getAll() {
        records.clear();
        for (String record : xml.getDates()) {
            records.add(new Record(record, xml.getDateByName(record)));
        }
        return records;
    }

    private static final GregorianCalendar g = new GregorianCalendar();

    public long getDiff() {
        return DateCal.cal(g.get(Calendar.YEAR), g.get(Calendar.MONTH) + 1, g.get(Calendar.DATE), year, month, day);
    }

    @Override
    public String toString() {
        return "Record{" + "name='" + name + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                "}\n";
    }

    public static void sort() {
    }
}
