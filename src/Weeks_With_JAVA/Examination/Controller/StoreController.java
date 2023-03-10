package Weeks_With_JAVA.Examination.Controller;

import Weeks_With_JAVA.Examination.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.Collator;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class StoreController extends JFrame {
    private JPanel storePanel;
    private JList<String> list1;
    private JTextField inputField;
    private JButton searchButton;
    private JComboBox dropDownMenu;
    private JButton createOrderB;
    private JButton addToOrder;
    private JButton removeFromCartB;
    private JLabel userNameLabel;
    private final Repository r;
    private final Kund kund;

    /*
    Store kontroller startas upp med referens till repository som vi startade i loginController samt kund
    Det finns 2 actionListeners. En för CreateOrder knappen och en för RapportMenyn
    */


    public StoreController(Kund kund, Repository r) throws HeadlessException {
        this.r = r;
        this.kund = kund;
        userNameLabel.setText("Logged in as: " + kund.namn);
        setContentPane(storePanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        dropDownMenu.addActionListener(this::showSelectedReport);
        createOrderB.addActionListener(this::createOrder);
        addToOrder.addActionListener(this::addToOrder);

    }

    /*
    showSelectedReport fungerar som en switch.
    Varje metod som ska kallas i dropDownMenyn mappas till ett index värde och interfacet Runnable
    När listener på dropDownMenu triggas tittar vi efter index värdet och matchar med nyckelvärdet i mappen och kör .run
    */

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
            JOptionPane.showMessageDialog(null, "Oväntat fel från val av rapport",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /*
    createOrder tar selected rad i Jlist och sparar till en string
    Splittar stringen till en lista av strings som ska definera en sko
    Stringen skickas till metoden findSkoId för att hitta rätt sko objekt
    Kund.ID - Sko.ID - Kund.namn Skickas till callAddToCart
    showInventoryList() hämtar och uppdaterar listan för att visa alla skor i lager.

    HOTFIX Bröt ut sko identifieringen till metod convertStringSkoToSkoObject()
    HOTFIX La till metod addToOrder för att kunna modifera en befintlig order
    */

    private Sko convertStringSkoToSkoObject(String articleString) {
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
        return findSkoId(sko);
    }

    private void createOrder(ActionEvent event) {
        try {
            String articleString = list1.getSelectedValue();
            if (articleString != null) {
                Sko skoObject = convertStringSkoToSkoObject(articleString);
                r.callAddToCart(kund.id, skoObject.id, kund.namn, Integer.MAX_VALUE, Output.NY_ORDER);
                showInventoryList();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Du kan inte lägga till objekt till order som inte är en sko! "
                    , "Ogiltigt val", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    private void addToOrder(ActionEvent event) {
        try {
            String articleString = list1.getSelectedValue();
            if (articleString != null) {

                Sko skoObject = convertStringSkoToSkoObject(articleString);
                try {
                    int orderNumber = Integer.parseInt(inputField.getText().trim());
                    if (orderNumber == 0) {
                        JOptionPane.showMessageDialog(null,
                                "Ordernummer får inte vara 0! "
                                , "Ogiltig input", JOptionPane.ERROR_MESSAGE);

                    } else {
                        r.callAddToCart(kund.id, skoObject.id, kund.namn, orderNumber, Output.UPPDATERA_ORDER);
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "Ogiltig input i fält. Använd endast nummer!  "
                            , "Ogiltig input", JOptionPane.ERROR_MESSAGE);
                }
                showInventoryList();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Du kan inte lägga till objekt till order som inte är en sko! "
                    , "Ogiltigt val", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    /* Högre ordningens Funktion:
       Tar in en DataMapper och ett Enum. Skriver ut en ListModel för display i JList
       DataMapper tar in en List och ett enum. Mappar list datat och returnerar en HashMap.

       För varje entry i hashMap adderar vi raden till listModel och return den fyllda ListModel
       för display i Jlist
    */

    public DefaultListModel<String> getListModelOf(DataMapper dataMapper, Output output) {
        final List<Beställning> beställningList = r.getAllaBeställning();
        Map<String, Integer> hashMap = dataMapper.mapData(beställningList, output);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            listModel.addElement(entry.getKey() + " Värde: " + entry.getValue());
        }
        return listModel;
    }

    /*
    3 stycken i princip identiska hämtningar av rapporter med 3 klasser som implementer interfacet DataMapper
    */

    private void showSpendPerStad() {
        list1.setModel(getListModelOf(new TotalBeställningarValuePerCityMapper(), Output.SPENT_PER_STAD));
    }

    public void showKundVärde() {
        list1.setModel(getListModelOf(new TotalBeställningValuePerKundMapper(), Output.SPENT_PER_KUND));
    }

    public void showOrdersPerKund() {
        list1.setModel(getListModelOf(new TotalBeställningarMadeByKundMapper(), Output.ORDERS_PER_KUND));
    }

    /*
    showTopList skapar en topplista av mest sålda skor
    Först hämtas alla kundvagnar som innehåller Skon samt antal per sko
    skoCountMap sätter sko till nyckel och adderar antalet till skon om nyckeln finns

    En TopList lista skapas som får värdena av skoCountMap i omvänd ordning för att visa högsta värdet högst upp
    Och en listModel skapas och för varje element i topList hämtar vi skoID och kör igenom findSkoByID och lägger till
    i listModel samt antal sålda skor per skoId
    */

    public void showTopList() {
        final List<Kundvagn> kundvagnList = r.getAllaKundvagn();

        Map<Integer, Integer> skoCountMap = kundvagnList.stream()
                .collect(Collectors.toMap(kv -> kv.sko.id, Kundvagn::getAntal, Integer::sum));

        final List<TopList> topList = skoCountMap.entrySet().stream()
                .map(entry -> new TopList(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingInt(TopList::getCount).reversed())
                .collect(Collectors.toList());

        final DefaultListModel<String> listModel = new DefaultListModel<>();
        topList.forEach(tl -> listModel.addElement(findSkoById(tl.getSkoId()) + " antal sålda: " + tl.getCount()));
        list1.setModel(listModel);
    }

    /*
    showInventoryList Hämtar alla skor, Skapar en collator för att kunna sortera efter svenska
    skapar en ListModel
    För varje sko i listan använder vi metoden getInventory i sko för att få
    en bättre utskrift till kund och lägger strängen till listModel och sätter listModel
    */

    public void showInventoryList() {
        final List<Sko> skoList = r.getAllaSko();
        final Collator collator = Collator.getInstance(new Locale("sv", "SV"));
        final DefaultListModel<String> listModel = new DefaultListModel<>();
        skoList.stream().map(Sko::getInventory).sorted(collator).forEach(listModel::addElement);
        list1.setModel(listModel);
    }

    /*
    findSkoById tar in en int och hittar skon i en lista med skor och returns en sträng av Skon med id't för att
    visa skon i topplista.
    */

    public String findSkoById(int skoId) {
        final List<Sko> skoList = r.getAllaSko();
        return skoList.stream().filter(sko -> sko.getId() == skoId).map(Sko::getSko).findFirst().orElse("");
    }

    /*
    findSkoId använder strängen som kommer ifrån selectedValue i Jlist för att identifiera skon och hitta rätt objekt
    till vald sko och return hela sko objektet.
    */

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

    // TODO: 2/2/2023 Bygg klart funktion att söka via bar på Märke,Modell,Storlek eller färg.
    // Skapa en array av storlek 1-4 och använd split för att dela upp orden.
    // Finns fler än 1 ord i array visa enbart Match för all input
    /*
    public void searchQuery() throws SQLException {
        String searchQuery = inputField.getText();
    }
    */
    // TODO: 2/2/2023 Lägg till möjlighet att lägga till orders till en cart och sedan beställa hela carten i en order.
    // Visa carten dynamiskt i en extra JList parallelt med huvud Display.
}
