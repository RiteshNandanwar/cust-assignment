package com.cust.weather.service;

import java.util.List;

import com.cust.weather.entity.Weather;
import com.cust.weather.response.WeatherInfo;

public interface WeatherInfoService {

	WeatherInfo getWeatherInfo(Long zipCode);
	
	List<Weather> getWeatherHistoryByPostalCode(String postalCode) ;

}
