package cc.isotopestudio.timecalculator;/*
 * Created by Mars Tan on 10/28/2016.
 * Copyright ISOTOPE Studio
 */

import java.util.Scanner;

public class Time {


    public static void main(String[] args) {
        while (true) {
            System.out.println("Please type two specific time for me: ");
            Scanner input = new Scanner(System.in);
            String[] s = input.nextLine().split(" |\\,|and|:");
            int[] a = new int[s.length];
            if (s.length <= 0) {

            } else {
                for (int b = 0; b < s.length; b++) {
                    int si = Integer.parseInt(s[b] + "");
                    a[b] = si;
                }
            }
            System.out.println(getInterval(a[0], a[1], a[2], a[3]));
        }
    }

    public static String getInterval(int h1, int m1, int h2, int m2) {
        if (m1 < m2) {
            m1 = m1 + 60;
            h1 = h1 - 1;
        } else {
        }
        ;
        if (h1 < h2)
            h1 = h1 + 24;
        int b = h1 - h2;
        int c = m1 - m2;
        if (h1 <= 47 && h2 <= 23 && m1 <= 118 && m2 <= 59)
            return b + ":" + c;
        else
            return null;
    }

}
