package com.example.java.weather;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class DetailsActivity extends AppCompatActivity {
    private String city= "dhaka";
    private TextView pressureTV;
    private TextView visibilityTV;
    private TextView feelsTV;
    private TextView humidityTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        String checkCity = preferences.getString("city",null);

        pressureTV = (TextView) findViewById(R.id.pressureTV);
        visibilityTV = (TextView) findViewById(R.id.visibilityTV);
        feelsTV = (TextView) findViewById(R.id.feelsTV);
        humidityTV = (TextView) findViewById(R.id.humidityTV);

        city=checkCity;
        getCurrentDetails(city);

    }

    public void getCurrentDetails(String city) {

        WeatherAPI weatherAIP = RetrofitClass.getClient().create(WeatherAPI.class);
        Call<Forecast> forecastCall=weatherAIP.getWeatherInfo(city);
        forecastCall.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Response<Forecast> response, Retrofit retrofit) {
                Forecast forecast=response.body();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });





    }
}
