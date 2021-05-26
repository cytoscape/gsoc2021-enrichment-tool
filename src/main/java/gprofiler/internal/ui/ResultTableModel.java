package gprofiler.internal.ui;

import javax.swing.table.AbstractTableModel;

/**
 * @description Data structure that represents the ResultTable
 */
public class ResultTableModel extends AbstractTableModel {
    private final Object[] columnNames;
    private final Object[][] data;
    private int SELECT = 0;

    public ResultTableModel(Object[] columnNames, Object[][] data) {
        super();
        this.columnNames = columnNames;
        this.data=data;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int column){
        return (String) columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }


}
