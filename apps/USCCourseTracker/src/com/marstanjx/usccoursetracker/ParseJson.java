package com.marstanjx.usccoursetracker;
/*
 * Created by david on 11/7/2017.
 * Copyright ISOTOPE Studio
 */

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

class ParseJson {

    private static void newCourse(JSONObject course, JSONObject section) {
        int available = section.getInt("spaces_available");
        int registered = section.getInt("number_registered");
        String s = section.getString("start_time");
        String e = section.getString("end_time");
        String d = section.getString("day");
        String instructor = "";
        if (!section.isNull("instructor"))
            if (section.get("instructor") instanceof JSONObject) {
                instructor = section.getJSONObject("instructor").getString("first_name") + ", "
                        + section.getJSONObject("instructor").getString("last_name");
            } else if (section.get("instructor") instanceof JSONArray) {
                instructor = section.getJSONArray("instructor").getJSONObject(0).getString("first_name") + ", "
                        + section.getJSONArray("instructor").getJSONObject(0).getString("last_name");
            }
        String courseID = course.getString("PublishedCourseID");
        new Course(section.getString("id"), course.getJSONObject("CourseData").getString("prefix"), courseID, instructor, d, s, e, registered, available);
    }

    static void updateCourses() {
        List<String> courses = ReadFile.getCourse();
        List<String> deparments = Arrays.asList("ASCJ", "COMM", "CSCI", "WRIT");
        for (String deparment : deparments) {
            try {
                JSONObject obj = new JSONObject(readUrlContent("http://web-app.usc.edu/web/soc/api/classes/" + deparment + "/20181", 2000));
                JSONArray arr = obj.getJSONObject("OfferedCourses").getJSONArray("course");
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject course = arr.getJSONObject(i);
                    if (course.getJSONObject("CourseData").get("SectionData") instanceof JSONArray) {
                        JSONArray sections = course.getJSONObject("CourseData").getJSONArray("SectionData");
                        for (int i1 = 0; i1 < sections.length(); i1++) {
                            JSONObject section = sections.getJSONObject(i1);
                            if (courses.contains(section.getString("id"))) {
                                Course courseObj = Course.get(section.getString("id"));
                                if (courseObj == null)
                                    newCourse(course, section);
                                else {
                                    int available = section.getInt("spaces_available");
                                    int registered = section.getInt("number_registered");
                                    courseObj.setAvailable(available);
                                    courseObj.setRegistered(registered);
                                }
                            }
                        }
                    } else if (course.getJSONObject("CourseData").get("SectionData") instanceof JSONObject) {
                        JSONObject section = course.getJSONObject("CourseData").getJSONObject("SectionData");
                        if (courses.contains(section.getString("id"))) {
                            Course courseObj = Course.get(section.getString("id"));
                            if (courseObj == null)
                                newCourse(course, section);
                            else {
                                int available = section.getInt("spaces_available");
                                int registered = section.getInt("number_registered");
                                courseObj.setAvailable(available);
                                courseObj.setRegistered(registered);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Course.courseList);
    }

    private static String readUrlContent(String address, int timeout) throws IOException {
        StringBuilder contents = new StringBuilder(2048);
        BufferedReader br = null;

        try {
            URL url = new URL(address);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setConnectTimeout(timeout);
            huc.setReadTimeout(timeout);
            huc.setRequestMethod("GET");
            huc.connect();
            br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                contents.append(line);
            }
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return contents.toString();
    }

}
