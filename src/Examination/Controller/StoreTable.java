package Examination.Controller;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StoreTable extends JFrame {
    private JPanel storePanel;
    private JList list1;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox dropDownMenu;
    private JButton createOrderB;
    private JButton addToCartB;
    private JButton removeFromCartB;

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

    }

    public JPanel getStorePanel() {
        return storePanel;
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