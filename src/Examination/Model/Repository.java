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
                p.getProperty("username"),
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

    public List<Färg> getAllaFärg() {
        return executeQuery("SELECT * FROM färg", rs -> {
            try {
                return new Färg(rs.getInt("id"), rs.getString("färg"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<Storlek> getAllaStorlekar() {
        return executeQuery("select * from storlek", rs -> {
            try {
                return new Storlek(rs.getInt("id"), rs.getString("storlek"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<Märke> getAllaMärke() {
        return executeQuery("select * from märke", rs -> {
            try {
                return new Märke(rs.getInt("id"), rs.getString("märke"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<Model> getAllaModel() {
        return executeQuery("select * from model", rs -> {
            try {
                return new Model(rs.getInt("id"), rs.getString("model"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<Kund> getAllaKunder() {
        return executeQuery("select * from kund", rs -> {
            try {
                return new Kund(rs.getInt("id"), rs.getString("email"),
                        rs.getString("namn"), rs.getString("adress"),
                        rs.getString("password"), rs.getString("skapad"),
                        rs.getString("ändrad"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<Kategori> getAllaKategori() {
        return executeQuery(" select * from kategori", rs -> {
            try {
                return new Kategori(rs.getInt("id"), rs.getString("kategori"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<Beställning> getAllaBeställning() {
        return executeQuery("SELECT beställning.*, kund.* FROM beställning JOIN kund ON beställning.kundId = kund.id",
                rs -> {
                    try {
                        Kund kund = null;
                        try {
                            kund = new Kund(rs.getInt("kund.id"), rs.getString("kund.email"),
                                    rs.getString("kund.namn"), rs.getString("kund.adress"),
                                    rs.getString("kund.password"), rs.getString("kund.skapad"),
                                    rs.getString("kund.ändrad"));
                            return new Beställning(rs.getInt("beställning.id"), kund, rs.getInt("beställning.totalpris"),
                                    rs.getString("beställning.skapad"), rs.getString("beställning.ändrad"));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                    } catch (RuntimeException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public List<Pris> getAllaPris(){
        return executeQuery("Select pris.*, model.*, märke.* FROM pris JOIN model ON pris.modelId = model.id" +
                "JOIN märke ON pris.märkeId = märke.id",rs -> {
            try {

            }
        })

    }

}

