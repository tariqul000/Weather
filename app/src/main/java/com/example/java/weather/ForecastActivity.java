package com.example.java.weather;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
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

public class ForecastActivity extends AppCompatActivity{
    TextView dayTVrow0, highTempTVrow0, lowTempTVrow0,
            dayTVrow1, highTempTVrow1, lowTempTVrow1,
            dayTVrow2, highTempTVrow2, lowTempTVrow2,
            dayTVrow3, highTempTVrow3, lowTempTVrow3,
            dayTVrow4, highTempTVrow4, lowTempTVrow4,
            dayTVrow5, highTempTVrow5, lowTempTVrow5,
            dayTVrow6, highTempTVrow6, lowTempTVrow6,

            cityTV, timeTV,

            hourTV0, hourTempTV0,
            hourTV1, hourTempTV1,
            hourTV2, hourTempTV2,
            hourTV3, hourTempTV3,
            hourTV4, hourTempTV4,
            hourTV5, hourTempTV5,
            hourTV6, hourTempTV6,
            hourTV7, hourTempTV7,
            hourTV8, hourTempTV8,
            hourTV9, hourTempTV9,
            hourTV10, hourTempTV10,
            hourTV11, hourTempTV11,
            hourTV12, hourTempTV12,
            hourTV13, hourTempTV13,
            hourTV14, hourTempTV14,
            hourTV15, hourTempTV15,
            hourTV16, hourTempTV16,
            hourTV17, hourTempTV17,
            hourTV18, hourTempTV18,
            hourTV19, hourTempTV19,
            hourTV20, hourTempTV20,
            hourTV21, hourTempTV21,
            hourTV22, hourTempTV22,
            hourTV23, hourTempTV23;

    ImageView dayImg_row0,
            dayImg_row1,
            dayImg_row2,
            dayImg_row3,
            dayImg_row4,
            dayImg_row5,
            dayImg_row6,

            hourImg0,
            hourImg1,
            hourImg2,
            hourImg3,
            hourImg4,
            hourImg5,
            hourImg6,
            hourImg7,
            hourImg8,
            hourImg9,
            hourImg10,
            hourImg11,
            hourImg12,
            hourImg13,
            hourImg14,
            hourImg15,
            hourImg16,
            hourImg17,
            hourImg18,
            hourImg19,
            hourImg20,
            hourImg21,
            hourImg22,
            hourImg23;

    private String cit;
    public static String city = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String checkCity = preferences.getString("city", null);

        cityTV = (TextView) findViewById(R.id.cityTV);
        timeTV = (TextView) findViewById(R.id.timeTV);

        hourTV0 = (TextView) findViewById(R.id.hourTV0);
        hourTV1 = (TextView) findViewById(R.id.hourTV1);
        hourTV2 = (TextView) findViewById(R.id.hourTV2);
        hourTV3 = (TextView) findViewById(R.id.hourTV3);
        hourTV4 = (TextView) findViewById(R.id.hourTV4);
        hourTV5 = (TextView) findViewById(R.id.hourTV5);
        hourTV6 = (TextView) findViewById(R.id.hourTV6);
        hourTV7 = (TextView) findViewById(R.id.hourTV7);
        hourTV8 = (TextView) findViewById(R.id.hourTV8);
        hourTV9 = (TextView) findViewById(R.id.hourTV9);
        hourTV10 = (TextView) findViewById(R.id.hourTV10);
        hourTV11 = (TextView) findViewById(R.id.hourTV11);
        hourTV12 = (TextView) findViewById(R.id.hourTV12);
        hourTV13 = (TextView) findViewById(R.id.hourTV13);
        hourTV14 = (TextView) findViewById(R.id.hourTV14);
        hourTV15 = (TextView) findViewById(R.id.hourTV15);
        hourTV16 = (TextView) findViewById(R.id.hourTV16);
        hourTV17 = (TextView) findViewById(R.id.hourTV17);
        hourTV18 = (TextView) findViewById(R.id.hourTV18);
        hourTV19 = (TextView) findViewById(R.id.hourTV19);
        hourTV20 = (TextView) findViewById(R.id.hourTV20);
        hourTV21 = (TextView) findViewById(R.id.hourTV21);
        hourTV22 = (TextView) findViewById(R.id.hourTV22);
        hourTV23 = (TextView) findViewById(R.id.hourTV23);

