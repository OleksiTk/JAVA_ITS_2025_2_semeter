package com.example; // Перевір, чи правильний тут пакет

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    // --- Таблиця та колонки ---
    @FXML
    private TableView<Site> table_sites;

    @FXML
    private TableColumn<Site, Integer> col_id;

    @FXML
    private TableColumn<Site, String> col_name;

    @FXML
    private TableColumn<Site, Double> col_lat;

    @FXML
    private TableColumn<Site, Double> col_lon;

    @FXML
    private TableColumn<Site, String> col_region;

    // --- Текстові поля ---
    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_lat;

    @FXML
    private TextField txt_lon;

    @FXML
    private TextField txt_region;

    // --- Кнопки ---
    @FXML
    private Button btn_insert;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_clear;

    @FXML
    private Button btn_delete;

    // Метод, який викликається автоматично при завантаженні форми
    @FXML
    public void initialize() {
        // Тут ми будемо налаштовувати таблицю
        setupTableColumns();
        loadDataFromDatabase();
    }

    // Налаштування колонок: вказуємо, які властивості з класу Site брати
    private void setupTableColumns() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_lat.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        col_lon.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        col_region.setCellValueFactory(new PropertyValueFactory<>("region"));
    }

    // Завантаження даних з бази
    private void loadDataFromDatabase() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ObservableList<Site> sitesList = dbHandler.getAllSites();
        table_sites.setItems(sitesList);
    }
    // --- ДІЇ ДЛЯ КНОПОК ---

    @FXML
    private void handleInsert() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        // Беремо текст з полів і перетворюємо координати в числа
        dbHandler.insertSite(
            txt_name.getText(), 
            Double.parseDouble(txt_lat.getText()), 
            Double.parseDouble(txt_lon.getText()), 
            txt_region.getText()
        );
        loadDataFromDatabase(); // Оновлюємо таблицю
        handleClear();          // Очищаємо поля
    }

    @FXML
    private void handleUpdate() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.updateSite(
            Integer.parseInt(txt_id.getText()),
            txt_name.getText(), 
            Double.parseDouble(txt_lat.getText()), 
            Double.parseDouble(txt_lon.getText()), 
            txt_region.getText()
        );
        loadDataFromDatabase(); 
        handleClear();
    }

    @FXML
    private void handleDelete() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.deleteSite(Integer.parseInt(txt_id.getText()));
        loadDataFromDatabase();
        handleClear();
    }

    @FXML
    private void handleClear() {
        txt_id.clear();
        txt_name.clear();
        txt_lat.clear();
        txt_lon.clear();
        txt_region.clear();
    }
}