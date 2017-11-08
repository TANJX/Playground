package com.marstanjx.usccoursetracker;
/*
 * Created by david on 11/7/2017.
 * Copyright ISOTOPE Studio
 */

public class CourseTrackerMain {


    public static void main(String[] args) {
        new CourseTrackerGUI("Course Tracker");
        CoursePreview.init();
        new Thread(() -> {
            while (true) {
                ParseJson.updateCourses();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).run();
    }
}
