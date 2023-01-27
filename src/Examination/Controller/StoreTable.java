package Examination.Controller;

import Examination.Model.Repository;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class StoreTable extends JFrame {
    private JPanel storePanel;
    private JList<String> list1;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox dropDownMenu;
    private JButton createOrderB;
    private JButton addToCartB;
    private JButton removeFromCartB;
    private final Repository r = new Repository();
    private int dropDownMenuIndex;

    public StoreTable() throws HeadlessException {

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
            case 0 -> showInventoryList();
            case 1 -> System.out.println("report 1");
            case 2 -> System.out.println("report 2");
            case 3 -> System.out.println("report 3");
            case 4 -> System.out.println("report 4");
            default -> JOptionPane.showMessageDialog(null, "Unexpected error from reports",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showInventoryList() {
        ListModel<String> listModel = r.displayInventory();
        list1.setModel(listModel);
    }


    public void searchQuery() throws SQLException {
        String searchQuery = searchField.getText();
        System.out.println(searchQuery);
        System.out.println("meee");
        String[] searchTerms = searchQuery.split(" ");
        String sql = "SELECT * FROM products WHERE ";

   /*     List conditions = new ArrayList<>();
        for (String term : searchTerms) {
            conditions.add("model LIKE ?");
            conditions.add("brand LIKE ?");
            conditions.add("name LIKE ?");
            conditions.add("color LIKE ?");
        }

        sql += String.join(" OR ", conditions);
        PreparedStatement statement = connection.prepareStatement(sql);

        int i = 1;
        for (String term : searchTerms) {

                statement.setString(i++, "%" + term + "%");
                statement.setString(i++, "%" + term + "%");
                statement.setString(i++, "%" + term + "%");
                statement.setString(i++, "%" + term + "%");
                ResultSet result = statement.executeQuery();
        }

    */
    }
}