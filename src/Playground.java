/*
 * Created by Mars Tan on 12/20/2016.
 * Copyright ISOTOPE Studio
 */


import io.properties.FileOperation;

import java.io.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Playground {

    public static void main(String[] args) {
        // The name of the file to open.
        String fileName = "O:\\OneDrive\\Coding\\Practice\\[2017.9.12]Mars Inc Webside\\site\\japanese\\course.txt";

        // This will reference one line at a time
        String line;

        List<Integer> firstCourse = Arrays.asList(1, 19, 29, 41, 52, 61, 71, 80, 88, 97);

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            int unit = -1;
            int course = 0;
            while ((line = bufferedReader.readLine()) != null) {
                course++;
                if (firstCourse.contains(course)) {
                    unit++;
                    if (unit != 0) System.out.println("</div>");
                    System.out.println(
                            "<div id=\"unit-" + unit + "\" class=\"btn-group second-btn\" role=\"group\" style=\"display: none\" aria-label=\"Units-" + unit + "\">");
                }
                System.out.println("<button type=\"button\" class=\"btn btn-info\" onClick=\"window.location.href='#c" + (course) + "'\">" + line + "</button>");

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
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
}


