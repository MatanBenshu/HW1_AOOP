package ui;

import country.Settlement;
import country.SettlementData;
import jdk.jfr.Event;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//
public class StaticTable extends JTable {


    private static class SettelementModel extends AbstractTableModel {
        private SettlementData data;
        private final String[] columnNames = {"index", "Name","Settlement Type", "Ramzor","present of Sick","Vaccinate given","population size"};

        public SettelementModel(SettlementData data) {
            this.data = data;
        }


        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Settlement settlement = data.at(rowIndex);
            switch (columnIndex) {
                case 0:
                    return rowIndex+1;
                case 1:
                    return settlement.getName();
                case 2:
                    return settlement.SettlementType();
                case 3:
                    return settlement.calculateRamzorGrade().getColorName();
                case 4:
                    return settlement.contagiousPercent();
                case 5:
                    return settlement.getVaccineNum();
                case 6:
                    return settlement.PopulationSize();
                case 7:
                    return settlement.getResidentsNum();
            }
            return null;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex > 0;
        }

        @Override
        public void setValueAt(Object aValue, int row, int col) {
            Settlement settlement = data.at(row);
            switch (col) {
                case 1: settlement.setName((String) aValue); break;

            }
            fireTableCellUpdated(row, col);
        }
    }
    private int row_index;
    private StatisticsDialog dialog;
    private TableRowSorter<SettelementModel> sorter;
    private JTextField tbFilterText;
    private SettelementModel model;
    private SettlementData settlementData;

    public StaticTable(SettlementData settlementData, StatisticsDialog dialog,JTextField tbFText) {
        this.dialog=dialog;
        this.settlementData=settlementData;
        this.model=new SettelementModel(settlementData);
        this.setModel(model);
        this.tbFilterText=tbFText;

//        JTable table = new JTable(model);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setFillsViewportHeight(true);
        this.setRowSorter(sorter = new TableRowSorter<SettelementModel>(model));
        dialog.add(new JScrollPane(this));
        tbFilterText.setToolTipText("Filter Name Column");
        tbFilterText.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                newFilter();
            }

            public void removeUpdate(DocumentEvent e) {
                newFilter();
            }

            public void changedUpdate(DocumentEvent e) {
                newFilter();
            }
        });

        dialog.pack();
        this.setRowSelectionAllowed(true);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setColumnSelectionAllowed(false);
        StaticTable table=this;
        this.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {

               row_index= table.getSelectedRow();
            }
        });
        dialog.setVisible(true);


    }

    private void newFilter() {
        try {
            sorter.setRowFilter(RowFilter.regexFilter(tbFilterText.getText(), 1));
        } catch (java.util.regex.PatternSyntaxException e) {
            // If current expression doesn't parse, don't update.
        }
    }
    public Settlement getSettlementInRow(){

        return  settlementData.at(row_index);
    }

    public void Update(StaticTable ref){ref= new StaticTable(settlementData,dialog,tbFilterText);}
}