package Examination.Controller;

import Examination.Model.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.function.Function;
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

    public StoreTable(Kund kund) throws HeadlessException {
        userNameLabel.setText("Logged in as: " + kund.namn);
        setContentPane(storePanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        createOrderB.addActionListener(l -> {
            try {
                String articleString = list1.getSelectedValue();
                if (articleString != null) {
                    final List<String> sko = Arrays.stream(articleString.split(" "))
                            .map(s -> {
                                int i = s.indexOf(":");
                                if (i != -1) {
                                    return s.substring(i + 1);
                                } else {
                                    return s;
                                }
                            })
                            .filter(s -> !s.isEmpty()).collect(Collectors.toList());
                    final Sko skoObject = findSkoId(sko);
                    r.callAddToCart(kund.id, skoObject.id, kund.namn);
                    showInventoryList();
                }
                //To doo use SP addToCart to create order
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        ;
        dropDownMenu.addActionListener(l -> showReports(dropDownMenu));
    }

    public void showReports(JComboBox dropDownMenu) {
        switch (dropDownMenu.getSelectedIndex()) {
            case 0 -> showInventoryList();
            case 1 -> showTopList();
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

    public void showTopList() {
        final List<Kundvagn> kundvagnList = r.getAllaKundvagn();
        Map<Integer, Integer> skoCountMap = new HashMap<>();
        for (Kundvagn kundvagn : kundvagnList) {
            Sko skoObject = kundvagn.sko;
            int skoId = skoObject.id;
            int antal = kundvagn.antal;
            if (skoCountMap.containsKey(skoId)) {
                skoCountMap.put(skoId, skoCountMap.get(skoId) + antal);
            } else {
                skoCountMap.put(skoId, antal);
            }
        }
        List<TopList> topList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : skoCountMap.entrySet()) {
            topList.add(new TopList(entry.getKey(), entry.getValue()));
        }
        topList.sort(Comparator.comparingInt(TopList::getCount).reversed());

        for (TopList topList1 : topList){
            System.out.println(topList1.getSkoId() + " " + topList1.getCount());
        }
    }




    public void searchQuery() throws SQLException {
        String searchQuery = searchField.getText();
        String[] searchTerms = searchQuery.split(" ");
        String sql = "SELECT * FROM products WHERE ";

    }

    public Sko findSkoId(List<String> sko) {
        final List<Sko> skoList = r.getAllaSko();
        return skoList.stream()
                .filter(s -> s.getModel().equals(sko.get(1))
                        && s.getFärg().equals(sko.get(2))
                        && s.getMärke().equals(sko.get(0))
                        && s.getStorlek().equals(sko.get(3)))
                .findFirst()
                .orElse(null);
    }
}