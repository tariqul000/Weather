package com.example.java.weather;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.java.weather.apixu.current.ACurrent;
import com.example.java.weather.apixu.forecast.AForecast;
import com.example.java.weather.apixu.forecast.AForecastAPI;
import com.example.java.weather.apixu.forecast.RetrofitClient;

import java.util.Date;

import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
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
    //private TextView humidityTV;
    private TextView feelsTV;
    private TextView visibilityTV;
    private ImageView gif;
    GifImageView imageview;
    public static String city = null;
    static Dialog dialog;
    //private Weather weather;
    private ACurrent weather;
    private WeatherAPI weatherAPI;
    private Location location;

    Button btnsr, btnhu, btnfe, btnvi, btndr,btnprssure, btnprep ;
    SwipeRefreshLayout swipe;
   // ImageView howDayImg = (ImageView)findViewById(R.id.howdayImg);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        btnsr = (Button)findViewById(R.id.search_button) ;
        btnhu = (Button)findViewById(R.id.humidity) ;
        btnfe = (Button)findViewById(R.id.feels) ;
        btnvi = (Button)findViewById(R.id.visibility) ;
        btndr = (Button) findViewById(R.id.winddir);
        btnprssure = (Button) findViewById(R.id.pressure);
        btnprep = (Button) findViewById(R.id.prediction);

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
        btnhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Humidity.class);
                startActivity(i);
            }
        });
        btnfe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Feels.class);
                startActivity(i);
            }
        });
        btnvi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Visibility.class);
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

        btnprep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Priciption.class);
                startActivity(i);
            }
        });
        btnprssure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Pressure.class);
                startActivity(i);
            }
        });

        //for day and night
        //initViews();
        getTimeFromAndroid();
       // weather = new Weather();
       // getRetrofitObject();
        SharedPreferences weatherPreferen = PreferenceManager.getDefaultSharedPreferences(this);
        String checkCity = weatherPreferen.getString("city", "Dhaka");
        if (checkCity==null){

            Intent intent=new Intent(this,AddcityActivity.class);
            startActivity(intent);

        }else {
            //humidityTV=(TextView)findViewById(R.id.humidityTV);
            //feelsTV=(TextView) findViewById(R.id.feelsTV);
            //visibilityTV=(TextView)findViewById(R.id.visibilityTV);
           cityTV = (TextView) findViewById(R.id.cityTV);
            timeTV = (TextView) findViewById(R.id.timeTV);
            tempTV = (TextView) findViewById(R.id.tempTV);
            highTempTV = (TextView) findViewById(R.id.highTV);
            lowTempTV = (TextView) findViewById(R.id.lowTV);
            howDayTV = (TextView) findViewById(R.id.howdayTV);
            imageview = (GifImageView) findViewById(R.id.imageview);
           /* countryTV=(TextView)findViewById(R.id.countryTV);*/
           // gif=(ImageView)findViewById(R.id.cloudyIG);
            //imageview = (GifImageView) findViewById(R.id.cloudyIE);
            city=checkCity;
            getCurrentFocus();

            getACurrent(city);

        }
    }

    private void getTimeFromAndroid(){
        Date dt = new Date();
        hours = dt.getHours();
        min = dt.getMinutes();
        Log.d("getTime","time"+hours);
    }

