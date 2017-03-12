package cc.isotopestudio.japanesetest;
/*
 * Created by Mars Tan on 2/17/2017.
 * Copyright ISOTOPE Studio
 */

import cc.isotopestudio.japanesetest.sql.SqlManager;

import javax.swing.*;
import java.awt.*;

public class WelcomeGUI {
    private static final String VERSION = "1.1.0";

    private static JFrame frame;
    private JPanel panel1;
    private JButton hiriganaQuizButton;
    private JButton katakanaQuizButton;
    private JButton infoButton;
    private JButton exitButton;

    private WelcomeGUI() {
        hiriganaQuizButton.addActionListener(e -> new TesterGUI(true));
        katakanaQuizButton.addActionListener(e -> new TesterGUI(false));
        infoButton.addActionListener(e -> {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(frame, "Japanese Test " + VERSION + "\nISOTOPE Studio, Mars\n2016.9.26",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        exitButton.addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });
    }


    public static void main(String[] args) {
        SqlManager.init();

        frame = new JFrame("Japanese Test " + VERSION);
        frame.setContentPane(new WelcomeGUI().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Toolkit toolkit = frame.getToolkit();
        Dimension size = toolkit.getScreenSize(); // resolution of the monitor
        frame.setLocation(size.width / 2 - frame.getWidth() / 2, size.height / 2 - frame.getHeight() / 2);
        frame.setResizable(false);
    }

}
