package Examination.Model;

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

    private void loadProperties() {
        try {
            p.load(new FileInputStream("resources/settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* Högre ordningens funktion:
    Funktionen utför en SQL query och returnerar resultat som en generisk lista
    Vi tar in en query och en Function för att mappa resultat till nytt format
     */

    private <T> List<T> executeQuery(String query, Function<ResultSet, T> mapper) {
        List<T> result = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                result.add(mapper.apply(rs)); // tar varje rad i rs och använder en mapper på varje rad
            }                                 // och lägger resultatet i listan result
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public void callAddToCart(int kundId, int skoId,String kundName) {
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));

             CallableStatement stm = con.prepareCall("CALL AddToCart(?,?,?)")) {
            stm.setInt(1, kundId);
            stm.setInt(2, Integer.MAX_VALUE);
            stm.setInt(3, skoId);
            stm.execute();
            System.out.println("Ny order skapad för " + kundName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public List<Färg> getAllaFärg() {
        return executeQuery("SELECT * FROM färg", rs -> {
            try {
                return new Färg(rs.getInt("färg.id"), rs.getString("färg.färg"));

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<Storlek> getAllaStorlekar() {
        return executeQuery("select * from storlek", rs -> {
            try {
                return new Storlek(rs.getInt("id"), rs.getInt("storlek"));
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
        return executeQuery("select * from kategori", rs -> {
            try {
                return new Kategori(rs.getInt("id"), rs.getString("kategori"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<Beställning> getAllaBeställning() {
        return executeQuery("""
                        SELECT beställning.*, kund.*
                        FROM beställning
                                 JOIN kund ON beställning.kundId = kund.idd""",
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

    public List<Pris> getAllaPris() {
        return executeQuery("""
                Select pris.*, model.*, märke.*
                FROM pris
                         JOIN model ON pris.modelId = model.id
                         JOIN märke ON pris.märkeId = märke.id""", rs -> {
            try {
                Model model = new Model(rs.getInt("model.id"), rs.getString("model.model"));
                Märke märke = new Märke(rs.getInt("märke.id"), rs.getString("märke.märke"));

                return new Pris(rs.getInt("pris.id"), rs.getInt("pris.pris"), model, märke,
                        rs.getString("pris.skapad"), rs.getString("pris.ändrad"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<Kundvagn> getAllaKundvagn() {
        return executeQuery("""
                select kundvagn.*,sko.*,beställning.*,märke.*,model.*,storlek.*,färg.*,pris.*,kund.*
                FROM kundvagn
                JOIN sko ON kundvagn.skoId = sko.id
                JOIN beställning ON kundvagn.beställningId = beställning.id
                JOIN märke ON sko.märkeId = märke.id
                JOIN model ON sko.modelId = model.id
                JOIN storlek ON sko.storlekId = storlek.id
                JOIN färg ON sko.färgId = färg.id
                JOIN pris ON sko.prisId = pris.id
                JOIN kund ON beställning.kundId = kund.id""", rs -> {
            try {
                Märke märke = new Märke(rs.getInt("märke.id"), rs.getString("märke.märke"));
                Model model = new Model(rs.getInt("model.id"), rs.getString("model.model"));
                Pris pris = new Pris(rs.getInt("pris.id"), rs.getInt("pris.pris"), model, märke,
                        rs.getString("pris.skapad"), rs.getString("pris.ändrad"));
                Färg färg = new Färg(rs.getInt("färg.id"), rs.getString("färg.färg"));
                Storlek storlek = new Storlek(rs.getInt("storlek.id"), rs.getInt("storlek.storlek"));
                Sko sko = new Sko(rs.getInt("sko.id"), märke, model, storlek, färg, pris, rs.getInt("sko.lagerSaldo"),
                        rs.getString("sko.skapad"), rs.getString("sko.ändrad"));
                Beställning beställning = new Beställning(rs.getInt("beställning.id"), new Kund(
                        rs.getInt("kund.id"), rs.getString("kund.email"), rs.getString("kund.namn"),
                        rs.getString("kund.adress"), rs.getString("kund.password"), rs.getString(
                        "kund.skapad"), rs.getString("kund.ändrad")), rs.getInt("beställning.totalPris"),
                        rs.getString("beställning.skapad"), rs.getString("beställning.ändrad"));
                return new Kundvagn(rs.getInt("id"), rs.getInt("antal"), sko, beställning);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<Sko> getAllaSko() {
        return executeQuery("""
                SELECT sko.*,märke.*,model.*,storlek.*,färg.*,pris.*
                FROM sko
                JOIN märke ON sko.märkeId = märke.id
                JOIN model ON sko.modelId = model.id
                JOIN storlek ON sko.storlekId = storlek.id
                JOIN färg ON sko.färgId = färg.id
                JOIN pris ON sko.prisId = pris.id""", rs -> {
            try {
                Märke märke = new Märke(rs.getInt("märke.id"), rs.getString("märke.märke"));
                Model model = new Model(rs.getInt("model.id"), rs.getString("model.model"));
                Färg färg = new Färg(rs.getInt("färg.id"), rs.getString("färg.färg"));
                Pris pris = new Pris(rs.getInt("pris.id"), rs.getInt("pris.pris"), model, märke,
                        rs.getString("pris.skapad"), rs.getString("pris.ändrad"));
                Storlek storlek = new Storlek(rs.getInt("storlek.id"), rs.getInt("storlek.storlek"));
                return new Sko(rs.getInt("sko.id"), märke, model, storlek, färg, pris, rs.getInt("sko.lagerSaldo"),
                        rs.getString("sko.skapad"), rs.getString("sko.ändrad"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<SkoKatMapp> getAllaSkoKatMapp() {
        return executeQuery("""
                SELECT skokatmapp.*,sko.*,kategori.*
                FROM skokatmapp
                JOIN sko ON skokatmapp.skoId = sko.id
                JOIN kategori ON skokatmapp.kategoriId = kategori.id""", rs -> {
            try {
                Märke märke = new Märke(rs.getInt("märke.id"), rs.getString("märke.märke"));
                Model model = new Model(rs.getInt("model.id"), rs.getString("model.model"));
                Pris pris = new Pris(rs.getInt("pris.id"), rs.getInt("pris.pris"), model, märke,
                        rs.getString("pris.skapad"), rs.getString("pris.ändrad"));
                Färg färg = new Färg(rs.getInt("färg.id"), rs.getString("färg.färg"));
                Storlek storlek = new Storlek(rs.getInt("storlek.id"), rs.getInt("storlek.storlek"));
                Sko sko = new Sko(rs.getInt("sko.id"), märke, model, storlek, färg, pris, rs.getInt("sko.lagerSaldo"),
                        rs.getString("sko.skapad"), rs.getString("sko.ändrad"));
                Kategori kategori = new Kategori(rs.getInt("kategori.id"), rs.getString("kategori.kategori"));
                return new SkoKatMapp(rs.getInt("skokatmapp.id"), sko, kategori);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

