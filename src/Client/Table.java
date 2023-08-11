package Client;

import Model.Stationery;
import Model.StationeryType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Table extends JScrollPane {
    public DefaultTableModel model;
    private String[] column;
    private JTable table;

    public Table(String[] column) throws RemoteException {
        super();
        this.column = column;
        this.model = new DefaultTableModel(null, column) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.table = new JTable(model);


        table.setRowHeight(25);
        this.setViewportView(table);
    }

    public DefaultTableModel getModel() {
        return this.model;
    }

    public JTable getTable() {
        return this.table;
    }

    public void setRowsStationery(ArrayList<Stationery> values) {
        this.model.setRowCount(0);
        for (Stationery value : values) {
            this.model.addRow(value.toArray());
        }
    }

    public void setRowsType(ArrayList<StationeryType> types) {
        this.model.setColumnCount(3);
        for (StationeryType type : types) {
            this.model.addRow(type.toArray());
        }
    }

    public int getSelectedRow() {
        return this.table.getSelectedRow();
    }

    public ArrayList<String> getValuesAtRow(int row) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < this.column.length; i++) {
            result.add((String) this.table.getValueAt(row, i));
        }
        return result;
    }

    public ListSelectionModel getSelectionModel() {
        return this.table.getSelectionModel();
    }
}
