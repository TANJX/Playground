package cc.isotopestudio.datecalculator;/*
 * Created by Mars on 9/26/2016.
 * Copyright ISOTOPE Studio
 */

import cc.isotopestudio.datecalculator.record.Record;
import cc.isotopestudio.datecalculator.xml.DOMXML;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DateGUI {
    private JSpinner aMonS;
    private JPanel panel1;
    private JSpinner aDayS;
    private JSpinner aYearS;
    private JSpinner bMonS;
    private JSpinner bDayS;
    private JSpinner bYearS;
    private JTextField resultBoxP1;
    private JButton infobtn;
    private JButton exitbtn;
    private JPanel panel;
    private JButton switchbtn;
    private JButton modebtn;
    private JPanel panel2;
    private JSpinner cMonS;
    private JSpinner cDayS;
    private JSpinner cYearS;
    private JSpinner dDayS;
    private JTextField resultBoxP2;
    private JButton recordsbtn;
    private JPanel panel3;
    private JButton addbtn;
    public static RecordTable recordTable;
    public JScrollPane recordPane;
    //    public JScrollPane recordPane;

    private static JFrame frame;
    public static DateGUI dateGUI;

    public static void main(String[] args) {
        frame = new JFrame("Date Calculator 1.1.0");
        frame.setContentPane(new DateGUI().panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static DOMXML xml;

    public java.util.List<JPanel> recordPanes = new ArrayList<>();

    public DateGUI() {
        dateGUI = this;
        xml = new DOMXML();
        xml.init();
//        recordPane.setLayout(new FlowLayout());
        recordTable = new RecordTable();
        recordPane.setViewportView(recordTable);

        final boolean[] panelState = {true, true};
        panel2.setVisible(false);
        panel3.setVisible(true);
        GregorianCalendar g = new GregorianCalendar();
        aYearS.setValue(g.get(Calendar.YEAR));
        aMonS.setValue(g.get(Calendar.MONTH) + 1);
        aDayS.setValue(g.get(Calendar.DATE));
        bYearS.setValue(g.get(Calendar.YEAR) + 1);
        bMonS.setValue(g.get(Calendar.MONTH) + 1);
        bDayS.setValue(g.get(Calendar.DATE));
        cYearS.setValue(g.get(Calendar.YEAR) + 1);
        cMonS.setValue(g.get(Calendar.MONTH) + 1);
        cDayS.setValue(g.get(Calendar.DATE));
        updateP1();
        updateP2();
        infobtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(frame, "Date Calculator 1.1.0\nISOTOPE Studio, Mars\n2016.9.26",
                        "Info",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        exitbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        switchbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int m = (int) aMonS.getValue();
                int d = (int) aDayS.getValue();
                int y = (int) aYearS.getValue();
                aMonS.setValue(bMonS.getValue());
                aYearS.setValue(bYearS.getValue());
                aDayS.setValue(bDayS.getValue());
                bMonS.setValue(m);
                bYearS.setValue(y);
                bDayS.setValue(d);
            }
        });

        modebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (panelState[0]) {
                    panel2.setVisible(true);
                    panel1.setVisible(false);
                } else {
                    panel1.setVisible(true);
                    panel2.setVisible(false);
                }
                frame.pack();
                panelState[0] = !panelState[0];
            }
        });

        cMonS.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                validateC();
            }
        });
        cDayS.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                validateC();
            }
        });
        cYearS.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                validateC();
            }
        });
        dDayS.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                validateC();
            }
        });

        recordsbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (panelState[1]) {
                    panel3.setVisible(false);
                } else {
                    panel3.setVisible(true);
                }
                frame.pack();
                panelState[1] = !panelState[1];
            }
        });
        addbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new AddRecordGUI();
                    }
                });
            }
        });
    }

    public JPanel getRecordPane(String name, ISODate date) {
        JPanel panel = new JPanel();
        JLabel nameLb = new JLabel(name);
        panel.add(nameLb);
        panel.setSize(150, 100);
        return panel;
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
        updateP1();
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
        updateP1();
    }

    public void validateC() {
        int temp = (int) cYearS.getValue();
        if (temp <= 0)
            cYearS.setValue(1);
        else if (temp > 2500)
            cYearS.setValue(2500);

        temp = (int) cMonS.getValue();
        if (temp <= 0)
            cMonS.setValue(1);
        else if (temp > 12)
            cMonS.setValue(12);

        temp = (int) cDayS.getValue();
        if (temp <= 0)
            cDayS.setValue(1);
        else if (temp > DateCal.getMonthDays((int) cYearS.getValue(), (int) cMonS.getValue()))
            cDayS.setValue(DateCal.getMonthDays((int) cYearS.getValue(), (int) cMonS.getValue()));

        if ((int) dDayS.getValue() > 100000)
            dDayS.setValue(100000);
        else if ((int) dDayS.getValue() < -100000)
            dDayS.setValue(-100000);

        updateP2();
    }


    private void updateP1() {
        if (DateCal.ifLarger((int) aYearS.getValue(), (int) aMonS.getValue(), (int) aDayS.getValue(),
                (int) bYearS.getValue(), (int) bMonS.getValue(), (int) bDayS.getValue()))
            resultBoxP1.setText("" + DateCal.cal((int) aYearS.getValue(), (int) aMonS.getValue(), (int) aDayS.getValue(),
                    (int) bYearS.getValue(), (int) bMonS.getValue(), (int) bDayS.getValue()));
        else
            resultBoxP1.setText("-" + DateCal.cal((int) bYearS.getValue(), (int) bMonS.getValue(), (int) bDayS.getValue(),
                    (int) aYearS.getValue(), (int) aMonS.getValue(), (int) aDayS.getValue()));
    }

    private void updateP2() {
        ISODate date = DateCal.addDate(
                new ISODate((int) cYearS.getValue(), (int) cMonS.getValue(), (int) cDayS.getValue()),
                (int) dDayS.getValue());
        resultBoxP2.setText(date.getYear() + "-" + date.getMonth() + "-" + date.getDay());
    }

}