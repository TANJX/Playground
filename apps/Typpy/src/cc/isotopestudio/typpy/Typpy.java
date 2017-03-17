package cc.isotopestudio.typpy;
/*
 * Created by david on 2017/3/17.
 * Copyright ISOTOPE Studio
 */

import cc.isotopestudio.typpy.file.FileManager;
import cc.isotopestudio.typpy.sql.SqlManager;

public class Typpy {

    static WelcomeGUI welcomeGUI;

    public static void main(String args[]) {
        System.out.println("Start");
        SqlManager.init();
        FileManager.init();
        welcomeGUI = new WelcomeGUI();
    }

}
