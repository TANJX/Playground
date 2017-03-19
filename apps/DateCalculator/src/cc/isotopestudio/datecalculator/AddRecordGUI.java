package cc.isotopestudio.datecalculator;
/*
 * Created by Mars Tan on 11/27/2016.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static cc.isotopestudio.datecalculator.DateGUI.*;

public class AddRecordGUI {
    private JSpinner aMonS;
    private JSpinner aDayS;
    private JSpinner aYearS;
    private JTextField nameField;
    private JPanel mainPane;
    private JButton addbtn;

    private final JFrame frame;

    AddRecordGUI() {
        frame = new JFrame("Add New Record");
        frame.setContentPane(mainPane);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        Toolkit toolkit = frame.getToolkit();
        Dimension size = toolkit.getScreenSize(); // resolution of the monitor
        frame.setLocation(size.width / 2 - frame.getWidth() / 2, (size.height - 50) / 2 - frame.getHeight() / 2);

        GregorianCalendar g = new GregorianCalendar();
        aYearS.setValue(g.get(Calendar.YEAR));
        aMonS.setValue(g.get(Calendar.MONTH) + 1);
        aDayS.setValue(g.get(Calendar.DATE));
        aYearS.addChangeListener(e -> vaildateA());
        aDayS.addChangeListener(e -> vaildateA());
        aMonS.addChangeListener(e -> vaildateA());
        addbtn.addActionListener(e -> {
            String name = nameField.getText();
            if ("".equals(name)) {
                JOptionPane.showMessageDialog(frame, "Please type in a name",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                ISODate date = new ISODate((int) aYearS.getValue(), (int) aMonS.getValue(), (int) aDayS.getValue());
                xml.addRecord(name, date);
                frame.dispose();
                recordTable.update();
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
