package Examination.Controller;

import Examination.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
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

        dropDownMenu.addActionListener(l -> showReports(dropDownMenu));
    }

    public void showReports(JComboBox dropDownMenu) {
        switch (dropDownMenu.getSelectedIndex()) {
            case 0 -> showInventoryList();
            case 1 -> System.out.println("report 2");
            case 2 -> System.out.println("report 3");
            case 3 -> System.out.println("report 4");
            case 4 -> showTopList();
            default -> JOptionPane.showMessageDialog(null, "Unexpected error from reports",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showTopList() {
        List<Kundvagn> kundvagnList = r.getAllaKundvagn();

        Map<Integer, Integer> skoCountMap = kundvagnList.stream()
                .collect(Collectors.toMap(kv -> kv.sko.id, Kundvagn::getAntal, Integer::sum));

        List<TopList> topList = skoCountMap.entrySet().stream()
                .map(entry -> new TopList(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingInt(TopList::getCount).reversed())
                .collect(Collectors.toList());

        DefaultListModel<String> topListString = new DefaultListModel<>();
        topList.forEach(tl -> topListString.addElement(findSkoById(tl.getSkoId()) + " antal sålda: " + tl.getCount()));
        list1.setModel(topListString);
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

    public String findSkoById(int skoId) {
        final List<Sko> skoList = r.getAllaSko();
        List<String> topList = new ArrayList<>();
        return skoList.stream().filter(sko -> sko.getId() == skoId).map(Sko::getSko).findFirst().orElse("");
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