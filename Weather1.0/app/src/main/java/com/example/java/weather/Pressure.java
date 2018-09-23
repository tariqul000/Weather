package com.example.java.weather;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pressure extends AppCompatActivity {
    int k;
    private TextView Layout;
    private TextView cityTV;

    private TextView pressureTV;
    public static String city = null;
    static Dialog dialog;
    private Weather weather;
    private WeatherAPI weatherAPI;
    private android.location.Location location;
    private SharedPreferences weatherPreferen;
    double l=25.4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure);



        weather = new Weather();
        getRetrofitObject();
        weatherPreferen = PreferenceManager.getDefaultSharedPreferences(this);
        cityTV=(TextView)findViewById(R.id.cityTV);
        pressureTV = (TextView) findViewById(R.id.pressureTV);
    }

    void getRetrofitObject() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.apixu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherAPI service = retrofit.create(WeatherAPI.class);

        Call<Weather> call = service.getWeatherInfo();

        Log.d("Api", call.request().url().toString());
        call.enqueue(new Callback<Weather>() {
            @RequiresApi(api = Build.VERSION_CODES.N)



            public void onResponse(Call<Weather> call, Response<Weather> response) {Weather weather = response.body();

                cityTV.setText(weather.getLocation().getName());

                //feelsTV.setText("Feels "+j+weather.getCurrent().getFeelslikeC()+"%");
                pressureTV.setText("Pressure "+k+weather.getCurrent().getPressureIn()+ " inch Hg");


            }


            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }

        });
    }
}
