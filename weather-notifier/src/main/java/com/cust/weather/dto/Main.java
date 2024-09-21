package com.cust.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {
	 @JsonProperty("temp")
	   double temp;

	   @JsonProperty("feels_like")
	   int feelsLike;

	   @JsonProperty("temp_min")
	   double tempMin;

	   @JsonProperty("temp_max")
	   double tempMax;

	   @JsonProperty("pressure")
	   int pressure;

	   @JsonProperty("humidity")
	   int humidity;

	   @JsonProperty("sea_level")
	   int seaLevel;

	   @JsonProperty("grnd_level")
	   int grndLevel;


	    public void setTemp(double temp) {
	        this.temp = temp;
	    }
	    public double getTemp() {
	        return temp;
	    }
	    
	    public void setFeelsLike(int feelsLike) {
	        this.feelsLike = feelsLike;
	    }
	    public int getFeelsLike() {
	        return feelsLike;
	    }
	    
	    public void setTempMin(double tempMin) {
	        this.tempMin = tempMin;
	    }
	    public double getTempMin() {
	        return tempMin;
	    }
	    
	    public void setTempMax(double tempMax) {
	        this.tempMax = tempMax;
	    }
	    public double getTempMax() {
	        return tempMax;
	    }
	    
	    public void setPressure(int pressure) {
	        this.pressure = pressure;
	    }
	    public int getPressure() {
	        return pressure;
	    }
	    
	    public void setHumidity(int humidity) {
	        this.humidity = humidity;
	    }
	    public int getHumidity() {
	        return humidity;
	    }
	    
	    public void setSeaLevel(int seaLevel) {
	        this.seaLevel = seaLevel;
	    }
	    public int getSeaLevel() {
	        return seaLevel;
	    }
	    
	    public void setGrndLevel(int grndLevel) {
	        this.grndLevel = grndLevel;
	    }
	    public int getGrndLevel() {
	        return grndLevel;
	    }
	    
}
