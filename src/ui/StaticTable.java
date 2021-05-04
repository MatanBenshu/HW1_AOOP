package ui;

import country.Map;
import country.Settlement;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class StaticTable  {
    private enum colname {
        Name(0),Type(1),Ramzor_Color(2),SickPercent(3),VaccinesGiven(4),NumberofDeceased(5),NumberofPeople(6);

        private final int num;
         private colname( final int num){
            this.num=num;
        }
        public final int getnum(){
            return  this.num;
        }
    }
    private static class MapModel extends AbstractTableModel
    {

        private Map data;
        private final String[] columnNames = {"Name","Type","Ramzor Color","Sick Percent","Vaccines Given","Number of Deceased","Number of People"};;

        public MapModel(Map data) {
            this.data = data;
        }

        @Override
        public int getRowCount() {
            return data.getSettlements().length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Settlement settlement = data.getSettlements()[rowIndex];

            switch (columnIndex) {
                case 0: return settlement.getName();
                case 1: return settlement.SettlementType();
                case 2: return settlement.getVaccineNum();
                case 3: return settlement.contagiousPercent();
                case 4:return settlement.getResidentsNum();
                case 5:return null;
                case 6:return settlement.calculateRamzorGrade().getColored();
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

//        @Override
//        public void setValueAt(Object aValue, int row, int col) {
//            Settlement student = data.at(row);
//            switch (col) {
//                case 1: student.setName((String) aValue); break;
//                case 2: student.setAge((Integer) aValue); break;
//                case 3: student.setDrivingLicense((Boolean) aValue); break;
//            }
//            fireTableCellUpdated(row, col);
//        }
    }

    private TableRowSorter<MapModel> sorter;
    private JTextField tbFilterText;

    public StaticTable(Map map,JDialog dialog) {


        MapModel model = new MapModel(map);
        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setRowSorter(sorter = new TableRowSorter<MapModel>(model));
        dialog.add(new JScrollPane(table),BorderLayout.CENTER);

       dialog.add(tbFilterText = new JTextField(),BorderLayout.CENTER);
        tbFilterText.setToolTipText("Filter Name Column");
        tbFilterText.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { newFilter(); }
            public void removeUpdate(DocumentEvent e) { newFilter(); }
            public void changedUpdate(DocumentEvent e) { newFilter(); }
        });

        dialog.pack();
        table.setVisible(true);

    }

    private void newFilter() {
        try {
            sorter.setRowFilter(RowFilter.regexFilter(tbFilterText.getText(), 1));
        } catch (java.util.regex.PatternSyntaxException e) {
            // If current expression doesn't parse, don't update.
        }
    }

}
