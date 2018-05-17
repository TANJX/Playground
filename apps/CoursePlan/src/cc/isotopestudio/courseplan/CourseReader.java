package cc.isotopestudio.courseplan;
/*
 * Created by david on 2/18/2018.
 * Copyright ISOTOPE Studio
 */

import java.io.*;
import java.util.*;

public class CourseReader {
    public static final Map<String, List<Course>> majors = new HashMap<>();

    public static void init() {
        File file = new File("C:\\Users\\david\\Documents\\Project\\Workspace\\Playground\\apps\\CoursePlan\\src\\req\\");
        System.out.println(file.getAbsolutePath());
        for (File majorFile : file.listFiles()) {
            String filename = majorFile.getName();
            if (filename.endsWith(".major")) {
                String major = filename.replace(".major", "");
//                System.out.println("---" + major);
                List<Course> majorCourses = new ArrayList<>();
                try {
                    // FileReader reads text files in the default encoding.
                    FileReader fileReader =
                            new FileReader(majorFile);

                    // Always wrap FileReader in BufferedReader.
                    BufferedReader bufferedReader =
                            new BufferedReader(fileReader);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
//                        System.out.println(line);
                        String[] s1 = line.split("\\. ");
                        majorCourses.add(new Course(Integer.parseInt(s1[1]),
                                new ArrayList<>(Arrays.asList(s1[0].split(", ")))));
                    }
                    // Always close files.
                    bufferedReader.close();
                } catch (FileNotFoundException ex) {
                    System.out.println(
                            "Unable to open file");
                } catch (IOException ex) {
                    System.out.println(
                            "Error reading file");
                }
                majors.put(major, majorCourses);
            }
        }
    }

    public static void main(String[] s) {
        init();
    }
}
