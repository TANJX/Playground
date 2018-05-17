package app.japaneseOperation;
/*
 * Created by david on 2/8/2018.
 * Copyright ISOTOPE Studio
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordParserMain {
    public static void main(String[] args) {
        String line;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader("C:\\Users\\david\\Desktop\\1.txt");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            String lastline = bufferedReader.readLine();
            List<String> info = new ArrayList<>();
            line = bufferedReader.readLine();
            boolean end = false;
            while (true) {
                if (end || count(line, "\t") == 3) {
                    StringBuilder sub = new StringBuilder();
                    String[] split = lastline.split("\t");
                    sub.append(split[0]).append(",");
                    if (split.length > 1) sub.append(split[1]);
                    sub.append(",");
                    if (split.length > 2) sub.append(split[2]);
                    if (info.size() > 0) {
                        sub.append("<div>");
                        for (String s : info) {
                            sub.append("<div>").append(s).append("</div>");
                        }
                        sub.append("</div>");
                    }
                    sub.append(",");
                    if (split.length > 3) sub.append(split[3]);
                    info.clear();
                    lastline = line;
                    sb.append(sub).append("\r\n");
                    if (end) break;
                } else {
                    info.add(line);
                }
                if ((line = bufferedReader.readLine()) == null) {
                    end = true;
                }
            }
            // Always close files.
            bufferedReader.close();
            io.fileOperation.Write.write(new File("C:\\Users\\david\\Desktop\\2.txt"), sb.toString());
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file");
            ex.printStackTrace();
        }
    }

    private static int count(String str, String findStr) {
        int lastIndex = 0;
        int count = 0;

        while (lastIndex != -1) {

            lastIndex = str.indexOf(findStr, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += findStr.length();
            }
        }
        return count;
    }
}
