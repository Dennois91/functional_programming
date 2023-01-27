package Examination.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogginTable extends JFrame implements ActionListener {
    private JPanel loginPanel;
    private JButton loginBut;
    private JPasswordField passwordField;
    private JTextField usernameTextField;

    private boolean accesGranted = true;

    public LogginTable() {
        setContentPane(loginPanel);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400, 90);
        setLocationRelativeTo(null);


        loginBut.addActionListener(l -> {
            if (accesGranted) {
                dispose();
                StoreTable storeTable = new StoreTable();
                storeTable.showInventoryList();
                revalidate();
                repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Access Denied", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
