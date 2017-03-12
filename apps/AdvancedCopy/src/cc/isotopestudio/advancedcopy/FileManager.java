package cc.isotopestudio.advancedcopy;
/*
 * Created by Mars Tan on 3/12/2017.
 * Copyright ISOTOPE Studio
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;


class FileManager {

    private ProgressGUI progressGUI;

    private File folderA;
    private File folderB;
    private File folderC;

    private Set<File> folderCFiles = new HashSet<>();

    private int finished = 0;
    private int total = 0;


    FileManager(ProgressGUI progressGUI, File folderA, File folderB, File folderC) {
        this.progressGUI = progressGUI;
        this.folderA = new File(folderA.getAbsolutePath());
        this.folderB = new File(folderB.getAbsolutePath());
        this.folderC = new File(folderC.getAbsolutePath());
        countFolderA(folderA);
    }

    void ingestFolderC() {
        addAllFile(folderC);
        progressGUI.log(folderCFiles.size() + " files in Folder C");
    }

    private void addAllFile(File folder) {
        for (File file : folder.listFiles()) {
            if (file.isDirectory())
                addAllFile(new File(file.getAbsolutePath()));
            else {
                folderCFiles.add(file);
            }
        }
    }

    private void countFolderA(File folder) {
        for (File file : folder.listFiles()) {
            if (file.isDirectory())
                countFolderA(new File(file.getAbsolutePath()));
            else {
                total++;
            }
        }
    }

    void copyToC() {
        new Thread(() -> {
            copyToC("");
        }).start();
    }

    private void copyToC(String path) {
        File directory;
        if (path.equals("")) {
            directory = folderA;
        } else {
            directory = new File(folderA.getAbsolutePath() + "\\" + path);
        }
        progressGUI.log("Directory: " + directory.toString());
        for (File file : directory.listFiles()) {
            if (!file.isDirectory()) {
                boolean exist = false;
                for (File folderCFile : folderCFiles) {
                    if (compareFiles(folderCFile, file)) {
                        // File exist in folder C
                        exist = true;
                        progressGUI.log(folderCFile.getAbsolutePath() + " and " + file.getAbsolutePath() + " are the same");
                        break;
                    }
                }
                if (!exist) {
                    progressGUI.log("Rename " + file.getAbsolutePath());
                    file.renameTo(new File(folderB.getAbsolutePath() + "\\" + path + "\\" + file.getName()));
                }
                finished++;
            } else {
                copyToC(path + "\\" + file.getName());
            }
        }
    }

    private boolean compareFiles(File A, File B) {
        try {
            boolean b = sameContent(Paths.get(A.getAbsolutePath()), Paths.get(B.getAbsolutePath()));
            return b;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
    * http://stackoverflow.com/questions/27379059/determine-if-two-files-store-the-same-content
    */
    private boolean sameContent(Path file1, Path file2) throws IOException {
        final long size = Files.size(file1);
        if (size != Files.size(file2))
            return false;

//        if (size < 4096)
//            return Arrays.equals(Files.readAllBytes(file1), Files.readAllBytes(file2));
//
//        try (InputStream is1 = Files.newInputStream(file1);
//             InputStream is2 = Files.newInputStream(file2)) {
//            // Compare byte-by-byte.
//            // Note that this can be sped up drastically by reading large chunks
//            // (e.g. 16 KBs) but care must be taken as InputStream.read(byte[])
//            // does not neccessarily read a whole array!
//            int data;
//            while ((data = is1.read()) != -1)
//                if (data != is2.read())
//                    return false;
//        }

        return true;
    }

    int getFinishedPercentage() {
        return (int) (Math.round(100.0 * finished / total));
    }

}