//    void getRetrofitObject() {
//        final Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://api.apixu.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        WeatherAPI service = retrofit.create(WeatherAPI.class);
//
//        Call<Weather> call = service.getWeatherInfo();
//
//        Log.d("Api", call.request().url().toString());
//        call.enqueue(new Callback<Weather>() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onResponse(Call<Weather> call, Response<Weather> response) {
//                //Log.d("Api Location name",response.body().getLocation().getName());
//                Weather weather = response.body();
//                try {
//                    cityTV.setText(weather.getLocation().getName());
//
//               /* countryTV.setText(weather.getLocation().getCountry());*/
//                timeTV.setText("Update " + weather.getLocation().getLocaltime());
//                howDayTV.setText(weather.getCurrent().getCondition().getText());
//                tempTV.setText(weather.getCurrent().getTempC().intValue() + "°");
//                //int i =(int) weather.getCurrent().getTempC().intValue();
//                //tempTV.setText(""+i+"C");
//                }catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//               // humidityTV.setText("Humidity "+i+weather.getCurrent().getHumidity()+"%");
//                //feelsTV.setText("Feels "+j+weather.getCurrent().getFeelslikeC()+"%");
//               // visibilityTV.setText("Visible "+k+weather.getCurrent().getVisKm()+"Km");
//                highTempTV.setText("⇧" + weather.getForecast().getForecastday().get(0).getDay().getMaxtempC().intValue() + "°");
//                lowTempTV.setText("⇧" + weather.getForecast().getForecastday().get(0).getDay().getMintempC().intValue() + "°");
//
//
//                if (weather.getCurrent().getCondition().getText().equals("Sunny")) {
//                    imageview.setImageResource(R.drawable.sunnyimage);
//
//                } else if (weather.getCurrent().getCondition().getText().equals("Clear")) {
//                    imageview.setImageResource(R.drawable.sunnyimage);
//
//                } else if (weather.getCurrent().getCondition().getText().equals("Cloudy")) {
//                    imageview.setImageResource(R.drawable.cloudyimage);
//
//                } else if (weather.getCurrent().getCondition().getText().equals("Rain")) {
//                    imageview.setImageResource(R.drawable.rainimage);
//
//                } else if (weather.getCurrent().getCondition().getText().equals("Partly cloudy")) {
//                    imageview.setImageResource(R.drawable.sky_loudy);
//
//                }
//                else if (weather.getCurrent().getCondition().getText().equals("Patchy rain possible")) {
//                imageview.setImageResource(R.drawable.rainimage);
//
//            }
//                else if (weather.getCurrent().getCondition().getText().equals("Patchy light rain with thunder")) {
//                    imageview.setImageResource(R.drawable.rainandthunder);
//
//                }
//
//               else if (weather.getCurrent().getCondition().getText().equals("heavy Rain")) {
//                        imageview.setImageResource(R.drawable.rainimage);
//                }else if (weather.getCurrent().getCondition().getText().equals("Moderate rains at time")) {
//                    imageview.setImageResource(R.drawable.rainye);
//
//             }
//             else if (weather.getCurrent().getCondition().getText().equals("Light rain shower")) {
//                    imageview.setImageResource(R.drawable.rainye);
//
//                }/* else if (weather.getCurrent().getCondition().getText().equals("Rain")) {
//                    imageview.setImageResource(R.drawable.rainimage);
//
//                } else if (weather.getCurrent().getCondition().getText().equals("Partly cloudy")) {
//                    imageview.setImageResource(R.drawable.partlycloudynight);
//                }*/
//            }
//            @Override
//            public void onFailure(Call<Weather> call, Throwable t) {
//
//            }
//        });
//    }

    private void getACurrent(String city) {
        AForecastAPI aForecastAPI = RetrofitClient.getClient().create(AForecastAPI.class);

        Call<AForecast> aForecastCall = aForecastAPI.getAForecastInfo(city);
        aForecastCall.enqueue(new Callback<AForecast>() {
            @Override
            public void onResponse(Call<AForecast> call, Response<AForecast> response) {
                AForecast aForecast = response.body();
                cityTV.setText(aForecast.getLocation().getName());
                timeTV.setText("Update  : "+aForecast.getLocation().getLocaltime());
                howDayTV.setText(aForecast.getCurrent().getCondition().getText());
               // Glide.with(MainActivity.this).load(aForecast.getCurrent().getCondition().getIcon()).into(howDayImg);
                highTempTV.setText("Max " + aForecast.getForecast().getForecastday().get(0).getDay().getMaxtempC());
                lowTempTV.setText("Min  " + aForecast.getForecast().getForecastday().get(0).getDay().getMintempC());
                tempTV.setText(""+aForecast.getCurrent().getTempC().intValue()+"°");


            }

            @Override
            public void onFailure(Call<AForecast> call, Throwable t) {

            }
        });
    }

}

