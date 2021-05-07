package ui;

import country.Settlement;
import country.SettlementData;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

//
public class StaticTable extends JTable {
//    private static class Student {
//        private String id, name;
//        private int age;
//        private boolean drivingLicense;
//
//        public Student(String id, String name, int age, boolean drivingLicense) {
//            this.id = id;
//            this.name = name;
//            this.age = age;
//            this.drivingLicense = drivingLicense;
//        }
//
//        public String getId() {
//            return id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public int getAge() {
//            return age;
//        }
//
//        public boolean isDrivingLicense() {
//            return drivingLicense;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public void setAge(int age) {
//            this.age = age;
//        }
//
//        public void setDrivingLicense(boolean drivingLicense) {
//            this.drivingLicense = drivingLicense;
//        }
//    }
//
//    private static class Course {
//        private Student[] students;
//
//        public Course(Student[] students) {
//            this.students = students;
//        }
//
//        public int size() {
//            return students.length;
//        }
//
//        public Student at(int index) {
//            return students[index];
//        }
//    }

    private static class SettelementModel extends AbstractTableModel {
        private SettlementData data;
        private final String[] columnNames = {"index","res size", "Name", "precent of sick", "Ramzor"};

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
                    return rowIndex;
                case 1:
                    return settlement.getResidentsNum();
                case 2:
                    return settlement.getName();
                case 3:
                    return settlement.contagiousPercent();
                case 4:
                    return settlement.calculateRamzorGrade().getColorName();
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

    private JDialog dialog;
    private TableRowSorter<SettelementModel> sorter;
    private JTextField tbFilterText;
    private SettelementModel model;
    private SettlementData settlementData;

    public StaticTable(SettlementData settlementData, JDialog dialog,JTextField tbFText) {
        this.dialog=dialog;
        this.settlementData=settlementData;
        this.model=new SettelementModel(settlementData);
        this.setModel(model);
        this.tbFilterText=tbFText;

        JTable table = new JTable(model);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setFillsViewportHeight(true);
        this.setRowSorter(sorter = new TableRowSorter<SettelementModel>(model));
        dialog.add(new JScrollPane(table));
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

        dialog.setVisible(true);


    }

    private void newFilter() {
        try {
            sorter.setRowFilter(RowFilter.regexFilter(tbFilterText.getText(), 1));
        } catch (java.util.regex.PatternSyntaxException e) {
            // If current expression doesn't parse, don't update.
        }
    }
    public Settlement getSettlementInRow(int index){

       return  settlementData.at(index);
    }
    private void  setRowSelect(){

        ListSelectionModel model_u= (ListSelectionModel) this.getModel();

        model_u.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
             int index =  model_u.getMinSelectionIndex();
            }
        });

    }
}