        hourTempTV0 = (TextView) findViewById(R.id.hourTempTV0);
        hourTempTV1 = (TextView) findViewById(R.id.hourTempTV1);
        hourTempTV2 = (TextView) findViewById(R.id.hourTempTV2);
        hourTempTV3 = (TextView) findViewById(R.id.hourTempTV3);
        hourTempTV4 = (TextView) findViewById(R.id.hourTempTV4);
        hourTempTV5 = (TextView) findViewById(R.id.hourTempTV5);
        hourTempTV6 = (TextView) findViewById(R.id.hourTempTV6);
        hourTempTV7 = (TextView) findViewById(R.id.hourTempTV7);
        hourTempTV8 = (TextView) findViewById(R.id.hourTempTV8);
        hourTempTV9 = (TextView) findViewById(R.id.hourTempTV9);
        hourTempTV10 = (TextView) findViewById(R.id.hourTempTV10);
        hourTempTV11 = (TextView) findViewById(R.id.hourTempTV11);
        hourTempTV12 = (TextView) findViewById(R.id.hourTempTV12);
        hourTempTV13 = (TextView) findViewById(R.id.hourTempTV13);
        hourTempTV14 = (TextView) findViewById(R.id.hourTempTV14);
        hourTempTV15 = (TextView) findViewById(R.id.hourTempTV15);
        hourTempTV16 = (TextView) findViewById(R.id.hourTempTV16);
        hourTempTV17 = (TextView) findViewById(R.id.hourTempTV17);
        hourTempTV18 = (TextView) findViewById(R.id.hourTempTV18);
        hourTempTV19 = (TextView) findViewById(R.id.hourTempTV19);
        hourTempTV20 = (TextView) findViewById(R.id.hourTempTV20);
        hourTempTV21 = (TextView) findViewById(R.id.hourTempTV21);
        hourTempTV22 = (TextView) findViewById(R.id.hourTempTV22);
        hourTempTV23 = (TextView) findViewById(R.id.hourTempTV23);

        hourImg0 = (ImageView) findViewById(R.id.hourImg0);
        hourImg1 = (ImageView) findViewById(R.id.hourImg1);
        hourImg2 = (ImageView) findViewById(R.id.hourImg2);
        hourImg3 = (ImageView) findViewById(R.id.hourImg3);
        hourImg4 = (ImageView) findViewById(R.id.hourImg4);
        hourImg5 = (ImageView) findViewById(R.id.hourImg5);
        hourImg6 = (ImageView) findViewById(R.id.hourImg6);
        hourImg7 = (ImageView) findViewById(R.id.hourImg7);
        hourImg8 = (ImageView) findViewById(R.id.hourImg8);
        hourImg9 = (ImageView) findViewById(R.id.hourImg9);
        hourImg10 = (ImageView) findViewById(R.id.hourImg10);
        hourImg11 = (ImageView) findViewById(R.id.hourImg11);
        hourImg12 = (ImageView) findViewById(R.id.hourImg12);
        hourImg13 = (ImageView) findViewById(R.id.hourImg13);
        hourImg14 = (ImageView) findViewById(R.id.hourImg14);
        hourImg15 = (ImageView) findViewById(R.id.hourImg15);
        hourImg16 = (ImageView) findViewById(R.id.hourImg16);
        hourImg17 = (ImageView) findViewById(R.id.hourImg17);
        hourImg18 = (ImageView) findViewById(R.id.hourImg18);
        hourImg19 = (ImageView) findViewById(R.id.hourImg19);
        hourImg20 = (ImageView) findViewById(R.id.hourImg20);
        hourImg21 = (ImageView) findViewById(R.id.hourImg21);
        hourImg22 = (ImageView) findViewById(R.id.hourImg22);
        hourImg23 = (ImageView) findViewById(R.id.hourImg23);

        dayTVrow0 = (TextView) findViewById(R.id.dayTVrow0);
        dayTVrow1 = (TextView) findViewById(R.id.dayTVrow1);
        dayTVrow2 = (TextView) findViewById(R.id.dayTVrow2);
        dayTVrow3 = (TextView) findViewById(R.id.dayTVrow3);
        dayTVrow4 = (TextView) findViewById(R.id.dayTVrow4);
        dayTVrow5 = (TextView) findViewById(R.id.dayTVrow5);
        dayTVrow6 = (TextView) findViewById(R.id.dayTVrow6);

        highTempTVrow0 = (TextView) findViewById(R.id.highTempTVrow0);
        highTempTVrow1 = (TextView) findViewById(R.id.highTempTVrow1);
        highTempTVrow2 = (TextView) findViewById(R.id.highTempTVrow2);
        highTempTVrow3 = (TextView) findViewById(R.id.highTempTVrow3);
        highTempTVrow4 = (TextView) findViewById(R.id.highTempTVrow4);
        highTempTVrow5 = (TextView) findViewById(R.id.highTempTVrow5);
        highTempTVrow6 = (TextView) findViewById(R.id.highTempTVrow6);

