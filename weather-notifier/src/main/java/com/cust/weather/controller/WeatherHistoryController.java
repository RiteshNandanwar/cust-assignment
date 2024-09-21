package com.cust.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cust.weather.entity.Weather;
import com.cust.weather.service.UserService;
import com.cust.weather.service.WeatherInfoService;

@RestController
public class WeatherHistoryController {

	@Autowired
	private WeatherInfoService weatherService;

	@Autowired
	private UserService userService;

	@GetMapping("app/history")
	public ResponseEntity<List<Weather>> getHistory(
			@RequestParam(name = "postalCode", required = false) String postalCode,
			@RequestParam(name = "user", required = false) String user) {

		if (postalCode != null) {
			return ResponseEntity.ok(weatherService.getWeatherHistoryByPostalCode(postalCode));
		} else if (user != null) {
			return ResponseEntity.ok(userService.getHistoryByUser(user));
		} else {
			// Handle the case where neither postalCode nor user is provided
			return ResponseEntity.badRequest().build();
		}
	}
}
