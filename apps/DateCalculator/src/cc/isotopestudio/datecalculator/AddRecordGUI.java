package cc.isotopestudio.datecalculator;
/*
 * Created by Mars Tan on 11/27/2016.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static cc.isotopestudio.datecalculator.DateGUI.dateGUI;
import static cc.isotopestudio.datecalculator.DateGUI.recordTable;
import static cc.isotopestudio.datecalculator.DateGUI.xml;

public class AddRecordGUI {
    private JSpinner aMonS;
    private JSpinner aDayS;
    private JSpinner aYearS;
    private JTextField nameField;
    private JPanel mainPane;
    private JButton addbtn;

    private JFrame frame;

    public AddRecordGUI() {
        frame = new JFrame("Add New Record");
        frame.setContentPane(mainPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        GregorianCalendar g = new GregorianCalendar();
        aYearS.setValue(g.get(Calendar.YEAR));
        aMonS.setValue(g.get(Calendar.MONTH) + 1);
        aDayS.setValue(g.get(Calendar.DATE));
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
        addbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if ("".equals(name)) {
                    JOptionPane.showMessageDialog(frame, "Please type in a name",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    ISODate date = new ISODate((int) aYearS.getValue(), (int) aMonS.getValue(), (int) aDayS.getValue());
                    xml.addRecord(name, date);
                    dateGUI.recordPanes.add(dateGUI.getRecordPane(name, date));
                    dateGUI.recordPane.removeAll();
                    for (JPanel pane : dateGUI.recordPanes)
                        dateGUI.recordPane.add(pane);
                    frame.dispose();
                    recordTable.initRowsData();
                }
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
    }


}
