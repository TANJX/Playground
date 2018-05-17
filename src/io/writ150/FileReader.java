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

public class FileReader {
    public static void main(String args[]) {
        String path = "L:\\OneDrive\\College\\2018.1\\WRIT 150\\WP3\\data\\InternetTextsforR\\individualtextfiles\\";
        File map = new File("L:\\OneDrive\\College\\2018.1\\WRIT 150\\WP3\\data\\metadataforalltexts.csv");
        File useless = new File("L:\\OneDrive\\College\\2018.1\\WRIT 150\\WP3\\data\\useless.txt");


        List<String> mapList = readFile(map);
        List<Article> articles = new ArrayList<>();
        List<String> uselessWord = readFile(useless);

        for (int i = 1; i <= 132; i++) {
            String filename = i + ".txt";
            if (i < 10) {
                filename = "00" + i + ".txt";
            } else if (i < 100) {
                filename = "0" + i + ".txt";
            }
            File file = new File(path + filename);
            List<String> text = readFile(file);
            int year = Integer.parseInt(mapList.get(i).split(",")[1]);
            String type = mapList.get(i).split(",")[0];
            articles.add(new Article(year, text, type));
        }
        Map<Integer, Integer> countMap1 = new HashMap<>();
        Map<Integer, Integer> countMap2 = new HashMap<>();
        Map<Integer, Integer> countMaptotal = new HashMap<>();
        Map<Integer, Map<String, Integer>> freqMaptotal = new HashMap<>();

        // change
        String query = "usage";

        Pattern pattern = Pattern.compile("\\w+");
        for (Article article : articles) {
            freqMaptotal.putIfAbsent(article.getYear(), new HashMap<>());
            Map<String, Integer> stringIntegerMap = freqMaptotal.get(article.getYear());
            int count = 0;
            for (String line : article.getText()) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String word = matcher.group();
                    if (word.length() <= 3) continue;
                    if (uselessWord.contains(word)) {
                        continue;
                    }
                    if (stringIntegerMap.containsKey(word)) {
                        stringIntegerMap.put(word, stringIntegerMap.get(word) + 1);
                    } else stringIntegerMap.put(word, 1);
                    if (word.equals(query)) {
                        count++;
                    }
                }
            }
            Map<Integer, Integer> countMap = article.getType().equals("Newspaper") ? countMap1 : countMap2;
            if (countMap.containsKey(article.getYear())) {
                countMap.put(article.getYear(), countMap.get(article.getYear()) + count);
            } else {
                countMap.put(article.getYear(), count);
            }
            if (countMaptotal.containsKey(article.getYear())) {
                countMaptotal.put(article.getYear(), countMaptotal.get(article.getYear()) + count);
            } else {
                countMaptotal.put(article.getYear(), count);
            }
        }
        Map<String, Integer> sub = new HashMap<>();
        for (Map<String, Integer> integerMapEntry : freqMaptotal.entrySet().stream().filter(integerMapEntry -> integerMapEntry.getKey() >= 2008).map(Map.Entry::getValue).collect(Collectors.toSet())) {
            for (String word : integerMapEntry.keySet()) {
                if (sub.containsKey(word)) {
                    sub.put(word, sub.get(word) + integerMapEntry.get(word));
                } else {
                    sub.put(word, integerMapEntry.get(word));
                }
            }
        }

        System.out.print(sortByValue(sub).entrySet().stream().limit(50)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining("\r\n")));

/*
        countMap1.forEach((year, count) -> {
//            System.out.println(year + ": Newspaper " + count);
//            System.out.println(year + ": Paper " + countMap2.get(year));

            System.out.print(year + ",");
            sortByValue(freqMaptotal.get(year)).entrySet().stream()
                    .limit(12).forEach(stringIntegerEntry ->
//                    System.out.print(stringIntegerEntry.getKey() + "(" + stringIntegerEntry.getValue()+") " ));
                    System.out.print(stringIntegerEntry.getKey() + ","));
            System.out.println();
        });
        */
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
