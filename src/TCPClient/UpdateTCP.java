package TCPClient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateTCP extends JDialog {
    private ArrayList<JTextField> inpText;
    private String[] properties;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public UpdateTCP(String[] properties, ArrayList<String> data, ObjectOutputStream out, ObjectInputStream in) {
        this.setTitle("Chỉnh sửa đồ dùng học tập");
        this.out = out;
        this.in = in;
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
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        JButton cancel = new JButton("Hủy");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cancel();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        JButton delete = new JButton("Xóa");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    delete();
                } catch (IOException ex) {
                    ex.printStackTrace();
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

    public void save() throws IOException {
        out.writeObject("UPDATE"); // Send request to the server
        ArrayList<String> newData = new ArrayList<>();
        for (JTextField textField : inpText) {
            newData.add(textField.getText());
        }
        out.writeObject(newData); // Send updated data to the server

        // Receive response from the server if needed
        // ...

        // Update the UI or take necessary actions
        // ...

        this.dispose();
    }

    public void delete() throws IOException {
        out.writeObject("DELETE"); // Send request to the server
        out.writeObject(Integer.parseInt(this.inpText.get(0).getText())); // Send ID to delete

        // Receive response from the server if needed
        // ...

        // Update the UI or take necessary actions
        // ...

        this.dispose();
    }

    public void cancel() throws IOException {
        // Update the UI or take necessary actions
        // ...

        this.dispose();
    }
}
