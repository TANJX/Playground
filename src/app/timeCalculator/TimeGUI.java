package app.timeCalculator;/*
 * Created by Mars Tan on 11/8/2016.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeGUI {
    private static JFrame frame;
    private JPanel panel;
    private JButton infoButton;
    private JButton exitButton;
    private JSpinner time1HourSpinner;
    private JSlider time1HourSlider;
    private JPanel time1HourPanel;
    private JPanel mainInsidePane;
    private JPanel time1MinutePanel;
    private JPanel resultPanel;
    private JTextField resultField;
    private JPanel time2HourPanel;
    private JPanel time2MinutePanel;
    private JSpinner time2MinuteSpinner;
    private JSlider time2MinuteSlider;
    private JSlider time2HourSlider;
    private JSpinner time2HourSpinner;
    private JSlider time1MinuteSlider;
    private JSpinner time1MinuteSpinner;

    public TimeGUI() {

        time1HourSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateResult();
            }
        });
        time1MinuteSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateResult();
            }
        });
        time2HourSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateResult();
            }
        });
        time2MinuteSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateResult();
            }
        });
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(frame, "Time Calculator 0.1.0\nISOTOPE Studio, Mars\n2016.11.8",
                        "Info",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });
    }

    private void updateResult() {
        int h1 = (int) time1HourSpinner.getValue();
        int m1 = (int) time1MinuteSpinner.getValue();
        int h2 = (int) time2HourSpinner.getValue();
        int m2 = (int) time2MinuteSpinner.getValue();
        resultField.setText(Time.getInterval(h1, m1, h2, m2));
    }

    public static void main(String[] args) {
        frame = new JFrame("Time Calculator 0.1.0");
        frame.setContentPane(new TimeGUI().panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

    }
}
