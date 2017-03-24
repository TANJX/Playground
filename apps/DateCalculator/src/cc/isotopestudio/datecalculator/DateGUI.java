package cc.isotopestudio.datecalculator;/*
 * Created by Mars on 9/26/2016.
 * Copyright ISOTOPE Studio
 */

import cc.isotopestudio.datecalculator.xml.DOMXML;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static cc.isotopestudio.datecalculator.CalMain.VERSION;

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
    static RecordTable recordTable;
    public JScrollPane recordPane;
    private JButton chronSortbtn;
    private JButton normalSortbtn;

    private static JFrame frame;

    public static DOMXML xml;

    DateGUI() {
        frame = new JFrame("Date Calculator " + VERSION);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        Toolkit toolkit = frame.getToolkit();
        Dimension size = toolkit.getScreenSize(); // resolution of the monitor
        frame.setLocation(size.width / 2 - frame.getWidth() / 2, (size.height - 50) / 2 - frame.getHeight() / 2);

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
        infobtn.addActionListener(e -> {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(frame, "Date Calculator " + VERSION + "\nISOTOPE Studio, Mars\n2017.3.19",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        exitbtn.addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });

        aYearS.addChangeListener(e -> vaildateA());
        aDayS.addChangeListener(e -> vaildateA());
        aMonS.addChangeListener(e -> vaildateA());
        bYearS.addChangeListener(e -> vaildateB());
        bDayS.addChangeListener(e -> vaildateB());
        bMonS.addChangeListener(e -> vaildateB());
        switchbtn.addActionListener(e -> {
            int m = (int) aMonS.getValue();
            int d = (int) aDayS.getValue();
            int y = (int) aYearS.getValue();
            aMonS.setValue(bMonS.getValue());
            aYearS.setValue(bYearS.getValue());
            aDayS.setValue(bDayS.getValue());
            bMonS.setValue(m);
            bYearS.setValue(y);
            bDayS.setValue(d);
        });

        modebtn.addActionListener(e -> {
            if (panelState[0]) {
                panel2.setVisible(true);
                panel1.setVisible(false);
            } else {
                panel1.setVisible(true);
                panel2.setVisible(false);
            }
            frame.pack();
            panelState[0] = !panelState[0];
        });

        cMonS.addChangeListener(e -> validateC());
        cDayS.addChangeListener(e -> validateC());
        cYearS.addChangeListener(e -> validateC());
        dDayS.addChangeListener(e -> validateC());

        recordsbtn.addActionListener(e -> {
            if (panelState[1]) {
                panel3.setVisible(false);
            } else {
                panel3.setVisible(true);
            }
            frame.pack();
            panelState[1] = !panelState[1];
        });
        addbtn.addActionListener(e -> EventQueue.invokeLater(AddRecordGUI::new));
        normalSortbtn.addActionListener(e -> {

        });
        chronSortbtn.addActionListener(e -> {

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

    private void validateC() {
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
