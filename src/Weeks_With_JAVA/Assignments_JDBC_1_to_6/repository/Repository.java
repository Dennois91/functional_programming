package Weeks_With_JAVA.Assignments_JDBC_1_to_6.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Repository {
    private final Properties p = new Properties();
    private final Connection connection;

    public Repository() {
        loadProperties();
        connection = createConnection();
    }

    public String getChildNameAndNice(String searchName) {
        String returnString = null;
        ResultSet rs;
        try (PreparedStatement stmt = connection.prepareStatement("SELECT name, " +
                "case when report.niceOrNot = true then 'Has been nice' else 'Has been naughty' end as niceOrNot" +
                " FROM child JOIN report ON child.id = report.childId WHERE name = ?")) {
            stmt.setString(1, searchName);

            rs = stmt.executeQuery();
            while (rs.next()) {
                returnString = rs.getString("name") + rs.getString("niceOrNot");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return returnString;
    }


    public List<Child> getChildrenList() {
        List<Child> children = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name,report.niceOrNot" +
                     " FROM child join report on child.id = report.childId")) {
            while (rs.next()) {
                Child child = new Child();
                child.setName(rs.getString("name"));
                child.setNiceOrNot(rs.getBoolean("niceOrNot"));
                children.add(child);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return children;
    }

    public List<Elf> getElvesList() {
        List<Elf> elves = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM Elf")) {
            while (rs.next()) {
                Elf elf = new Elf();
                elf.setName(rs.getString("name"));
                elves.add(elf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return elves;
    }


    private Connection createConnection() {
        try {
            return DriverManager.getConnection(p.getProperty("connectionString"),
                    p.getProperty("username"), p.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void loadProperties() {
        try {
            p.load(new FileInputStream("resources/settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeElfNameByName(String name, String newName) {

        String query = "UPDATE elf SET name = ? WHERE name like ? ;";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("username"), p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setString(2, name);
            int rowChanged = stmt.executeUpdate();
            switch (rowChanged) {
                case 0 -> System.out.println("no elf with that name found");
                case 1 -> System.out.println("Elf" + name + " changed name to " + newName);
                default -> System.out.println("Several names changed name to " + newName);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteElfByName(String name) {

        String query = "DELETE FROM elf WHERE name = ? ;";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("username"), p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, name);

            int rowChanged = stmt.executeUpdate();
            switch (rowChanged) {
                case 0 -> System.out.println("no elf with that name found");
                case 1 -> System.out.println("Elf " + name + " Fired");
                default -> System.out.println("Several elves with " + name + " fired");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addNewGift(String name) {

        String query = "INSERT INTO present (name) VALUES (?);";

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, name);

            int rowChanged = stmt.executeUpdate();

            switch (rowChanged) {
                case 0 -> System.out.println("No gift added");
                case 1 -> System.out.println("Gift " + name + " added");
                default -> System.out.println("Unexpected error");


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void callAddGift(String name) {

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));

             CallableStatement stm = con.prepareCall("CALL addPresent(?)")) {
            stm.setString(1, name);
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void callAddNewManufacturingElfSP(String name) {
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));

             CallableStatement stm = con.prepareCall("CALL addManufacturingElf(?)")) {
            stm.setString(1, name);
            stm.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}