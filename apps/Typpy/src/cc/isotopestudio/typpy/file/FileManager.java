package cc.isotopestudio.typpy.file;
/*
 * Created by david on 2017/3/17.
 * Copyright ISOTOPE Studio
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class FileManager {

    private static File dataFolder;

    public static void init() {
//        dataFolder = new File("C:\\OneDrive\\apps\\Mars' Collection\\Typpy\\text");
        dataFolder = new File("text");
        System.out.println(dataFolder.getAbsoluteFile());
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
            write(new File(dataFolder.getAbsolutePath() + "\\Please put your text files here"),
                    "Please put your text files in this folder as txt file");
        }
    }

    public static List<String> getFiles() {
        List<String> result = new ArrayList<>();
        File[] files = dataFolder.listFiles();
        if (files == null) {
            return result;
        }
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.endsWith(".txt")) {
                result.add(fileName);
                System.out.println(fileName);
            }
        }
        Collections.reverse(result);
        return result;
    }

    public static String getContent(String fileName) {
        System.out.println("Reading file");
        try {
            String encoding = "GBK";
            File file = new File(dataFolder.getAbsolutePath() + "\\" + fileName);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt;
                String txt = "";
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    txt += lineTxt + " ";
                    System.out.println(lineTxt);
                }
                read.close();
                System.out.println("End of file");
                return txt;
            } else {
                System.out.println("Can't find file");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Can't read file");
            e.printStackTrace();
            return null;
        }
    }

    private static void write(File file, String data) {
        try {
            if (!file.exists())
                file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();

            System.out.println("Write " + file + " Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
