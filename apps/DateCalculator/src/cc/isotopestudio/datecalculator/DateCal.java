package cc.isotopestudio.datecalculator;
/*
 * Created by Mars on 9/26/2016.
 * Copyright ISOTOPE Studio
 */

public class DateCal {

    private static boolean ifYear(int a) {
        return a % 100 == 0 ? a % 400 == 0 : a % 4 == 0;
    }

    static int getMonthDays(int year, int a) {
        if (a == 2) {
            return ifYear(year) ? 29 : 28;
        } else if (a <= 7 && a > 0) {
            return a % 2 == 0 ? 30 : 31;
        } else if (a >= 8 && a <= 12) {
            return a % 2 == 0 ? 31 : 30;
        } else
            return 0;
    }

    static boolean ifLarger(int aYear, int aMonth, int aDay, int bYear, int bMonth, int bDay) {
        if (bYear > aYear)
            return true;
        if (aYear > bYear)
            return false;

        if (bMonth > aMonth)
            return true;
        if (aMonth > bMonth)
            return false;

        if (bDay > aDay)
            return true;
        if (aDay > bDay)
            return false;

        return true;

    }

    public static long cal(int aYear, int aMonth, int aDay, int bYear, int bMonth, int bDay) {

        long resultD = 0;


        if (bYear != aYear) {
            // 中间年份 日期总数
            int middleD = 0;
            for (int i = aYear + 1; i < bYear; i++)
                if (ifYear(i))
                    middleD++;
            middleD += (bYear - aYear - 1) * 365;
            resultD += middleD;

            // 今年1月1日到今天的日期数
            int lastD = 0;
            for (int i = 1; i < bMonth; i++) {
                lastD += getMonthDays(bYear, i);
            }
            lastD += bDay;
            resultD += lastD;

            // 出生日期到12月31日天数
            int firstD = 0;
            for (int i = aMonth + 1; i <= 12; i++)
                firstD += getMonthDays(bYear, i);
            firstD += getMonthDays(bYear, aMonth) - aDay;
            resultD += firstD;
        } else {
            if (aMonth != bMonth) {
                resultD += getMonthDays(aYear, aMonth) - aDay;
                for (int i = aMonth + 1; i < bMonth; i++) {
                    resultD += getMonthDays(aYear, i);
                }
                resultD += bDay;
            } else
                resultD = bDay - aDay;
        }

        return resultD;
    }

    static ISODate addDate(ISODate date, int days) {
        if (days >= 0)
            for (int i = 0; i < days; i++)
                date.addDay();
        else
            for (int i = 0; i > days; i--)
                date.subtractDay();
        return date;
    }
}
