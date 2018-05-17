package cc.isotopestudio.advancedcopy2;
/*
 * Created by Mars Tan on 3/12/2017.
 * Copyright ISOTOPE Studio
 */

import java.io.File;

public class AdvancedCopy {

    public static void main(String[] args) {

        File folderA = new File("W:\\Occupation\\works\\");
        File folderC = new File("W:\\Occupation\\works\\");
//        File folderC = new File("W:\\Photo\\");
        FileManager fileManager = new FileManager(folderA, folderC);
        fileManager.ingestFolderA();
        fileManager.ingestFolderC();
        fileManager.copyToC();
    }

}
