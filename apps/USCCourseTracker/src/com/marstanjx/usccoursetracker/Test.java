package com.marstanjx.usccoursetracker;
/*
 * Created by david on 2018/06/14.
 * Copyright ISOTOPE Studio
 */

public class Test {

    public static void main(String[] a) {
        SemesterGUI semesterGUI = new SemesterGUI("20183");
        while (true) {
            try {
                semesterGUI.updateCourses();
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }
}
