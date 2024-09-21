package com.cust.weather.response;

import java.io.Serializable;

public class WeatherInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private double temp;
	
	private int feelsLike;
	
	private double tempMin;
	
	private double tempMax;
	
	private int pressure;
	
	private int humidity;
	
	private int visibility;

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public int getFeelsLike() {
		return feelsLike;
	}

	public void setFeelsLike(int feelsLike) {
		this.feelsLike = feelsLike;
	}

	public double getTempMin() {
		return tempMin;
	}

	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}

	public double getTempMax() {
		return tempMax;
	}

	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}

	public int getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}


