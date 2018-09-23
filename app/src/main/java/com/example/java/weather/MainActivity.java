package com.example.java.weather;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.java.weather.apixu.forecast.AForecast;
import com.example.java.weather.apixu.forecast.AForecastAPI;
import com.example.java.weather.apixu.forecast.RetrofitClient;

import java.util.Date;

import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{
    private double modeType;
    int hours;
    int min;
    int i;
    int j;
    int k;
    private TextView Layout;
    private TextView cityTV;
    private TextView timeTV;
    private TextView tempTV;
    private TextView highTempTV;
    private TextView lowTempTV;
    private TextView howDayTV;
    private TextView countryTV;
    private TextView humidityTV;
    private TextView feelsTV;
    private TextView visibilityTV;
    private ImageView gif;
    GifImageView imageview;
    public static String city = null;
    static Dialog dialog;
    //private Weather weather;
    private WeatherAPI weatherAPI;
    private Location location;
    EditText addET;
    //Button btnsr;
    ImageView howDayImg;
    private ProgressDialog mProgressDialog;
    private RelativeLayout mWeatherInfosLayout;

    Button btnsr, btnDe, btnfe, btnvi, btndr, btnprssure, btnprep, btnForecast;
    //ImageButton btnsr;
    SwipeRefreshLayout swipe;
    // ImageView howDayImg = (ImageView)findViewById(R.id.howdayImg);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsr = (Button) findViewById(R.id.search_button);
        btnDe = (Button) findViewById(R.id.details);
        btndr = (Button) findViewById(R.id.winddir);
        btnForecast = (Button) findViewById(R.id.foecast);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Intent i = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

       btnsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddcityActivity.class);
                startActivity(i);
            }
       });
        btnForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ForecastActivity.class);
                startActivity(i);
            }
        });
        btnDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(i);
            }
        });

        btndr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Winddir.class);
                startActivity(i);
            }
        });

        SharedPreferences weatherPreferen = PreferenceManager.getDefaultSharedPreferences(this);
        String checkCity = weatherPreferen.getString("city", null);
        if (checkCity == null) {
            Intent intent = new Intent(this, AddcityActivity.class);
            startActivity(intent);

        } else {
            cityTV = (TextView) findViewById(R.id.cityTV);
            //countryTV=(TextView)findViewById(R.id.countryTV);
            timeTV = (TextView) findViewById(R.id.timeTV);
            tempTV = (TextView) findViewById(R.id.tempTV);
            highTempTV = (TextView) findViewById(R.id.highTV);
            lowTempTV = (TextView) findViewById(R.id.lowTV);
            howDayTV = (TextView) findViewById(R.id.howdayTV);
            imageview = (GifImageView) findViewById(R.id.imageview);
            howDayImg=(ImageView)findViewById(R.id.howDayTV);

            city = checkCity;
            getCurrentFocus();
            getACurrent(city);
        }
    }
    private void getACurrent(String city) {
        //mWeatherInfosLayout.removeAllViews();
        AForecastAPI aForecastAPI = RetrofitClient.getClient().create(AForecastAPI.class);

        Call<AForecast> aForecastCall = aForecastAPI.getAForecastInfo(city);
        aForecastCall.enqueue(new Callback<AForecast>() {
            @Override
            public void onResponse(Call<AForecast> call, Response<AForecast> response) {
                AForecast aForecast = response.body();
                try {
                    cityTV.setText(aForecast.getLocation().getName());
                    //countryTV.setText(aForecast.getLocation().getCountry());
                    /* timeTV.setText(aForecast.getLocation().getLocaltime());*/
                    timeTV.setText("Last update  "+aForecast.getCurrent().getLastUpdated());
                    howDayTV.setText(aForecast.getCurrent().getCondition().getText());
                    Glide.with(MainActivity.this).load(aForecast.getCurrent().getCondition().getIcon()).into(howDayImg);
                    highTempTV.setText("⇧" + aForecast.getForecast().getForecastday().get(0).getDay().getMaxtempC());
                    lowTempTV.setText("⇩" + aForecast.getForecast().getForecastday().get(0).getDay().getMintempC());
                    //int i=((int)aForecast.getCurrent().getTempC().intValue());
                    tempTV.setText(""+i+aForecast.getCurrent().getTempC().intValue()+"°");
                    //tempTV.setText(i);

                    if (aForecast.getCurrent().getCondition().getText().equals("Sunny")) {
                        imageview.setImageResource(R.drawable.sunnyimage);

                    } else if (aForecast.getCurrent().getCondition().getText().equals("Clear")) {
                        imageview.setImageResource(R.drawable.sunnyimage);

                    } else if (aForecast.getCurrent().getCondition().getText().equals("Cloudy")) {
                        imageview.setImageResource(R.drawable.cloudyimage);

                    } else if (aForecast.getCurrent().getCondition().getText().equals("Rain")) {
                        imageview.setImageResource(R.drawable.rainimage);

                    } else if (aForecast.getCurrent().getCondition().getText().equals("Partly cloudy")) {
                        imageview.setImageResource(R.drawable.sky_loudy);

                    } else if (aForecast.getCurrent().getCondition().getText().equals("Patchy rain possible")) {
                        imageview.setImageResource(R.drawable.rainimage);

                    } else if (aForecast.getCurrent().getCondition().getText().equals("Patchy light rain with thunder")) {
                        imageview.setImageResource(R.drawable.rainandthunder);

                    } else if (aForecast.getCurrent().getCondition().getText().equals("heavy Rain")) {
                        imageview.setImageResource(R.drawable.rainimage);
                    } else if (aForecast.getCurrent().getCondition().getText().equals("Moderate rains at time")) {
                        imageview.setImageResource(R.drawable.rainye);

                    } else if (aForecast.getCurrent().getCondition().getText().equals("Light rain shower")) {
                        imageview.setImageResource(R.drawable.rainye);

                    } else if (aForecast.getCurrent().getCondition().getText().equals("Rain")) {
                        imageview.setImageResource(R.drawable.rainimage);

                    } else if (aForecast.getCurrent().getCondition().getText().equals("Partly cloudy")) {
                        imageview.setImageResource(R.drawable.partlycloudynight);
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this,"City not found please try with correct city name", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<AForecast> call, Throwable t) {

            }
        });
    }
}




