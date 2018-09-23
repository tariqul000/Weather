package com.example.java.weather.apixu.forecast;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Trainer on 9/17/2017.
 */

public class RetrofitClient {

    public static Retrofit getClient(){
        return new Retrofit.Builder()
                .baseUrl("http://api.apixu.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
