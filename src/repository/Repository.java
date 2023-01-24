package repository;

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
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name," +
                     " case when report.niceOrNot = true then 'Has been nice' else 'Has been naughty' end as niceOrNot" +
                     " FROM child join report " +
                     "on child.id = report.childId WHERE name like '%" + searchName + "%'")) {

            while (rs.next()) {
                returnString = (rs.getString("name")) + " " + (rs.getString("niceOrNot"));
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
}


