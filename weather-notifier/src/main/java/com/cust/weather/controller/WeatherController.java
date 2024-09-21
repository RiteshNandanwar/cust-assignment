package com.cust.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cust.weather.response.WeatherInfo;
import com.cust.weather.service.UserService;
import com.cust.weather.service.WeatherInfoService;

@RestController
@RequestMapping("/app")
public class WeatherController {

	@Autowired
	private WeatherInfoService weatherService;

	@Autowired
	private UserService userService;

	/**
	 * Endpoint to get weather information based on zip code.
	 *
	 * @param postalCode the zip code for which weather information is requested
	 * @param userName the name of the user making the request
	 * @return ResponseEntity containing WeatherInfo and HTTP status
	 */
	@GetMapping("/weather/{zipCode}")
	public ResponseEntity<WeatherInfo> getWeatherInfo(@PathVariable Long postalCode, @RequestParam String userName) {
		// Validate userName
		if (userName == null || userName.isEmpty() || !userName.equals(userService.getUser(userName))) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		// Validate zipCode
		if (postalCode == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		// Fetch weather information using the service
		WeatherInfo weatherInfo = weatherService.getWeatherInfo(postalCode);

		// Return the weather information with HTTP status OK or INTERNAL_SERVER_ERROR
		return weatherInfo != null ? new ResponseEntity<>(weatherInfo, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}