package cc.isotopestudio.advancedcopy2;
/*
 * Created by Mars Tan on 3/12/2017.
 * Copyright ISOTOPE Studio
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


class FileManager {

    private File folderA;
    private File folderC;

    private Set<File> folderCFiles = new HashSet<>();
    private Set<File> folderAFiles = new HashSet<>();


    FileManager(File folderA, File folderC) {
        this.folderA = new File(folderA.getAbsolutePath());
        this.folderC = new File(folderC.getAbsolutePath());
        countFolderA(folderA);
    }

    void ingestFolderC() {
        addAllFile(folderC, folderCFiles);
        System.out.println(folderCFiles.size() + " files in Folder C");
    }

    void ingestFolderA() {
        addAllFile(folderA, folderAFiles);
        System.out.println(folderAFiles.size() + " files in Folder A");
    }

    private void addAllFile(File folder, Set<File> files) {
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isDirectory())
                addAllFile(new File(file.getAbsolutePath()), files);
            else {
                if (file.length() > 10 * 1024 * 1024 && !file.getAbsolutePath().contains(".tmp.drivedownload")) {
                    files.add(file);
                }
            }
        }
    }

    private void countFolderA(File folder) {
        for (File file : folder.listFiles()) {
            if (file.isDirectory())
                countFolderA(new File(file.getAbsolutePath()));
        }
    }

    void copyToC() {
        new Thread(() -> {
            copyToC("");
            System.out.println();
            System.out.println();
            System.out.println();
            fileObjects.sort((o1, o2) -> (int) (o1.size - o2.size));
            fileObjects.forEach(sameFiles -> System.out.println(sameFiles.path1 + "\r\n" + sameFiles.path2 + "\r\n" + sameFiles.size));
            System.out.println(fileObjects.stream().mapToLong(o -> o.size).sum());
        }).start();
    }

    private List<SameFiles> fileObjects = new ArrayList<>();

    private void copyToC(String path) {
        for (File file : folderCFiles) {
            if (!file.isDirectory()) {
                for (File folderCFile : folderAFiles) {
                    if (folderCFile.getAbsolutePath().equals(file.getAbsolutePath())) continue;
                    if (compareFiles(folderCFile, file)) {
                        boolean same = false;
                        for (SameFiles fileObject : fileObjects) {
                            if (fileObject.path2.equals(folderCFile.getAbsolutePath()) &&
                                    fileObject.path1.equals(file.getAbsolutePath())) {
                                same = true;
                            }
                        }
                        if (!same) {
                            fileObjects.add(new SameFiles(folderCFile.getAbsolutePath(), file.getAbsolutePath(), file.length() / 1024 / 1024));
                            String line = folderCFile.getAbsolutePath() + " and " + file.getAbsolutePath() + " (" + file.length() / 1024 / 1024 + "MB)";
                            System.out.println(line);
                            break;
                        }
                    }
                }
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

    private static final int MAX = 100000;

    private boolean sameContent(Path file1, Path file2) throws IOException {
        final long size = Files.size(file1);
        if (size != Files.size(file2))
            return false;

        if (size < 4096)
            return Arrays.equals(Files.readAllBytes(file1), Files.readAllBytes(file2));

        try (InputStream is1 = Files.newInputStream(file1);
             InputStream is2 = Files.newInputStream(file2)) {
            // Compare byte-by-byte.
            // Note that this can be sped up drastically by reading large chunks
            // (e.g. 16 KBs) but care must be taken as InputStream.read(byte[])
            // does not neccessarily read a whole array!
            int data;
            int count = 0;
            while (((data = is1.read()) != -1) && count++ < MAX)
                if (data != is2.read())
                    return false;
        }

        return true;
    }

    class SameFiles {
        String path1;
        String path2;
        long size;

        public SameFiles(String p1, String p2, long s) {
            path1 = p1;
            path2 = p2;
            size = s;
        }
    }

}
