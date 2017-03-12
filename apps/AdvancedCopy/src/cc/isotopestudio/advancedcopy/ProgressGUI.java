package cc.isotopestudio.advancedcopy;
/*
 * Created by Mars Tan on 3/12/2017.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class ProgressGUI {
    JProgressBar progressBar1;
    private JPanel panel1;
    private JLabel Progress;
    private JTextArea logTextPane;

    ProgressGUI() {
        JFrame frame = new JFrame("Progress");
        frame.setContentPane(panel1);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        Toolkit toolkit = frame.getToolkit();
        Dimension size = toolkit.getScreenSize(); // resolution of the monitor
        frame.setLocation(size.width / 2 - frame.getWidth() / 2, size.height / 2 - frame.getHeight() / 2);
        logTextPane.setText(getDatePrefix() + "Start working");
    }

    private String getDatePrefix() {
        return "[" + new Date().toString() + "] ";
    }

    void log(String info) {
        logTextPane.setText(logTextPane.getText() + "\n" + getDatePrefix() + info);
    }

}
