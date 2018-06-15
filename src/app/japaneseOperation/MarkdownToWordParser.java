package app.japaneseOperation;
/*
 * Created by david on 2/8/2018.
 * Copyright ISOTOPE Studio
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MarkdownToWordParser {
    public static void main(String[] args) {
        String noteFolderPath = "C:\\Users\\david\\Desktop\\notes\\";
        String outputFolder = "C:\\Users\\david\\Desktop\\output\\";
        File noteFolder = new File(noteFolderPath);
        File[] notesFiles = noteFolder.listFiles();
        assert notesFiles != null;
        for (File file : notesFiles) {
            if (!file.getName().contains(".md")) continue;
            try {

                FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                List<String> sb = new ArrayList<>();
                List<String> info = new ArrayList<>();
                String line = br.readLine();
                String lastline = line;
                boolean start = false;
                boolean end = false;
                while (true) {
                    if (!start) {
                        if (line.equals("## µ¥´Ê")) {
                            start = true;
                            line = br.readLine();
                            lastline = line;
                            continue;
                        }
                        lastline = line;
                        line = br.readLine();
                        continue;
                    }
                    if (!line.equals(""))
                        if (end || count(line, "/") >= 2) {
                            StringBuilder sub = new StringBuilder();
                            List<String> parts = new ArrayList<>(Arrays.asList(lastline.split("/")));
                            if (parts.get(0).equals("")) parts.remove(0);
                            try {
                                sub.append(parts.get(0)).append(",");
                            } catch (Exception e) {
                                System.out.println(lastline);
                                System.out.println(line);
                                System.out.println(file.getName());
                                e.printStackTrace();
                                return;
                            }
                            if (parts.get(1).matches("\\[.*].*")) {
                                parts.add(1, "");
                            }
                            if (parts.size() > 1) sub.append(parts.get(1));
                            sub.append(",");
                            if (parts.size() > 2) sub.append(parts.get(2));
                            if (info.size() > 0) {
                                sub.append("<div>");
                                for (String s : info) {
                                    sub.append("<div>").append(s).append("</div>");
                                }
                                sub.append("</div>");
                            }
                            sub.append(",");
                            if (parts.size() > 3) sub.append(parts.get(3));
                            info.clear();

                            lastline = line;
                            sb.add(sub.toString());
                            if (end) break;
                        } else {
                            while (line.contains("!")) {
                                line = line.replaceFirst("!", "<span style=\"color: orange\">")
                                        .replaceFirst("!", "</span>");
                            }
                            while (line.contains("**")) {
                                line = line.replaceFirst("\\*\\*", "<b>")
                                        .replaceFirst("\\*\\*", "</b>");
                            }
                            if (line.startsWith("^")) {
                                line = "<span style=\"background-color: lightgreen;\">" + line.substring(1) + "</span>";
                            }
                            if (line.startsWith("%")) {
                                line = "<span style=\"background-color: #FFFF66;\">" + line.substring(1) + "</span>";
                            }
                            if (line.startsWith("$")) {
                                line = "<span style=\"background-color: lightgreen;\">" + line.substring(1) + "</span>";
                            }
                            if (line.startsWith("&&")) {
                                line = line.substring(2);
                            } else if (line.startsWith("&")) {
                                line = line.substring(1);
                            }
                            info.add(line);
                        }
                    if ((line = br.readLine()) == null) {
                        end = true;
                    } else if (line.contains("##")) {
                        end = true;
                    }
                }
                br.close();
                sb.remove(0);
                write(sb.stream().collect(Collectors.joining("\r\n")), outputFolder + file.getName().replace(".md", ".txt"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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

    private static void write(String content, String path) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        osw.write(content);
        osw.flush();
    }
}
