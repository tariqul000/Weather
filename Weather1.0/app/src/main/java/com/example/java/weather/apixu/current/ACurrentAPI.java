package com.example.java.weather.apixu.current;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Trainer on 9/17/2017.
 */

public interface ACurrentAPI {
    @GET("current.json?key=8793752fdd664e6d825125241172109&q=dhaka")

    Call<ACurrent> getACurentInfo();

    
}
