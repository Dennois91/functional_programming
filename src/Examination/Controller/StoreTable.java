package Examination.Controller;

import Examination.Model.Repository;
import Examination.Model.Sko;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class StoreTable extends JFrame {
    private JPanel storePanel;
    private JList<String> list1;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox dropDownMenu;
    private JButton createOrderB;
    private JButton addToCartB;
    private JButton removeFromCartB;
    private JLabel userNameLabel;
    private final Repository r = new Repository();
    private int dropDownMenuIndex;

    public StoreTable(String user) throws HeadlessException {
        userNameLabel.setText("Logged in as: " + user);
        setContentPane(storePanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        searchButton.addActionListener(l -> {
            try {
                searchQuery();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        dropDownMenu.addActionListener(l -> showReports(dropDownMenu));
    }

    public void showReports(JComboBox dropDownMenu) {
        switch (dropDownMenu.getSelectedIndex()) {
            case 0 -> System.out.println("report 1");
            case 1 -> System.out.println("report 2");
            case 2 -> System.out.println("report 3");
            case 3 -> System.out.println("report 4");
            case 4 -> System.out.println("report 5");
            default -> JOptionPane.showMessageDialog(null, "Unexpected error from reports",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showInventoryList() {
        final List<Sko> skoList = r.getAllaSko();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        skoList.stream().map(Sko::getInventory).forEach(listModel::addElement);
        list1.setModel(listModel);

    }


    public void searchQuery() throws SQLException {
        String searchQuery = searchField.getText();
        String[] searchTerms = searchQuery.split(" ");
        String sql = "SELECT * FROM products WHERE ";

    }
}