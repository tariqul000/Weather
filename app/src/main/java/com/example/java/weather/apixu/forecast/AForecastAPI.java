package com.example.java.weather.apixu.forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Trainer on 9/17/2017.
 */

public interface AForecastAPI {
    @GET("forecast.json?key=8793752fdd664e6d825125241172109&q=dhaka")
    Call<AForecast> getAForecastInfo();
    @GET("forecast.json?key=8793752fdd664e6d825125241172109")
    Call<AForecast> getAForecastInfo(@Query("q") String city);

    @GET("forecast.json?key=8793752fdd664e6d825125241172109")
    Call<AForecast> getAForecastInfo(@Query("q") String city, @Query("days") Integer day);




    
}
