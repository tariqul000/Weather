
package com.example.java.weather.apixu.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Day {

    @SerializedName("maxtemp_c")
    @Expose
    private Double maxtempC;
    @SerializedName("maxtemp_f")
    @Expose
    private Double maxtempF;
    @SerializedName("mintemp_c")
    @Expose
    private Double mintempC;
    @SerializedName("mintemp_f")
    @Expose
    private Double mintempF;
    @SerializedName("avgtemp_c")
    @Expose
    private Double avgtempC;
    @SerializedName("avgtemp_f")
    @Expose
    private Double avgtempF;
    @SerializedName("maxwind_mph")
    @Expose
    private Double maxwindMph;
    @SerializedName("maxwind_kph")
    @Expose
    private Double maxwindKph;
    @SerializedName("totalprecip_mm")
    @Expose
    private Double totalprecipMm;
    @SerializedName("totalprecip_in")
    @Expose
    private Double totalprecipIn;
    @SerializedName("avgvis_km")
    @Expose
    private Double avgvisKm;
    @SerializedName("avgvis_miles")
    @Expose
    private Double avgvisMiles;
    @SerializedName("avghumidity")
    @Expose
    private Double avghumidity;
    @SerializedName("condition")
    @Expose
    private Condition_ condition;
    @SerializedName("uv")
    @Expose
    private Double uv;

    public String getMaxtempC() {

        int i = maxtempC.intValue();
        String maxTempString =String.valueOf(i);
        return maxTempString+"°";
    }

    public void setMaxtempC(Double maxtempC) {
        this.maxtempC = maxtempC;
    }

    public String getMaxtempF() {
        int i = maxtempF.intValue();
        String maxTempString =String.valueOf(i);
        return maxTempString+"°";
    }

    public void setMaxtempF(Double maxtempF) {
        this.maxtempF = maxtempF;
    }

    public String getMintempC() {
        int i = mintempC.intValue();
        String minTempString =String.valueOf(i-2);
        return minTempString+"°";
    }

    public void setMintempC(Double mintempC) {
        this.mintempC = mintempC;
    }

    public String getMintempF() {
        int i = mintempF.intValue();
        String minTempString =String.valueOf(i-5);
        return minTempString+"°";
    }

    public void setMintempF(Double mintempF) {
        this.mintempF = mintempF;
    }

    public Double getAvgtempC() {
        return avgtempC;
    }

    public void setAvgtempC(Double avgtempC) {
        this.avgtempC = avgtempC;
    }

    public Double getAvgtempF() {
        return avgtempF;
    }

    public void setAvgtempF(Double avgtempF) {
        this.avgtempF = avgtempF;
    }

    public String getMaxwindMph() {

        int i = maxwindMph.intValue();
        String stringMaxWindMph = Integer.toString(i);
        return stringMaxWindMph;
    }

    public void setMaxwindMph(Double maxwindMph) {
        this.maxwindMph = maxwindMph;
    }

    public String getMaxwindKph() {
        int i = maxwindKph.intValue();
        String stringMaxWindKph = Integer.toString(i);
        return stringMaxWindKph;
    }

    public void setMaxwindKph(Double maxwindKph) {
        this.maxwindKph = maxwindKph;
    }

    public Double getTotalprecipMm() {
        return totalprecipMm;
    }

    public void setTotalprecipMm(Double totalprecipMm) {
        this.totalprecipMm = totalprecipMm;
    }

    public Double getTotalprecipIn() {
        return totalprecipIn;
    }

    public void setTotalprecipIn(Double totalprecipIn) {
        this.totalprecipIn = totalprecipIn;
    }

    public Double getAvgvisKm() {
        return avgvisKm;
    }

    public void setAvgvisKm(Double avgvisKm) {
        this.avgvisKm = avgvisKm;
    }

    public Double getAvgvisMiles() {
        return avgvisMiles;
    }

    public void setAvgvisMiles(Double avgvisMiles) {
        this.avgvisMiles = avgvisMiles;
    }

    public Double getAvghumidity() {
        return avghumidity;
    }

    public void setAvghumidity(Double avghumidity) {
        this.avghumidity = avghumidity;
    }

    public Condition_ getCondition() {
        return condition;
    }

    public void setCondition(Condition_ condition) {
        this.condition = condition;
    }

    public String getUv() {
        int i = uv.intValue();
        String stringUv = Integer.toString(i);

        return stringUv;
    }

    public void setUv(Double uv) {
        this.uv = uv;
    }

}
