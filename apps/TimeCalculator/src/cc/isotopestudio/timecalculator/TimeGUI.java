package cc.isotopestudio.timecalculator;/*
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

    private static final String VERSION = "0.2.0";

    public TimeGUI() {

        time1HourSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                validate(0);
                sync(0, 0);
                updateResult();
            }
        });
        time1HourSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                validate(0);
                sync(0, 1);
                updateResult();
            }
        });
        time1MinuteSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                validate(1);
                sync(1, 0);
                updateResult();
            }
        });
        time1MinuteSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                validate(1);
                sync(1, 1);
                updateResult();
            }
        });
        time2HourSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                validate(2);
                sync(2, 0);
                updateResult();
            }
        });
        time2HourSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                validate(2);
                sync(2, 1);
                updateResult();
            }
        });
        time2MinuteSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                validate(3);
                sync(3, 0);
                updateResult();
            }
        });
        time2MinuteSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                validate(3);
                sync(3, 1);
                updateResult();
            }
        });
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(frame, "Time Calculator " + VERSION + "\nISOTOPE Studio, Mars\n2016.11.9",
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

    /**
     * @param p 0, 1, 2, or 3 (time1hour, time1minute, time2hour, time2minute)
     */
    private void validate(int p) {
        switch (p) {
            case (0):
                time1HourSpinner.setValue(getValidatedValue(0, (int) time1HourSpinner.getValue()));
                return;
            case (1):
                time1MinuteSpinner.setValue(getValidatedValue(1, (int) time1MinuteSpinner.getValue()));
                return;
            case (2):
                time2HourSpinner.setValue(getValidatedValue(0, (int) time2HourSpinner.getValue()));
                return;
            case (3):
                time2MinuteSpinner.setValue(getValidatedValue(1, (int) time2MinuteSpinner.getValue()));
        }
    }

    /**
     * @param type  0 or 1 (hour or minute)
     * @param value value need to be adjusted
     * @return validated value
     */
    private int getValidatedValue(int type, int value) {
        if (value < 0)
            return 0;
        if (type == 0 && value > 24)
            return 24;
        if (type == 1 && value > 60)
            return 60;
        return value;

    }

    /**
     * @param p    0, 1, 2, or 3 (time1hour, time1minute, time2hour, time2minute)
     * @param type 0 or 1 (spinner or slider)
     */
    private void sync(int p, int type) {
        switch (p) {
            case (0):
                if (type == 0) {
                    time1HourSlider.setValue((Integer) time1HourSpinner.getValue());
                }
                if (type == 1) {
                    time1HourSpinner.setValue(time1HourSlider.getValue());
                }
                return;
            case (1):
                if (type == 0) {
                    time1MinuteSlider.setValue((Integer) time1MinuteSpinner.getValue());
                }
                if (type == 1) {
                    time1MinuteSpinner.setValue(time1MinuteSlider.getValue());
                }
                return;
            case (2):
                if (type == 0) {
                    time2HourSlider.setValue((Integer) time2HourSpinner.getValue());
                }
                if (type == 1) {
                    time2HourSpinner.setValue(time2HourSlider.getValue());
                }
                return;
            case (3):
                if (type == 0) {
                    time2MinuteSlider.setValue((Integer) time2MinuteSpinner.getValue());
                }
                if (type == 1) {
                    time2MinuteSpinner.setValue(time2MinuteSlider.getValue());
                }
        }
    }

    public static void main(String[] args) {
        frame = new JFrame("Time Calculator " + VERSION);
        frame.setContentPane(new TimeGUI().panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

    }
}
