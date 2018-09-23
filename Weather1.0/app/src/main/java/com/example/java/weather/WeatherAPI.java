package com.example.java.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by JAVA on 10/6/2017.
 */

public interface WeatherAPI {
    @GET("v1/forecast.json?key=6a59de1d05724a3aa52110539170310&q=dhaka")

    Call<Weather>getWeatherInfo();

    retrofit.Call<Forecast> getWeatherInfo(String city);

    @GET("forecast.json?key=8793752fdd664e6d825125241172109")
    Call<WeatherAPI> getAForecastInfo(@Query("q")String city);

    @GET("forecast.json?key=8793752fdd664e6d825125241172109")
    Call<WeatherAPI> getAForecastInfo(@Query("q")String city,@Query("days")Integer day);



}
