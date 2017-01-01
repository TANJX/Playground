package cc.isotopestudio.datecalculator;
/*
 * Created by Mars Tan on 12/14/2016.
 * Copyright ISOTOPE Studio
 */

import cc.isotopestudio.datecalculator.record.Record;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class RecordTable extends JTable {

    private DefaultTableModel defaultTableModel;
    private final Object[] columnNames = {"id", "name", "month", "day", "year", "diff"};

    public RecordTable() {
        defaultTableModel = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                super.setValueAt(aValue, row, column);
                fireTableCellUpdated(row, column);
            }
        };
        this.setModel(defaultTableModel);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setRowSelectionAllowed(true);
        initRowsData();
    }

    void initRowsData() {
//        this.removeRowsData();

        List<Record> recordList = Record.getAll();
        String[] row = new String[6];
        int i = 0;
        for (Record record : recordList) {
            long diff = record.getDiff();
            row[0] = i + "";
            row[1] = record.getName();
            row[2] = record.getMonth() + "";
            row[3] = record.getDay() + "";
            row[4] = record.getYear() + "";
            row[5] = diff < 0 ? diff + " days to go" : diff + " past";
            if (diff == 0) {
                row[5] = "TODAY";
            }
            i++;
            defaultTableModel.addRow(row.clone());
        }
//        this.revalidate();

        this.updateUI();
    }

    private void removeRowsData() {
        int count = defaultTableModel.getRowCount();
        for (count -= 1; count > -1; count--) {
            defaultTableModel.removeRow(count);
        }
    }
}
