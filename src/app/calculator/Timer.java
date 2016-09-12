package app.calculator;/*
 * Created by Mars on 9/11/2016.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer {
    private JPanel panel1;
    private JButton STARTButton;
    private JButton PAUSEButton;
    private JTextField textField1;

    public Timer() {
        STARTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("Start!");
            }
        });
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("Vocabulary Plan V1.2");
        Timer timer = new Timer();
        frame.add(timer.panel1);
        frame.setSize(500,500);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
