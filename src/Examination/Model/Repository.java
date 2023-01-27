package Examination.Model;

import Assignments_JDBC_1_to_6.repository.Elf;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;

public class Repository {
    private final Properties p = new Properties();

    public Repository() {
        loadProperties();
    }

    public DefaultListModel<String> displayInventory() {
        DefaultListModel<String> inventoryListModel = new DefaultListModel<>();
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));

             CallableStatement stm = con.prepareCall("CALL getInventory()")) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                //Hämtar data ifrån ResultSet och add till inventoryListModel
                inventoryListModel.addElement(rs.getString("märke") + " - " + rs.getString("model")
                        + " - " + rs.getString("färg") + " - " + rs.getString("storlek")
                        + " - Pris: " + rs.getString("pris") + " - Saldo: " + rs.getString("lagerSaldo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventoryListModel;
    }

    private void loadProperties() {
        try {
            p.load(new FileInputStream("resources/settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private <T> List<T> executeQuery(String query, Function<ResultSet, T> mapper) {
        List<T> result = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                result.add(mapper.apply(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Färg> getAllaFärger() {
        return executeQuery("SELECT * FROM färg", rs -> {
            try {
                return new Färg(rs.getInt("id"), rs.getString("färg"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<Storlek> getAllaStorlekar() {
        List<Storlek> allaStorlekar = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from storlek")) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String storlek = rs.getString("storlek");
                allaStorlekar.add(new Storlek(id, storlek));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allaStorlekar;
    }

    public List<Märke> getAllaMärke() {
        List<Märke> allaMärken = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from märke")) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String märke = rs.getString("märke");
                allaMärken.add(new Märke(id, märke));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allaMärken;
    }

    public List<Model> getAllaModel() {
        List<Model> allaModel = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from model")) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String model = rs.getString("model");
                allaModel.add(new Model(id, model));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allaModel;
    }

    public List<Kund> getAllaKunder() {
        List<Kund> allaKunder = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from kund")) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String email = rs.getString("email");
                String namn = rs.getString("namn");
                String adress = rs.getString("adress");
                String password = rs.getString("password");
                String skapad = rs.getString("skapad");
                String ändrad = rs.getString("ändrad");
                allaKunder.add(new Kund(id, email, namn, adress, password, skapad, ändrad));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allaKunder;
    }

    public List<Kategori> getAllaKategori() {
        List<Kategori> allaKategori = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from kategori")) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String kategori = rs.getString("kategori");
                allaKategori.add(new Kategori(id, kategori));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allaKategori;
    }

    public List<Färg> getAllaFärg() {
        List<Färg> allaFärg = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from färg")) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String färg = rs.getString("färg");
                allaFärg.add(new Färg(id, färg));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allaFärg;
    }

    public List<Beställning> getAllaBeställning() {
        List<Beställning> allaBeställning = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from beställning")) {

            //Prova att hämta kund där beställning.kundId == kund.ID ocskå på en gång.

            while (rs.next()) {
                int id = rs.getInt("id");
                int kundId = rs.getInt("kundId");
                int totalPris = rs.getInt("totalPris");
                String skapad = rs.getString("skapad");
                String ändrad = rs.getString("ändrad");

                // Retrieve the Kund object using the kundId
                Kund kund = getKundById(kundId);

                // Create a new Beställning object
                Beställning beställning = new Beställning(id, kund, totalPris, skapad, ändrad);

                // Add the Beställning object to the allaBeställning list
                allaBeställning.add(beställning);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allaBeställning;
    }
    public Kund getKundById(int id) {
        Kund kund = null;
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM kund WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs .getInt("id");
                String email = rs.getString("email");
                String namn = rs.getString("namn");
                String adress = rs.getString("adress");
                String password = rs.getString("password");
                String skapad = rs.getString("skapad");
                String ändrad = rs.getString("ändrad");
                kund = new Kund(id, email, namn, adress, password, skapad, ändrad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kund;
    }
}

