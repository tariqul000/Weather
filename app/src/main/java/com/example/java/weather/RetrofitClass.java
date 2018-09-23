package com.example.java.weather;

import android.support.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JAVA on 10/6/2017.
 */

public class RetrofitClass {
    @NonNull
    public static Retrofit getClient(){
        return new Retrofit.Builder()
                .baseUrl("http://api.apixu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
