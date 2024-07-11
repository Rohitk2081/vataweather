package com.weather.wataweather;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.weather.wataweather.api.WeatherService;
import com.weather.wataweather.model.WeatherResponse;

import missing.namespace.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView cityNameTextView;
    private TextView countryTextView;
    private TextView temperatureTextView;
    private TextView feelsLikeTextView;
    private TextView weatherDescriptionTextView;
    private TextView windSpeedTextView;
    private TextView windDirectionTextView;
    private TextView pressureTextView;
    private TextView humidityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityNameTextView = findViewById(R.id.cityName);
        countryTextView = findViewById(R.id.country);
        temperatureTextView = findViewById(R.id.temperature);
        feelsLikeTextView = findViewById(R.id.feelsLike);
        weatherDescriptionTextView = findViewById(R.id.weatherDescription);
        windSpeedTextView = findViewById(R.id.windSpeed);
        windDirectionTextView = findViewById(R.id.windDirection);
        pressureTextView = findViewById(R.id.pressure);
        humidityTextView = findViewById(R.id.humidity);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create WeatherService instance
        WeatherService weatherService = retrofit.create(WeatherService.class);

        // Example of using the API key (assuming you have defined it in your BuildConfig)
        String apiKey = "0f10bdcf8a7f5a1eb8d786a061b0e9da";
        String city = "London";
        String units = "metric"; // or "imperial" for Fahrenheit

        // Make the network call
        Call<WeatherResponse> call = weatherService.getWeather(city, apiKey, units);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weatherResponse = response.body();

                    // Update UI with weather data
                    cityNameTextView.setText(weatherResponse.getName());
                    countryTextView.setText(weatherResponse.getSys().getCountry());
                    temperatureTextView.setText("Temperature: " + weatherResponse.getMain().getTemp() + " °C");
                    feelsLikeTextView.setText("Feels Like: " + weatherResponse.getMain().getFeels_like() + " °C");
                    weatherDescriptionTextView.setText("Description: " + weatherResponse.getWeather().get(0).getDescription());
                    windSpeedTextView.setText("Wind Speed: " + weatherResponse.getWind().getSpeed() + " m/s");
                    windDirectionTextView.setText("Wind Direction: " + weatherResponse.getWind().getDeg() + "°");
                    pressureTextView.setText("Pressure: " + weatherResponse.getMain().getPressure() + " hPa");
                    humidityTextView.setText("Humidity: " + weatherResponse.getMain().getHumidity() + "%");
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                // Handle failure
                cityNameTextView.setText("Failed to fetch weather data");
            }
        });
    }
}
