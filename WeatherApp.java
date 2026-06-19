import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class WeatherApp {

    public static void main(String[] args) {

        String apiUrl = "https://api.open-meteo.com/v1/forecast"
                + "?latitude=18.68&longitude=73.911&current_weather=true";

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonObject = new JSONObject(response.body());
            JSONObject weather = jsonObject.getJSONObject("current_weather");

            System.out.println("        WEATHER REPORT            ");

            System.out.println("Temperature: " + weather.getDouble("temperature") + " °C");
            System.out.println("Wind Speed: " + weather.getDouble("windspeed") + " km/h");
            System.out.println("Wind Direction: " + weather.getInt("winddirection") + "°");
            System.out.println("Time: " + weather.getString("time"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

