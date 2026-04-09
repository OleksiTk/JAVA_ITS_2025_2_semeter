package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseHandler {
    // Дані для підключення
    private String dbHost = "localhost";
    private String dbPort = "3306";
    private String dbUser = "root";      // Твій логін MySQL
    private String dbPass = "123root";   // Твій пароль до MySQL
    private String dbName = "ukraine_sites";

    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        // Формуємо рядок підключення
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        // Завантажуємо драйвер
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Встановлюємо з'єднання
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    // Тепер цей метод ЗНАХОДИТЬСЯ ВСЕРЕДИНІ класу DatabaseHandler
    public ObservableList<Site> getAllSites() {
        ObservableList<Site> list = FXCollections.observableArrayList();
        String query = "SELECT * FROM sites";

        try (Connection conn = getDbConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                list.add(new Site(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("latitude"),
                    rs.getDouble("longitude"),
                    rs.getString("region"),
                    rs.getString("image")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    // Метод для додавання (Insert)
    public void insertSite(String name, double lat, double lon, String region) {
        String query = "INSERT INTO sites (name, latitude, longitude, region) VALUES (?, ?, ?, ?)";
        try (java.sql.Connection conn = getDbConnection();
             java.sql.PreparedStatement prSt = conn.prepareStatement(query)) {
            prSt.setString(1, name);
            prSt.setDouble(2, lat);
            prSt.setDouble(3, lon);
            prSt.setString(4, region);
            prSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Метод для оновлення (Update)
    public void updateSite(int id, String name, double lat, double lon, String region) {
        String query = "UPDATE sites SET name=?, latitude=?, longitude=?, region=? WHERE id=?";
        try (java.sql.Connection conn = getDbConnection();
             java.sql.PreparedStatement prSt = conn.prepareStatement(query)) {
            prSt.setString(1, name);
            prSt.setDouble(2, lat);
            prSt.setDouble(3, lon);
            prSt.setString(4, region);
            prSt.setInt(5, id);
            prSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Метод для видалення (Delete)
    public void deleteSite(int id) {
        String query = "DELETE FROM sites WHERE id=?";
        try (java.sql.Connection conn = getDbConnection();
             java.sql.PreparedStatement prSt = conn.prepareStatement(query)) {
            prSt.setInt(1, id);
            prSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
