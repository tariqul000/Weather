package com.example.java.weather;

import com.example.java.weather.apixu.forecast.AForecastAPI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.java.weather.apixu.forecast.AForecast;
import com.example.java.weather.apixu.forecast.AForecastAPI;
import com.example.java.weather.apixu.forecast.RetrofitClient;

import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity{
    private String city= "dhaka";
    private TextView pressureTV;
    private TextView visibilityTV;
    private TextView feelsTV;
    private TextView humidityTV;
    private TextView cityTV;
    private TextView precipTV;
    private TextView sunriseTV;
    private TextView sunsetTV;
    private TextView countryTV;
    int j,k,i,l,m;
    private LinearLayout mWeatherInfosLayout;
    private int contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String checkCity = preferences.getString("city", null);

        pressureTV = (TextView) findViewById(R.id. pressureTV);
        countryTV = (TextView) findViewById(R.id.countryTV);
        visibilityTV = (TextView) findViewById(R.id.visibilityTV );
        feelsTV = (TextView) findViewById(R.id.feelsTV);
        humidityTV = (TextView) findViewById(R.id.humidityTV);
        cityTV = (TextView) findViewById(R.id.cityTV);
        precipTV = (TextView) findViewById(R.id.precipTV);
        sunriseTV = (TextView) findViewById(R.id.sunriseTV);
        sunsetTV = (TextView) findViewById(R.id.sunsetTV);

        getCurrentFocus();
        city = checkCity;
        getCurrentDetails(city);
    }

   private void getCurrentDetails(String city) {
      // mWeatherInfosLayout.removeAllViews();
        AForecastAPI aForecastAPI = RetrofitClient.getClient().create(AForecastAPI.class);
        retrofit2.Call<AForecast> aForecastCall = aForecastAPI.getAForecastInfo(city);
        aForecastCall.enqueue(new retrofit2.Callback<AForecast>() {
            @Override
            public void onResponse(retrofit2.Call<AForecast> call, retrofit2.Response<AForecast> response) {
                AForecast aForecast=response.body();
                cityTV.setText(aForecast.getLocation().getName());
                feelsTV.setText("Feels "+j+aForecast.getCurrent().getFeelslikeC()+"%");
                countryTV.setText(aForecast.getLocation().getCountry());
                pressureTV.setText("Pressure "+k+aForecast.getCurrent().getPressureIn()+ " inch Hg");
                humidityTV.setText("Humidity "+i+aForecast.getCurrent().getHumidity());
                visibilityTV.setText("Visible "+l+aForecast.getCurrent().getVisKm()+"Km");
               /* precipTV.setText("Precipitation  "+m+aForecast.getCurrent().getPrecipMm()+" mm");*/
                precipTV.setText("Cloudiness  "+m+aForecast.getCurrent().getCloud()+"%");
                sunriseTV.setText("Sunrise "+aForecast.getForecast().getForecastday().get(0).getAstro().getSunrise());
                sunsetTV.setText("Sunset "+aForecast.getForecast().getForecastday().get(0).getAstro().getSunset());
            }
            @Override
            public void onFailure(retrofit2.Call<AForecast> call, Throwable t) {

            }
        });
    }

}