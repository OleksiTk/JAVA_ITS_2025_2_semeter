package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Шукаємо файл main.fxml у папці resources
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/main.fxml"));
        
        // Створюємо сцену (вікно) з розмірами 800x600
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        
        // Налаштовуємо та показуємо головне вікно
        stage.setTitle("Визначні місця України");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // Цей метод запускає весь життєвий цикл JavaFX
        launch();
    }
}