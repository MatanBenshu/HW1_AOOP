
//Matan Ben-Shushsan 205639800
//Aviya David 209203991
package ui;

import country.Settlement;
import country.SettlementData;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

//
public class StaticTable extends JTable {


    private static class SettelementModel extends AbstractTableModel {
        private SettlementData data;
        private final String[] columnNames = {"Index", "Name","Settlement Type", "Ramzor","Present Of Sick","Vaccinate Given","Population Size","Max Residents ","Dead"};

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
                    return String.format("%.3f",settlement.contagiousPercent()*100) + "%";
                case 5:
                    return settlement.getVaccineNum();
                case 6:
                    return settlement.PopulationSize();
                case 7:
                    return settlement.getMaxResidents();
                case 8:
                    return settlement.getDeadNum();
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

        public String[] getColumnNames() {
            return columnNames;
        }

    }
    private static int row_index;
    private StatisticsDialog dialog;
    private TableRowSorter<SettelementModel> sorter;
    private JTextField tbFilterText;
    private SettelementModel model;
    private SettlementData settlementData;

    public StaticTable(SettlementData settlementData, StatisticsDialog dialog, JTextField tbFText) {
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

//        dialog.pack();
        this.setRowSelectionAllowed(true);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setColumnSelectionAllowed(false);
        StaticTable table=this;
        this.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {

                row_index= table.getSelectedRow();
                System.out.println(row_index);
            }
        });



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

    public void Update(){model.fireTableDataChanged();this.repaint();
    }
    public String[]  getColNames(){return model.getColumnNames();}
    public void SortTableby(int col_index){
        this.sorter.toggleSortOrder(col_index);
        this.Update();

    }


}