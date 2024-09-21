package com.cust.weather.dto;

import java.util.List;

import com.cust.weather.entity.Weather;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfoDto {

	   @JsonProperty("coord")
	   Coord coord;

	   @JsonProperty("weather")
	   List<Weather> weather;

	   @JsonProperty("base")
	   String base;

	   @JsonProperty("main")
	   Main main;

	   @JsonProperty("visibility")
	   int visibility;

	   @JsonProperty("clouds")
	   Clouds clouds;

	   @JsonProperty("dt")
	   int dt;

	   @JsonProperty("timezone")
	   int timezone;

	   @JsonProperty("id")
	   int id;

	   @JsonProperty("name")
	   String name;

	   @JsonProperty("cod")
	   int cod;


	    public void setCoord(Coord coord) {
	        this.coord = coord;
	    }
	    public Coord getCoord() {
	        return coord;
	    }
	    
	    public void setWeather(List<Weather> weather) {
	        this.weather = weather;
	    }
	    public List<Weather> getWeather() {
	        return weather;
	    }
	    
	    public void setBase(String base) {
	        this.base = base;
	    }
	    public String getBase() {
	        return base;
	    }
	    
	    public void setMain(Main main) {
	        this.main = main;
	    }
	    public Main getMain() {
	        return main;
	    }
	    
	    public void setVisibility(int visibility) {
	        this.visibility = visibility;
	    }
	    public int getVisibility() {
	        return visibility;
	    }
	    
	    public void setClouds(Clouds clouds) {
	        this.clouds = clouds;
	    }
	    public Clouds getClouds() {
	        return clouds;
	    }
	    
	    public void setDt(int dt) {
	        this.dt = dt;
	    }
	    public int getDt() {
	        return dt;
	    }
	    
	    public void setTimezone(int timezone) {
	        this.timezone = timezone;
	    }
	    public int getTimezone() {
	        return timezone;
	    }
	    
	    public void setId(int id) {
	        this.id = id;
	    }
	    public int getId() {
	        return id;
	    }
	    
	    public void setName(String name) {
	        this.name = name;
	    }
	    public String getName() {
	        return name;
	    }
	    
	    public void setCod(int cod) {
	        this.cod = cod;
	    }
	    public int getCod() {
	        return cod;
	    }
	    
}
