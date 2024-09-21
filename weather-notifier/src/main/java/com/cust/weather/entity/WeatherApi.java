package com.cust.weather.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeatherApi {

	@Value("${weather.url}")
	private String weatherUrl;
	
	@Value("${weather.appkey}")
	private String weatherApiAppKey;

	public String getWeatherUrl() {
		return weatherUrl;
	}

	public void setWeatherUrl(String weatherUrl) {
		this.weatherUrl = weatherUrl;
	}

	public String getWeatherApiAppKey() {
		return weatherApiAppKey;
	}

	public void setWeatherApiAppKey(String weatherApiAppKey) {
		this.weatherApiAppKey = weatherApiAppKey;
	}
	
}
