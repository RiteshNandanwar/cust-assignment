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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/app")
@Tag(name = "Weather Controller", description = "Operations pertaining to weather information")
public class WeatherController {

    @Autowired
    private WeatherInfoService weatherService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get weather information based on zip code", description = "Fetch weather information for a given zip code.")
    @GetMapping("/weather/{zipCode}")
    public ResponseEntity<WeatherInfo> getWeatherInfo(
            @Parameter(description = "Zip code for which weather information is requested", required = true) @PathVariable Long postalCode,
            @Parameter(description = "Name of the user making the request", required = true) @RequestParam String userName) {
        if (userName == null || userName.isEmpty() || !userName.equals(userService.getUser(userName))) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (postalCode == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        WeatherInfo weatherInfo = weatherService.getWeatherInfo(postalCode);
        return weatherInfo != null ? new ResponseEntity<>(weatherInfo, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}