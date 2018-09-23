package com.example.java.weather;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.java.weather.apixu.forecast.AForecast;
import com.example.java.weather.apixu.forecast.AForecastAPI;
import com.example.java.weather.apixu.forecast.RetrofitClient;

import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Winddir extends AppCompatActivity{
    private String city= "dhaka";
    private TextView  cityTV;
    private TextView winddirecTV;
    private TextView windblowTV;
    private TextView countryTV;
    private TextView timeTV;
    GifImageView gif;
    int i,j;
    private LinearLayout mWeatherInfosLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winddir);
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        String checkCity = preferences.getString("city",null);


        cityTV = (TextView) findViewById(R.id.cityTV);
        winddirecTV = (TextView) findViewById(R.id.winddirecTV);
        countryTV=(TextView)findViewById(R.id.countryTV);
        timeTV = (TextView) findViewById(R.id.timeTV);
        windblowTV = (TextView) findViewById(R.id.windblowTV);
        gif= (GifImageView) findViewById(R.id.gif);

        getCurrentFocus();
        city=checkCity;
        getCurrentDetails(city);

      /*  // for gif pic
      Glide.with(Winddir.this)
              .load(R.drawable.gifgh)
              .into(gif);*/
    }

    private void getCurrentDetails(String city) {
        //mWeatherInfosLayout.removeAllViews();
        AForecastAPI aForecastAPI = RetrofitClient.getClient().create(AForecastAPI.class);
        retrofit2.Call<AForecast> aForecastCall = aForecastAPI.getAForecastInfo(city);
        aForecastCall.enqueue(new retrofit2.Callback<AForecast>() {
            @Override
            public void onResponse(retrofit2.Call<AForecast> call, retrofit2.Response<AForecast> response) {
                AForecast aForecast=response.body();
                cityTV.setText(aForecast.getLocation().getName());
                countryTV.setText(aForecast.getLocation().getCountry());
                timeTV.setText(aForecast.getLocation().getLocaltime());
                winddirecTV.setText("Direction "+i+aForecast.getCurrent().getWindDir());
                windblowTV.setText("Speed "+j+aForecast.getCurrent().getWindKph()+"Kph");
            }
            @Override
            public void onFailure(retrofit2.Call<AForecast> call, Throwable t) {

            }
        });
    }
}
