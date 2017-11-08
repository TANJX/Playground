package com.marstanjx.usccoursetracker;
/*
 * Created by david on 11/7/2017.
 * Copyright ISOTOPE Studio
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadFile {

    static List<String> getCourse() {
        String fileName = "C:\\Users\\david\\Desktop\\course.txt";

        // This will reference one line at a time
        List<String> courses = new ArrayList<>();
        String line;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                courses.add(line);
            }
            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
        return courses;
    }

}
