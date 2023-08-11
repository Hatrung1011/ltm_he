package Client;

import RMIServer.ServerInterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class UIListType extends JFrame {
    public static final String[] COLUMNS = {"ID", "Tên", "Mô tả"};
    private ServerInterface rmi;
    private DefaultTableModel model;
    private Table table;
    private JTextField key;

    public UIListType(ServerInterface rmi) throws RemoteException, NotBoundException {
        super();
        this.rmi = rmi;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocation(200, 200);
        this.setTitle("Danh sách loại đồ dùng học tập");

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel searchBar = new JPanel(new FlowLayout());
        this.key = new JTextField();
        key.setPreferredSize(new Dimension(150, 30));

        searchBar.add(key);
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    search();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });
        searchBar.add(btnSearch);

        panel.add(searchBar, BorderLayout.NORTH);

        this.table = new Table(COLUMNS);
        this.table.setRowsType(rmi.getAllType());
        this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    dispose();
                    int selectedRow = table.getSelectedRow();
                    ArrayList<String> data = table.getValuesAtRow(selectedRow);
                    new UpdateType(COLUMNS, data, rmi);
                }
            }

        });
        panel.add(this.table, BorderLayout.CENTER);

        JButton addType = new JButton("Thêm loại đồ dùng học tập");
        addType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStationeryType add = new AddStationeryType(COLUMNS, rmi);
            }
        });

        JButton callListStationery = new JButton("Xem danh sách đồ dùng");
        callListStationery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new UILstStationery(0, "");
                    dispose();
                } catch (Exception ex) {
                    System.out.println("lỗi: " + ex);
                }
            }
        });
        JPanel actPanel = new JPanel(new GridLayout(1, 2, 40, 40));
        actPanel.setBorder(new EmptyBorder(20, 80, 10, 80));

        actPanel.add(addType);
        actPanel.add(callListStationery);
        panel.add(actPanel, BorderLayout.SOUTH);

        this.add(panel);
        this.setVisible(true);
    }

    public void search() throws RemoteException {
        String key = this.key.getText();
        this.table.setRowsType(rmi.searchType(key));
    }
}
