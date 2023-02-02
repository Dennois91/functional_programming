package Examination.Controller;

import Examination.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.Collator;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class StoreController extends JFrame {
    private JPanel storePanel;
    private JList<String> list1;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox dropDownMenu;
    private JButton createOrderB;
    private JButton addToCartB;
    private JButton removeFromCartB;
    private JLabel userNameLabel;
    private final Repository r;
    private final Kund kund;


    public StoreController(Kund kund, Repository r) throws HeadlessException {
        this.r = r;
        this.kund = kund;
        userNameLabel.setText("Logged in as: " + kund.namn);
        setContentPane(storePanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        createOrderB.addActionListener(this::createOrder);
        dropDownMenu.addActionListener(this::showSelectedReport);
    }

    private void showSelectedReport(ActionEvent event) {
        Map<Integer, Runnable> menu = new HashMap<>();
        menu.put(0, this::showInventoryList);
        menu.put(1, this::showOrdersPerKund);
        menu.put(2, this::showKundVärde);
        menu.put(3, this::showSpendPerStad);
        menu.put(4, this::showTopList);

        int selectedIndex = dropDownMenu.getSelectedIndex();
        if (menu.containsKey(selectedIndex)) {
            menu.get(selectedIndex).run();
        } else {
            JOptionPane.showMessageDialog(null, "Unexpected error from reports",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createOrder(ActionEvent event) {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void showSpendPerStad() {
        list1.setModel(r.getListModelOf(Output.SPENT_PER_STAD));
    }

    public void showKundVärde() {
        list1.setModel(r.getListModelOf(Output.SPENT_PER_KUND));
    }

    public void showOrdersPerKund() {
        list1.setModel(r.getListModelOf(Output.ORDERS_PER_KUND));
    }

    public void showTopList() {
        final List<Kundvagn> kundvagnList = r.getAllaKundvagn();

        Map<Integer, Integer> skoCountMap = kundvagnList.stream()
                .collect(Collectors.toMap(kv -> kv.sko.id, Kundvagn::getAntal, Integer::sum));

        final List<TopList> topList = skoCountMap.entrySet().stream()
                .map(entry -> new TopList(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingInt(TopList::getCount).reversed())
                .collect(Collectors.toList());

        final DefaultListModel<String> topListString = new DefaultListModel<>();
        topList.forEach(tl -> topListString.addElement(findSkoById(tl.getSkoId()) + " antal sålda: " + tl.getCount()));
        list1.setModel(topListString);
    }

    public void showInventoryList() {
        final List<Sko> skoList = r.getAllaSko();
        final Collator collator = Collator.getInstance(new Locale("sv", "SV"));
        final DefaultListModel<String> listModel = new DefaultListModel<>();
        skoList.stream().map(Sko::getInventory).sorted(collator).forEach(listModel::addElement);
        list1.setModel(listModel);
    }

    public void searchQuery() throws SQLException {
        String searchQuery = searchField.getText();
    }

    public String findSkoById(int skoId) {
        final List<Sko> skoList = r.getAllaSko();
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
/*
    public void populateColorToProductTable() {
        for (Product p : productMap.values()) {
            for (Integer key : colorMap.keySet()) {
                if (p.getColorID() == key) {
                    p.setColor(colorMap.get(key));
                }
            }
        }
    }

    public void populateColorToProductTable2() {
        productMap.values().stream().forEach(p -> colorMap.keySet().stream().
                forEach(key -> key == p.getColorID() ? p.setColor(colorMap.get(key)) :));
    }

    public void populateColorToProductTable2() {
        productMap.values().stream().forEach(p -> colorMap.keySet().stream().
                forEach(key -> key == p.getColorID() ? p.setColor(colorMap.get(key)) : null));
    }
}
public void populateColorToProductTable2() {
        productMap.values().stream().forEach(p -> colorMap.keySet().stream().
        filter(key -> key == p.getColorID()).findFirst().ifPresent(key -> p.setColor(colorMap.get(key))));
        }

 */