package com.kpi.dbviewer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;
import io.github.cdimascio.dotenv.Dotenv;

public class App extends JFrame {

    private static final Dotenv dotenv = Dotenv.load();
    
    private static final String DB_URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");       
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

    public App() {
        setTitle("Перегляд таблиці MySQL (Завдання 2)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); 

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);

        JButton loadButton = new JButton("Завантажити дані з БД");
        loadButton.addActionListener(e -> loadData(tableModel));

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(loadButton, BorderLayout.SOUTH);
        
        loadData(tableModel);
    }

    private void loadData(DefaultTableModel tableModel) {
        String query = "SELECT * FROM students";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);

            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(metaData.getColumnName(i));
            }

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getObject(i));
                }
                tableModel.addRow(row);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                    "Помилка підключення до БД: " + ex.getMessage(), 
                    "Помилка", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }
}