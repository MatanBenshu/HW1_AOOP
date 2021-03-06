
//Matan Ben-Shushsan 205639800
//Aviya David 209203991
package ui;
import javax.swing.table.AbstractTableModel;

public class MutationsTable extends AbstractTableModel  {

    private final String[] col_names ;
    private Object[][] data;

    public MutationsTable(Object[][] data, String[] col_names) {
        this.data = data;
        this.col_names=col_names;}

    @Override
    public int getRowCount() { return data.length; }

    @Override
    public int getColumnCount() { return col_names.length; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    public String getColumnName(int column) { return col_names[column]; }

    public Class getColumnClass(int column) { return getValueAt(0, column).getClass(); }
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    @Override
    public void setValueAt(Object aValue, int row, int col) {
        if (aValue instanceof Boolean)
            data[row][col]= aValue;
        fireTableCellUpdated(row, col);
    }


}
