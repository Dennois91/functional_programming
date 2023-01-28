package Examination.Controller;

import Examination.Model.Kund;
import Examination.Model.Repository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LogginTable extends JFrame implements ActionListener {
    private final Repository r = new Repository();
    private JPanel loginPanel;
    private JButton loginBut;
    private JTextField passwordField;
    private JTextField usernameTextField;
    final private List<Kund> kunder = r.getAllaKunder();


    public LogginTable() {
        setContentPane(loginPanel);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400, 90);
        setLocationRelativeTo(null);


        loginBut.addActionListener(l -> {
            boolean matchFound = false;
            for (Kund kund : kunder) {
                if (kund.namn.equals(usernameTextField.getText()) && kund.password.equals(passwordField.getText())) {
                    matchFound = true;
                    break;
                }
            }
            if (matchFound) {
                dispose();
                StoreTable storeTable = new StoreTable(usernameTextField.getText());
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
