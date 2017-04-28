package cc.isotopestudio.mcbackup;
/*
 * Created by Mars Tan on 4/28/2017.
 * Copyright ISOTOPE Studio
 */

import java.io.File;
import java.util.Date;
import java.util.Scanner;

public class BackupMain {

    public static void log(String line) {
        System.out.println("[" + new Date().toString() + "] " + line);
    }

    public static void main(String[] args) {
        log("Start");
        log("Minecraft Server Map Backup");
        log("ISOTOPE Studio");
        log("April 28, 2017");
        File folder = new File("backups");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        Thread backupThread = new Thread(() -> {
            while (true) {
                try {
                    log("----");
                    log("Backup Starts");
                    log("----");
                    ZipCompressing.zip("backups\\" +
                                    new Date().toString()
                                            .replaceAll(" ", "-")
                                            .replaceAll(":", ".")
                                    + ".zip",
                            new File("world"));
                    log("----");
                    log("Backup Ends");
                    log("----");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000 * 60 * 60 * 24 * 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        backupThread.start();
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        while (!"stop".equals(line)) {
            line = sc.nextLine();
        }
        System.exit(0);
    }

}
