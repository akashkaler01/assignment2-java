package org.example.as2;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class weatherservices {

    private static final String API_KEY = "";

    public static void fetchWeatherData(String city, TextArea resultArea) {
        new Thread(() -> {
            try {
                String jsonResponse = getWeather(city);
                WeatherResponse weather = parseWeatherData(jsonResponse);
                String result = String.format("Temperature: %.2f°C\nDescription: %s",
                        weather.getMain().getTemp() - 273.15,
                        weather.getWeather()[0].getDescription());

                Platform.runLater(() -> resultArea.setText(result));
            } catch (Exception e) {
                Platform.runLater(() -> resultArea.setText("Error: " + e.getMessage()));
            }
        }).start();
    }

    private static String getWeather(String city) throws Exception {
        String urlString = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", city, API_KEY);
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        return response.toString();
    }

    private static WeatherResponse parseWeatherData(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, WeatherResponse.class);
    }
}
