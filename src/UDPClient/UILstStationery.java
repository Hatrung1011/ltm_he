package UDPClient;

import Client.Table;
import RMIServer.ServerInterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class UILstStationery extends JFrame {
    public static final String[] COLUMN = {"ID", "Tên đồ dùng", "Loại đồ dùng", "Lứa tuổi", "Hình ảnh", "Giá"};
    public static final String[] TYPE = {"Tất cả", "Tên", "Loại", "Giá"};

    private ServerInterface rmi;
    private DefaultTableModel model;
    private Table table;
    private int typeSelected;
    private String keySelected;
    private JComboBox<String> searchtype;
    private JTextField key;

    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int serverPort;

    public void connectUDP() {
        try {
            socket = new DatagramSocket();
            serverAddress = InetAddress.getByName("localhost"); // Replace with your server's address
            serverPort = 12345; // Replace with your server's port
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public UILstStationery(int type, String keySelected) throws RemoteException, NotBoundException {
        connectUDP();
        this.typeSelected = typeSelected;
        this.keySelected = keySelected;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocation(200, 200);
        this.setTitle("Quản lý đồ dùng học tập");

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel searchBar = new JPanel(new FlowLayout());
        this.searchtype = new JComboBox<String>(TYPE);
        searchtype.setSelectedIndex(this.typeSelected);
        this.key = new JTextField(keySelected);
        key.setPreferredSize(new Dimension(150, 30));


        searchBar.add(searchtype);
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

        this.table = new Table(COLUMN);
        this.table.setRowsStationery(rmi.getAllStationery());
        this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    dispose();
                    int selectedRow = table.getSelectedRow();
                    ArrayList<String> data = table.getValuesAtRow(selectedRow);
                    try {
                        sendUpdateRequest(data);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        panel.add(this.table, BorderLayout.CENTER);

        JButton addStationery = new JButton("Thêm đồ dùng học tập");
        addStationery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Send request to server
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        JButton callListType = new JButton("Xem danh sách loại đồ dùng");
        callListType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new UIListType(rmi);
                    dispose();
                } catch (Exception ex) {
                    System.out.println("lỗi gọi UIListCitys: " + ex);
                }
            }
        });
        JPanel actPanel = new JPanel(new GridLayout(1, 2, 40, 40));
        actPanel.setBorder(new EmptyBorder(20, 70, 10, 70));
        actPanel.add(addStationery);
        actPanel.add(callListType);
        panel.add(actPanel, BorderLayout.SOUTH);

        int n = Window.getWindows().length;
        if (n >= 2) {
            Window.getWindows()[n - 2].dispose();
        }

        this.add(panel);
        this.setVisible(true);
    }

    public void setTypeSelected(int typeSelected) {
        this.typeSelected = typeSelected;
    }

    public int getTypeSelected() {
        return typeSelected;
    }

    public void search() throws RemoteException {
        int type = this.searchtype.getSelectedIndex();
        String key = this.key.getText();
        System.out.println(key);
        if (type == 0) {
            this.table.setRowsStationery(rmi.getAllStationery());
        } else if (type == 1) {
            this.table.setRowsStationery(rmi.getStationeryByName(key));
        } else if (type == 2) {
            this.table.setRowsStationery(rmi.getStationeryByType(key));
        } else {
            this.table.setRowsStationery(rmi.getStationeryByPrice(key));
        }

    }


    public static void main(String[] args) throws RemoteException, NotBoundException {
        new UILstStationery(0, "");
    }

    private void sendUpdateRequest(ArrayList<String> data) throws IOException {
        String request = "UPDATE|" + String.join("|", data);
        byte[] requestData = request.getBytes();
        DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, serverAddress, serverPort);
        socket.send(requestPacket);
        new Update(COLUMN, data, socket, serverAddress, serverPort);
    }

}
