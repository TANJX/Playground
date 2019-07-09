package app.photoOperation;
/*
 * Created by marstanjx on 2019-06-05.
 * Copyright ISOTOPE Studio
 */

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Exposure {

    public static void main(String[] args) {
        File noteFolder = new File("/Users/marstanjx/Desktop/Japan/timelapse/3/");
        File outputFolder = new File("/Users/marstanjx/Desktop/Japan/timelapse/3/output/");
        List<File> notesFiles = Arrays.stream(Objects.requireNonNull(noteFolder.listFiles()))
                .filter(file -> file.getName().contains(".xmp"))
                .sorted((f1, f2) -> {
                    int i1 = Integer.parseInt(f1.getName().substring(3, 8));
                    int i2 = Integer.parseInt(f2.getName().substring(3, 8));
                    return i1 - i2;
                }).collect(Collectors.toList());
        double exp = -0.91;
        double step = (2.65 - exp) / 600;
        for (File file : notesFiles) {
            try {
                FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                final double finalExp = exp;
                List<String> lines = br.lines().map(s -> {
                    if (s.contains("crs:Exposure2012=")) {
                        return "   crs:Exposure2012=\"" + formatDouble(finalExp) + "\"";
                    }
                    return s;
                }).collect(Collectors.toList());
                br.close();
                write(String.join("\r\n", lines),
                        outputFolder.getAbsolutePath() + '/' + file.getName());
            } catch (IOException ex) {
                ex.printStackTrace();
                return;
            }
            exp += step;
        }
    }

    private static String formatDouble(double num) {
        num = Math.round(num * 100) / 100.0;
        return num >= 0 ? "+" + num : String.valueOf(num);
    }

    private static void write(String content, String path) throws IOException {
        System.out.println("Writing to " + path);
        FileOutputStream fos = new FileOutputStream(path);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        osw.write(content);
        osw.flush();
    }
}
