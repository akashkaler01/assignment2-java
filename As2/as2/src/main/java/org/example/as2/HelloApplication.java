package org.example.as2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Weather App");

        TextField cityInput = new TextField();
        cityInput.setPromptText("Enter city name");

        Button fetchButton = new Button("Get Weather");

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        fetchButton.setOnAction(e -> {
            String city = cityInput.getText();
            if (!city.isEmpty()) {
                weatherservices.fetchWeatherData(city, resultArea);
            }
        });

        VBox vbox = new VBox(10, new Label("Weather Information"), cityInput, fetchButton, resultArea);
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
