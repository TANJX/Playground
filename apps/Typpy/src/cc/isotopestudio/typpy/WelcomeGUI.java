package cc.isotopestudio.typpy;
/*
 * Created by david on 2017/3/17.
 * Copyright ISOTOPE Studio
 */

import cc.isotopestudio.typpy.file.FileManager;
import cc.isotopestudio.typpy.sql.SqlManager;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class WelcomeGUI {
    private static final String VERSION = "1.0.0";

    private JFrame frame;
    private JPanel mainPane;
    private JButton startbtn;
    private JButton infobtn;
    private JButton exitbtn;
    private JButton statsbtn;

    WelcomeGUI() {
        frame = new JFrame("Typpy " + VERSION);
//        try {
//            UIManager.setLookAndFeel(com.alee.class.getName());
//        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
        frame.setContentPane(mainPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Toolkit toolkit = frame.getToolkit();
        Dimension size = toolkit.getScreenSize(); // resolution of the monitor
        frame.setLocation(size.width / 2 - frame.getWidth() / 2, size.height / 2 - frame.getHeight() / 2);
        frame.setResizable(false);

        startbtn.addActionListener(e -> {
            List<String> files = FileManager.getFiles();
            if (files.size() == 0) {
                JOptionPane.showMessageDialog(frame, "No text file found", "ERROR", JOptionPane.WARNING_MESSAGE);
            } else {
                String[] filesName = new String[files.size()];
                for (int i = 0; i < files.size(); i++) {
                    filesName[i] = files.get(i);
                }
                String s = (String) JOptionPane.showInputDialog(frame, "Choose a text file:\n", "File Chooser",
                        JOptionPane.PLAIN_MESSAGE, new ImageIcon(getClass().getResource("/green.gif")), filesName, files.get(0));
                if (s != null) {
                    System.out.println("Select " + s);
                    startbtn.setEnabled(false);
                    startbtn.setText("In progress");
                    String content = FileManager.getContent(s);
                    if (content == null || content.length() < 1) {
                        JOptionPane.showMessageDialog(frame, "Can't open file.\r\nAsk Mars ;-)", "ERROR", JOptionPane.WARNING_MESSAGE);
                    } else {
                        new TypeGUI(s, content);
                    }
                }
            }
        });
        infobtn.addActionListener(e -> {
            Toolkit.getDefaultToolkit().beep();
            URL img = getClass().getResource("/logo.gif");
            JOptionPane.showMessageDialog(frame,
                    "<html>" +
                            "<style> h1, h2, div, img {text-align: center; margin: 0;} </style>" +
                            "<h1>Typpy</h1>" +
                            "<h2>ISOTOPE Studio</h2>" +
                            "<div><img src=\"" + img + "\"></div>" +
                            "<h2>Mars</h2>" +
                            "<h2>2017.3.17</h2>" +
                            "</html>", "Info", JOptionPane.INFORMATION_MESSAGE, null);
        });
        exitbtn.addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });
    }

    void finish() {
        startbtn.setText("Start");
        startbtn.setEnabled(true);
    }

    void finish(String fileName, long response) {
        startbtn.setText("Start");
        startbtn.setEnabled(true);
        SqlManager.addRecord(fileName, response);
    }

}
