package io.writ150;
/*
 * Created by david on 4/1/2018.
 * Copyright ISOTOPE Studio
 */

import io.fileOperation.Write;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileReader2 {
    public static void main(String args[]) {
        String pathin = "L:\\OneDrive\\College\\2018.1\\WRIT 150\\WP3\\data\\history short.csv";
        String pathout = "L:\\OneDrive\\College\\2018.1\\WRIT 150\\WP3\\data\\out.csv";

        File in = new File(pathin);

        List<String> dataList = readFile(in);
        dataList.remove(0);

        Map<String, Map<String, Integer>> dataMap = new HashMap<>();
        List<String> urlList = new ArrayList<>();

        for (String line : dataList) {
            String[] d = line.split(",");
            String date = d[0].replace("\"","");
            dataMap.putIfAbsent(date, new HashMap<>());
            String url = d[2].replaceFirst("^www\\.", "");
            if (!urlList.contains(url)) {
                urlList.add(url);
            }
            if (dataMap.get(date).containsKey(url)) {
                dataMap.get(date).put(url, dataMap.get(date).get(url) + 1);
            } else {
                dataMap.get(date).put(url, 1);
            }
        }

        List<String> dateList = dataMap.keySet().stream().sorted().collect(Collectors.toList());
        urlList.sort(String::compareTo);
        List<String> outputData = new ArrayList<>();
        outputData.add("," + dateList.stream().collect(Collectors.joining(",")));

        for (String url : urlList) {
            StringBuilder sb = new StringBuilder(url);
            for (String date : dateList) {
                sb.append(",");
                dataMap.get(date).putIfAbsent(url, 0);
                sb.append(dataMap.get(date).get(url));
            }
            outputData.add(sb.toString());
        }

        Write.write(new File(pathout),outputData.stream().collect(Collectors.joining("\r\n")));

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


}
