package cc.isotopestudio.datecalculator;/*
 * Created by Mars on 9/26/2016.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateGUI {
    private JSpinner aMonS;
    private JPanel panel1;
    private JSpinner aDayS;
    private JSpinner aYearS;
    private JSpinner bMonS;
    private JSpinner bDayS;
    private JSpinner bYearS;
    private JTextField resultBox;
    private JButton infobtn;
    private JButton exitbtn;
    private JPanel panel;

    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("DateGUI");
        frame.setContentPane(new DateGUI().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public DateGUI() {
        GregorianCalendar g = new GregorianCalendar();
        aYearS.setValue(g.get(Calendar.YEAR));
        aMonS.setValue(g.get(Calendar.MONTH) + 1);
        aDayS.setValue(g.get(Calendar.DATE));
        bYearS.setValue(g.get(Calendar.YEAR) + 1);
        bMonS.setValue(g.get(Calendar.MONTH) + 1);
        bDayS.setValue(g.get(Calendar.DATE));
        update();
        infobtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(frame, "Date Calculator 1.0.0\nISOTOPE Studio, Mars\n2016.9.26",
                        "Info",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        exitbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int temp = (int) aMonS.getValue();
                if (temp <= 0)
                    aMonS.setValue(1);
                else if (temp > 12)
                    aMonS.setValue(12);
                frame.dispose();
                System.exit(0);
            }
        });

        aYearS.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                vaildateA();
            }
        });
        aDayS.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                vaildateA();
            }
        });
        aMonS.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                vaildateA();
            }
        });
        bYearS.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                vaildateB();
            }
        });
        bDayS.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                vaildateB();
            }
        });
        bMonS.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                vaildateB();
            }
        });
    }

    private void vaildateA() {
        int temp = (int) aYearS.getValue();
        if (temp <= 0)
            aYearS.setValue(1);
        else if (temp > 2500)
            aYearS.setValue(2500);

        temp = (int) aMonS.getValue();
        if (temp <= 0)
            aMonS.setValue(1);
        else if (temp > 12)
            aMonS.setValue(12);

        temp = (int) aDayS.getValue();
        if (temp <= 0)
            aDayS.setValue(1);
        else if (temp > DateCal.getMonthDays((int) aYearS.getValue(), (int) aMonS.getValue()))
            aDayS.setValue(DateCal.getMonthDays((int) aYearS.getValue(), (int) aMonS.getValue()));
        update();
    }

    private void vaildateB() {
        int temp = (int) bYearS.getValue();
        if (temp <= 0)
            bYearS.setValue(1);
        else if (temp > 2500)
            bYearS.setValue(2500);

        temp = (int) bMonS.getValue();
        if (temp <= 0)
            bMonS.setValue(1);
        else if (temp > 12)
            bMonS.setValue(12);

        temp = (int) bDayS.getValue();
        if (temp <= 0)
            bDayS.setValue(1);
        else if (temp > DateCal.getMonthDays((int) bYearS.getValue(), (int) bMonS.getValue()))
            bDayS.setValue(DateCal.getMonthDays((int) bYearS.getValue(), (int) bMonS.getValue()));
        update();
    }

    private void update() {
        if (DateCal.ifLarger((int) aYearS.getValue(), (int) aMonS.getValue(), (int) aDayS.getValue(),
                (int) bYearS.getValue(), (int) bMonS.getValue(), (int) bDayS.getValue()))
            resultBox.setText("" + DateCal.cal((int) aYearS.getValue(), (int) aMonS.getValue(), (int) aDayS.getValue(),
                    (int) bYearS.getValue(), (int) bMonS.getValue(), (int) bDayS.getValue()));
        else
            resultBox.setText("Error!");
    }
}
