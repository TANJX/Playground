package io.fileOperation;
/*
 * Created by Mars Tan on 12/1/2016.
 * Copyright ISOTOPE Studio
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Write {
    public static void write(File file, String data) {
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
