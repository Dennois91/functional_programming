package Weeks_With_JAVA.Examination.Controller;

import Weeks_With_JAVA.Examination.Model.Kund;
import Weeks_With_JAVA.Examination.Model.Repository;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class LoginController extends JFrame {
    private final Repository r = new Repository();
    private JPanel loginPanel;
    private JButton loginBut;
    private JTextField passwordField;
    private JTextField usernameTextField;
    final private List<Kund> kunder = r.getAllaKunder();

    /*
    Repository skapas upp en gång och enbart härifrån
    Kundlist skapas för att matcha användarnamn och lösenord
    Vid giltigt login körs metoden valid login och loginfönster stängs
    Vi skickar med kund och repository in i StoreController som skapas upp
     */

    public LoginController() {
        setContentPane(loginPanel);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400, 90);
        setLocationRelativeTo(null);

        loginBut.addActionListener(l -> {
            Optional<Kund> kundFinns = kunder.stream()
                    .filter(kund -> kund.namn.equals(usernameTextField.getText()) &&
                            kund.password.equals(passwordField.getText()))
                    .findFirst();
            kundFinns.ifPresentOrElse(
                    this::validLogin,
                    this::failedLogin
            );
        });
    }

    private void validLogin(Kund kund) {
        dispose();
        StoreController storeController = new StoreController(kund, r);
        storeController.showInventoryList();
        revalidate();
        repaint();
    }

    private void failedLogin() {
        JOptionPane.showMessageDialog(null, "Ogiltigt inloggnings försök",
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}