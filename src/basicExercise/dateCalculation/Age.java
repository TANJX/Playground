package basicExercise.dateCalculation;/*
 * Created by Mars on 9/23/2016.
 * Copyright ISOTOPE Studio
 */


import java.util.Calendar;
import java.util.GregorianCalendar;

public class Age {

    private boolean ifYear(int a) {
        if (a % 100 == 0) {
            if (a % 400 == 0)
                return true;
            else
                return false;
        } else {
            if (a % 4 == 0)
                return true;
            else
                return false;
        }
    }

    private int getMonthDays(int year, int a) {
        if (a == 2) {
            if (ifYear(year))
                return 29;
            else
                return 28;
        } else if (a <= 7 && a > 0) {
            if (a % 2 == 0)
                return 30;
            else
                return 31;
        } else if (a >= 8 && a <= 12) {
            if (a % 2 == 0)
                return 31;
            else
                return 30;
        } else
            return 0;
    }

    public void Calculuateage(int birthYear, int birthMonth, int birthDay) {

        GregorianCalendar g = new GregorianCalendar();
        int year = (int) g.get(Calendar.YEAR);
        int month = (int) g.get(Calendar.MONTH) + 1;
        int day = (int) g.get(Calendar.DATE);
        //System.out.println(year + " " + month + " " + day);
        int resultD = 0;

        // 中间年份 日期总数
        if (year != birthYear) {
            int middleD = 0;
            for (int i = birthYear + 1; i < year; i++)
                if (ifYear(i))
                    middleD++;
            middleD += (year - birthYear - 1) * 365;
            resultD += middleD;
            //System.out.println(middleD);
        }

        // 今年1月1日到今天的日期数
        int lastD = 0;
        for (int i = 1; i < month; i++) {
            lastD += getMonthDays(year, i);
        }
        lastD += day;
        //System.out.println(lastD);
        resultD += lastD;

        // 出生日期到12月31日天数
        if (year != birthYear) {
            int firstD = 0;
            for (int i = birthMonth + 1; i <= 12; i++)
                firstD += getMonthDays(year, i);
            firstD += getMonthDays(year, birthMonth) - birthDay;
            //System.out.println(firstD);
            resultD += firstD;
        }

        System.out.println(resultD + "days");
        System.out.println(resultD / 365.0 + "years");

    }
}
