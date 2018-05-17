package io.writ150;
/*
 * Created by david on 4/1/2018.
 * Copyright ISOTOPE Studio
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileReader3 {
    public static void main(String args[]) {
        String path = "L:\\OneDrive\\College\\2018.1\\WRIT 150\\WP3\\Word Clouds\\WordCloud.csv";


        List<String> data = readFile(new File(path));
        System.out.println(data.size());
        System.out.println(data.stream().filter(s -> s.contains("Google") || s.contains("google")).count());
        System.out.println(data.stream().filter(s -> s.contains("Facebook") || s.contains("facebook")).count());
        System.out.println(data.stream().filter(s -> s.contains("YouTube")||s.contains("youtube")||s.contains("Youtube")).count());
        System.out.println(data.stream().filter(s -> s.contains("YouTube")||s.contains("youtube")||s.contains("Youtube")).count());
    }

    private static List<String> readFile(File file) {
        List<String> list = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new java.io.FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                list.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

}
