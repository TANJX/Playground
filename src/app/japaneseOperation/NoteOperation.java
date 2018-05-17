package app.japaneseOperation;/*
 * Created by Mars Tan on 12/20/2016.
 * Copyright ISOTOPE Studio
 */

import java.io.*;
import java.util.*;

public class NoteOperation {

    public static void main(String[] args) {
        // The name of the file to open.
        String fileName = "C:\\Users\\david\\Desktop\\japanese\\Japanese2.htm";

        // This will reference one line at a time
        String line;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            int count = 0;
            String s1 = null, s2 = null, s3 = null;
            String target = "<P style=\"MARGIN: 0in\">&nbsp;</P>";
            StringBuilder sb = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                if (s1 == null) {
                    s1 = line;
                    sb.append(line).append("\r\n");
                    continue;
                }
                if (s2 == null) {
                    s2 = line;
                    sb.append(line).append("\r\n");
                    continue;
                }
                if (s3 == null) {
                    s3 = line;
                    sb.append(line).append("\r\n");
                    continue;
                }
                if (s1.equals(target) && s2.equals(target) && s3.equals(target)) {
                    count++;
                    sb.append("<DIV id=\"c").append(count+1).append("\">\r\n");
                } else sb.append(line).append("\r\n");
                s1 = s2;
                s2 = s3;
                s3 = line;
            }
            // Always close files.
            bufferedReader.close();
            io.fileOperation.Write.write(new File("C:\\Users\\david\\Desktop\\japanese\\Japanese.htm"), sb.toString());
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

    private static void f1() {
        // The name of the file to open.
        String fileName = "C:\\Users\\david\\Desktop\\japanese\\Japanese.htm";

        // This will reference one line at a time
        String line;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            Map<String, Integer> map = new HashMap<>();
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.contains("style=")) continue;
                ;
                int i = line.indexOf("style=") + 7;
                char c = 0;
                try {
                    c = line.charAt(i - 1);
                } catch (Exception e) {
                    System.out.println(line);
                    e.printStackTrace();
                }
                StringBuilder s = new StringBuilder();
                do {
                    s.append(line.charAt(i++));
                }
                while (i < line.length() && line.charAt(i) != c);
                String string = "style=" + c + s.toString() + c;
                if (!map.containsKey(string)) {
                    map.put(string, 1);
                } else map.put(string, map.get(string) + 1);
            }
            printMap(sortByValue(map));
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

    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        list.sort((o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    private static <K, V> void printMap(Map<K, V> map) {
        int count = 0;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (count++ >= 1000) return;
            System.out.println(count + ": " + entry.getKey()
                    + " [" + entry.getValue() + "]");
        }
    }


}


