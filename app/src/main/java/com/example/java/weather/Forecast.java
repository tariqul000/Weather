
package com.example.java.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Forecast implements Serializable{

    private static final long serialVersionUID = 1L;

    @SerializedName("forecastday")
    public ArrayList<Forecastday> forecastday;

    public ArrayList<Forecastday> getForecastday()
    {
        return forecastday;
    }
    public void setForecastday(ArrayList<Forecastday> mForecastday)
    {
        this.forecastday = mForecastday;
    }


}