        lowTempTVrow0 = (TextView) findViewById(R.id.lowTempTVrow0);
        lowTempTVrow1 = (TextView) findViewById(R.id.lowTempTVrow1);
        lowTempTVrow2 = (TextView) findViewById(R.id.lowTempTVrow2);
        lowTempTVrow3 = (TextView) findViewById(R.id.lowTempTVrow3);
        lowTempTVrow4 = (TextView) findViewById(R.id.lowTempTVrow4);
        lowTempTVrow5 = (TextView) findViewById(R.id.lowTempTVrow5);
        lowTempTVrow6 = (TextView) findViewById(R.id.lowTempTVrow6);

        dayImg_row0 = (ImageView) findViewById(R.id.dayImg_row0);
        dayImg_row1 = (ImageView) findViewById(R.id.dayImg_row1);
        dayImg_row2 = (ImageView) findViewById(R.id.dayImg_row2);
        dayImg_row3 = (ImageView) findViewById(R.id.dayImg_row3);
        dayImg_row4 = (ImageView) findViewById(R.id.dayImg_row4);
        dayImg_row5 = (ImageView) findViewById(R.id.dayImg_row5);
        dayImg_row6 = (ImageView) findViewById(R.id.dayImg_row6);

        getCurrentFocus();
        city=checkCity;
        getCurrentDetails(city);

    }

    private void getCurrentDetails(String city) {
        AForecastAPI aForecastAPI = RetrofitClient.getClient().create(AForecastAPI.class);
        retrofit2.Call<AForecast> aForecastCall = aForecastAPI.getAForecastInfo(city);
        aForecastCall.enqueue(new retrofit2.Callback<AForecast>() {
            @Override
            public void onResponse(retrofit2.Call<AForecast> call, retrofit2.Response<AForecast> response) {
                AForecast aForecast=response.body();
                Log.d("DayOfDate",call.request().url()+" "+aForecast.getForecast().getForecastday().get(0).getDate()+" "+response.raw().toString());
                try {
                    cityTV.setText(aForecast.getLocation().getName());
                    timeTV.setText(aForecast.getLocation().getLocaltime());

                    hourTV0.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(0).getTime());
                    hourTV1.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(1).getTime());
                    hourTV2.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(2).getTime());
                    hourTV3.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(3).getTime());
                    hourTV4.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(4).getTime());
                    hourTV5.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(5).getTime());
                    hourTV6.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(6).getTime());
                    hourTV7.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(7).getTime());
                    hourTV8.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(8).getTime());
                    hourTV9.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(9).getTime());
                    hourTV10.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(10).getTime());
                    hourTV11.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(11).getTime());
                    hourTV12.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(12).getTime());
                    hourTV13.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(13).getTime());
                    hourTV14.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(14).getTime());
                    hourTV15.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(15).getTime());
                    hourTV16.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(16).getTime());
                    hourTV17.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(17).getTime());
                    hourTV18.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(18).getTime());
                    hourTV19.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(19).getTime());
                    hourTV20.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(20).getTime());
                    hourTV21.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(21).getTime());
                    hourTV22.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(22).getTime());
                    hourTV23.setText(aForecast.getForecast().getForecastday().get(0).getHour().get(23).getTime());

                    hourTempTV0.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(0).getTempC()));
                    hourTempTV1.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(1).getTempC()));
                    hourTempTV2.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(2).getTempC()));
                    hourTempTV3.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(3).getTempC()));
                    hourTempTV4.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(4).getTempC()));
                    hourTempTV5.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(5).getTempC()));
                    hourTempTV6.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(6).getTempC()));
                    hourTempTV7.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(7).getTempC()));
                    hourTempTV8.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(8).getTempC()));
                    hourTempTV9.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(9).getTempC()));
                    hourTempTV10.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(10).getTempC()));
                    hourTempTV11.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(11).getTempC()));
                    hourTempTV12.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(12).getTempC()));
                    hourTempTV13.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(13).getTempC()));
                    hourTempTV14.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(14).getTempC()));
                    hourTempTV15.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(15).getTempC()));
                    hourTempTV16.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(16).getTempC()));
                    hourTempTV17.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(17).getTempC()));
                    hourTempTV18.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(18).getTempC()));
                    hourTempTV19.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(19).getTempC()));
                    hourTempTV20.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(20).getTempC()));
                    hourTempTV21.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(21).getTempC()));
                    hourTempTV22.setText((int) (+aForecast.getForecast().getForecastday().get(0).getHour().get(22).getTempC()));

                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(0).getCondition().getIcon()).into(hourImg0);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(1).getCondition().getIcon()).into(hourImg1);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(2).getCondition().getIcon()).into(hourImg2);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(3).getCondition().getIcon()).into(hourImg3);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(4).getCondition().getIcon()).into(hourImg4);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(5).getCondition().getIcon()).into(hourImg5);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(6).getCondition().getIcon()).into(hourImg6);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(7).getCondition().getIcon()).into(hourImg7);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(8).getCondition().getIcon()).into(hourImg8);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(9).getCondition().getIcon()).into(hourImg9);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(10).getCondition().getIcon()).into(hourImg10);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(11).getCondition().getIcon()).into(hourImg11);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(12).getCondition().getIcon()).into(hourImg12);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(13).getCondition().getIcon()).into(hourImg13);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(14).getCondition().getIcon()).into(hourImg14);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(15).getCondition().getIcon()).into(hourImg15);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(16).getCondition().getIcon()).into(hourImg16);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(17).getCondition().getIcon()).into(hourImg17);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(18).getCondition().getIcon()).into(hourImg18);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(19).getCondition().getIcon()).into(hourImg19);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(20).getCondition().getIcon()).into(hourImg20);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(21).getCondition().getIcon()).into(hourImg21);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(22).getCondition().getIcon()).into(hourImg22);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().getForecastday().get(0).getHour().get(23).getCondition().getIcon()).into(hourImg23);



                    dayTVrow0.setText("WED"+aForecast.getForecast().getForecastday().get(0).getDate());
                    dayTVrow1.setText("THU"+aForecast.getForecast().getForecastday().get(1).getDate());
                    dayTVrow2.setText("FRI"+aForecast.getForecast().getForecastday().get(2).getDate());
                    dayTVrow3.setText("SAT"+aForecast.getForecast().getForecastday().get(3).getDate());
                    dayTVrow4.setText("SUN"+aForecast.getForecast().getForecastday().get(4).getDate());
                    dayTVrow5.setText("MON"+aForecast.getForecast().getForecastday().get(5).getDate());
                    dayTVrow6.setText("THE"+aForecast.getForecast().getForecastday().get(6).getDate());
                    //right
                    highTempTVrow0.setText(""+aForecast.getForecast().getForecastday().get(0).getDay().getMaxtempC());
                    highTempTVrow1.setText(""+aForecast.getForecast().getForecastday().get(1).getDay().getMaxtempC());
                    highTempTVrow2.setText(""+aForecast.getForecast().getForecastday().get(2).getDay().getMaxtempC());
                    highTempTVrow3.setText(""+aForecast.getForecast().getForecastday().get(3).getDay().getMaxtempC());
                    highTempTVrow4.setText(""+aForecast.getForecast().getForecastday().get(4).getDay().getMaxtempC());
                    highTempTVrow5.setText(""+aForecast.getForecast().getForecastday().get(5).getDay().getMaxtempC());
                    highTempTVrow6.setText(""+aForecast.getForecast().getForecastday().get(6).getDay().getMaxtempC());

                    lowTempTVrow0.setText(""+aForecast.getForecast().getForecastday().get(0).getDay().getMintempC());
                    lowTempTVrow1.setText(""+aForecast.getForecast().getForecastday().get(1).getDay().getMintempC());
                    lowTempTVrow2.setText(""+aForecast.getForecast().getForecastday().get(2).getDay().getMintempC());
                    lowTempTVrow3.setText(""+aForecast.getForecast().getForecastday().get(3).getDay().getMintempC());
                    lowTempTVrow4.setText(""+aForecast.getForecast().getForecastday().get(4).getDay().getMintempC());
                    lowTempTVrow5.setText(""+aForecast.getForecast().getForecastday().get(5).getDay().getMintempC());
                    lowTempTVrow6.setText(""+aForecast.getForecast().getForecastday().get(6).getDay().getMintempC());
                    //right
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().forecastday.get(0).getDay().getCondition().getIcon()).into(dayImg_row0);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().forecastday.get(1).getDay().getCondition().getIcon()).into(dayImg_row1);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().forecastday.get(2).getDay().getCondition().getIcon()).into(dayImg_row2);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().forecastday.get(3).getDay().getCondition().getIcon()).into(dayImg_row3);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().forecastday.get(4).getDay().getCondition().getIcon()).into(dayImg_row4);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().forecastday.get(5).getDay().getCondition().getIcon()).into(dayImg_row5);
                    Glide.with(ForecastActivity.this).load(aForecast.getForecast().forecastday.get(6).getDay().getCondition().getIcon()).into(dayImg_row6);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(retrofit2.Call<AForecast> call, Throwable t) {

            }
        });
    }
}




