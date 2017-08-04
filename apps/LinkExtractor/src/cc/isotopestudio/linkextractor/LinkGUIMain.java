package cc.isotopestudio.linkextractor;
/*
 * Created by Mars Tan on 6/28/2017.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkGUIMain {
    private JPanel mainPane;
    private JTextField inputField;
    private JTextField outputField;


    private LinkGUIMain() {
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                String line = inputField.getText() + e.getKeyChar();
                String reg = "[\u4e00-\u9fa5]";
                Pattern p = Pattern.compile(reg);
                Matcher m = p.matcher(line);
                outputField.setText(m.replaceAll(""));
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Link Extractor");
        frame.setContentPane(new LinkGUIMain().mainPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
