/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.calculator;

/**
 *
 * @author Administrator
 */
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 * Created by Mars on 5/30/2016.
 * Copyright ISOTOPE Studio
 */
public class History extends JFrame {
    private static final JTextArea text = new JTextArea();
    private static JFrame fm;

    public static void run() {
        fm = new JFrame("History");
        JScrollPane fp = new JScrollPane();
        Container con = fm.getContentPane();
        fp.setViewportView(text);
        con.add(fp);
        text.setEditable(false);
        fm.setSize(500, 400);
        fm.setLocation(500, 500);
    }

    public static void on(boolean b) {
        fm.setVisible(b);
    }

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static void addInfo(String info) {
        text.append("[" + format.format(new java.util.Date()) + "] "
                + info + "\n");
    }

}
