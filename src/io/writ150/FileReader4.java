package io.writ150;
/*
 * Created by david on 4/1/2018.
 * Copyright ISOTOPE Studio
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileReader4 {
    public static void main(String args[]) {
        String path = "C:\\Users\\david\\Desktop\\watch-history.html";


        List<String> data = readFile(new File(path));

        boolean first = true;
        List<String> seg = new ArrayList<>();
        for (String line : data) {
            if (line.equals("")) {
                if (!first) {
                    try {
                        System.out.println(seg.size() < 6 ? seg.get(1) : seg.get(2));
                    } catch (Exception e) {
                        System.out.println(seg);
                    }
                }
                first = false;
                seg.clear();
            } else
            seg.add(line);
        }
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
