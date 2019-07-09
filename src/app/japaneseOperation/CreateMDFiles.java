package app.japaneseOperation;
/*
 * Created by marstanjx on 9/8/18.
 * Copyright ISOTOPE Studio
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CreateMDFiles {

    private final static String[] MONTH = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
            "Aug", "Sep", "Oct", "Nov", "Dec"};
    private final static String[] NUM = {"一", "二", "三", "四", "五", "六", "七",
            "八", "九", "十"};

    public static void main(String[] args) {
        String outPath = "/Library/WebServer/Documents/japanese/notes/n5/";

        // 19,1-1
        Map<String, String> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        String line = sc.next();
        while (!"999".equals(line)) {
            String[] split = line.split(",");
            map.put(split[0], split[1]);
            line = sc.next();
        }
        // 1-1, 3/16/17
        Map<String, String> dateMap = new HashMap<>();
        String fileName = "/Users/marstanjx/Desktop/test.csv";
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                if (split.length <= 1) continue;
                Arrays.stream(split[0].split("/")).forEach(System.out::println);
                int date[] = Arrays.stream(split[0].split("/"))
                        .mapToInt(Integer::parseInt).toArray();
                String dateString = MONTH[date[0] - 1] + " " + date[1] + ", 2017";
                for (int i = 1; i < split.length; i++) {
                    String courseName = split[i];
                    if (courseName.contains("第") && courseName.contains("课")) {
                        int course = Integer.parseInt(
                                courseName.substring(1, courseName.indexOf("课")));
                        int section = Integer.parseInt(courseName.substring(courseName.length() - 1));
                        dateMap.put(course + "-" + section, dateString);
                    }
                }
            }
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

        /*
        for (String key : map.keySet()) {
            WriteFile.write(outPath,map.get(key)+".md","");
        }
        */

        List<String> list = new ArrayList<>(map.values());
        list.sort((s1, s2) -> {
            int course1 = Integer.parseInt(s1.split("-")[0]);
            int course2 = Integer.parseInt(s2.split("-")[0]);
            int section1 = Integer.parseInt(s1.split("-")[1]);
            int section2 = Integer.parseInt(s2.split("-")[1]);
            return (course1 * 2 + section1) - (course2 * 2 + section2);
        });
        for (String id : list) {
            int course = Integer.parseInt(id.split("-")[0]);
            int section = Integer.parseInt(id.split("-")[1]);
            if ((course - 1) % 4 == 0 && section == 1) {
                int chapter = ((course + 3) / 4);
                System.out.println("<chapter id=\"" + chapter + "\" name=\"第" + NUM[chapter - 1] + "单元\">");
            }
            System.out.println("  <lecture id=\"" + id + "\">");
            System.out.println("    <name>第" + course + "課（" + section + "）</name>");
            System.out.println("    <date>" + dateMap.get(id) + "</date>");
            System.out.println("  </lecture>");
            if ((course) % 4 == 0 && section == 2) {
                System.out.println("</chapter>");
            }
        }
    }
}
