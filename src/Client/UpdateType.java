package Client;

import Model.StationeryType;
import RMIServer.ServerInterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateType extends JFrame {
    private ArrayList<JTextField> inpText;
    private String[] properties;
    private ServerInterface rmi;

    public UpdateType(String[] properties, ArrayList<String> data, ServerInterface rmi) {
        this.setTitle("Chỉnh sửa loại đồ dùng học tập");
        this.rmi = rmi;
        this.properties = properties;
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        int length = properties.length;
        JPanel inpPanel = new JPanel(new GridLayout(length, 2, 30, 30));
        inpPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.inpText = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            inpPanel.add(new JLabel(properties[i]));
            this.inpText.add(new JTextField(data.get(i)));
            if (i == 0) {
                this.inpText.get(i).setEditable(false);
            }
            inpPanel.add(this.inpText.get(i));
            this.inpText.get(i).setPreferredSize(new Dimension(170, 25));

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
                } catch (RemoteException ex) {
                    System.out.println(".actionPerformed()");
                    ;
                } catch (NotBoundException ex) {
                    Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        JButton cancel = new JButton("Hủy");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cancel();
                } catch (RemoteException ex) {
                    Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NotBoundException ex) {
                    Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        JButton delete = new JButton("Xóa");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    delete();
                } catch (RemoteException ex) {
                    Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NotBoundException ex) {
                    Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        actPanel.add(save);
        actPanel.add(cancel);
        actPanel.add(delete);
        panel.add(actPanel, BorderLayout.SOUTH);

        this.setSize(350, (this.properties.length + 1) * 65);
        this.setLocation(300, 300);
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public void save() throws RemoteException, NotBoundException {

        this.rmi.updateType(new StationeryType(
                Integer.parseInt(inpText.get(0).getText()),
                inpText.get(1).getText(),
                inpText.get(2).getText()
        ));
        new UIListType(rmi);
        this.dispose();
    }

    public void delete() throws RemoteException, NotBoundException {

        this.rmi.deleteStationery(Integer.parseInt(this.inpText.get(0).getText()));
        new UILstStationery(0, "");

        this.dispose();
    }

    public void cancel() throws RemoteException, NotBoundException {

        new UILstStationery(0, "");

        this.dispose();
    }
}
