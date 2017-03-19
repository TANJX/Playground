package cc.isotopestudio.datecalculator;
/*
 * Created by Mars Tan on 12/14/2016.
 * Copyright ISOTOPE Studio
 */

import cc.isotopestudio.datecalculator.record.Record;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.util.List;

class RecordTable extends JTable {

    private final Object[] columnNames = {"id", "name", "month", "day", "year", "diff"};
    private Object[][] rowData;
    private final RecordTableModel tableModel;

    RecordTable() {
        initRowsData();
        tableModel = new RecordTableModel();
        this.setModel(tableModel);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setRowSelectionAllowed(true);
        TableColumn column;
        for (int i = 0; i < columnNames.length; i++) {
            column = getColumnModel().getColumn(i);
            switch (i) {
                case (0):
                    column.setPreferredWidth(20);
                    break;
                case (1):
                    column.setPreferredWidth(150);
                    break;
                case (2):
                    column.setPreferredWidth(20);
                    break;
                case (3):
                    column.setPreferredWidth(30);
                    break;
                case (4):
                    column.setPreferredWidth(40);
                    break;
                case (5):
                    column.setPreferredWidth(100);
                    break;
            }
        }
    }

    private void initRowsData() {
        List<Record> recordList = Record.getAll();
        rowData = new Object[recordList.size()][6];
        int i = 0;
        for (Record record : recordList) {
            long diff = record.getDiff();
            rowData[i][0] = i + "";
            rowData[i][1] = record.getName();
            rowData[i][2] = record.getMonth() + "";
            rowData[i][3] = record.getDay() + "";
            rowData[i][4] = record.getYear() + "";
            rowData[i][5] = diff < 0 ? -diff + " days past" : "In " + diff + " days";
            if (diff == 0) {
                rowData[i][5] = "Today";
            }
            i++;
        }
    }

    void update() {
        initRowsData();
        tableModel.fireTableRowsInserted(0, rowData.length - 1);
    }

    private class RecordTableModel extends AbstractTableModel {
        @Override
        public String getColumnName(int col) {
            return columnNames[col].toString();
        }

        @Override
        public int getRowCount() {
            return rowData.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int row, int col) {
            return rowData[row][col];
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            rowData[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

}
