package Client;

import Model.Stationery;
import RMIServer.ServerInterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class AddStationery extends JFrame {
    private ArrayList<JTextField> inpText;
    private String[] properties;
    private ServerInterface rmi;

    public AddStationery(String[] properties, ServerInterface rmi) {

        this.setTitle("Thêm đồ dùng học tập");
        this.properties = properties;
        this.rmi = rmi;
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        int length = properties.length;
        JPanel inpPanel = new JPanel(new GridLayout(length, 2, 30, 30));
        inpPanel.setBorder(new EmptyBorder(0, 0, 20, 20));
        this.inpText = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            inpPanel.add(new JLabel(properties[i]));
            this.inpText.add(new JTextField());
            inpPanel.add(this.inpText.get(i));
        }
        inpPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(inpPanel);

        Panel actPanel = new Panel(new GridLayout(1, 3, 10, 10));
        JButton save = new JButton("Lưu");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    save();
                } catch (Exception ex) {
                    System.err.println("Add Element:" + ex);
                    showMessage("Thông tin không hợp lệ");
                }
            }
        });
        JButton cancel = new JButton("Hủy");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cancel();
                } catch (Exception ex) {
                    System.err.println("Add Element:" + ex);

                }
            }
        });
        actPanel.add(save);
        actPanel.add(cancel);

        panel.add(actPanel, BorderLayout.SOUTH);

        int n = Window.getWindows().length;
        if (n >= 2) {
            Window.getWindows()[n - 2].dispose();
        }

        this.setSize(350, 370);
        this.setLocation(200, 200);
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public void save() throws RemoteException, NotBoundException {
        //this.dispose();
        try {
            this.rmi.addStationery(new Stationery(
                    Integer.parseInt(inpText.get(0).getText()),
                    inpText.get(1).getText(),
                    inpText.get(2).getText(),
                    Integer.parseInt(inpText.get(3).getText()),
                    inpText.get(4).getText(),
                    inpText.get(5).getText()
            ));
            this.dispose();
            new UILstStationery(0, "");
        } catch (Exception ex) {
            showMessage("Thông tin không hợp lệ");
        }

    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void cancel() throws RemoteException, NotBoundException {
        new UILstStationery(0, "");
        this.dispose();
    }
}
