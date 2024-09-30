package com.cust.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cust.weather.entity.Weather;
import com.cust.weather.service.UserService;
import com.cust.weather.service.WeatherInfoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@Tag(name = "Weather History Controller", description = "Operations pertaining to weather history")
public class WeatherHistoryController {

    @Autowired
    private WeatherInfoService weatherService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get weather history by postal code or user", description = "Fetch weather history for a given postal code or user.")
    @GetMapping("app/history")
    public ResponseEntity<List<Weather>> getHistory(
            @Parameter(description = "Postal code for which weather history is requested", required = false) @RequestParam(name = "postalCode", required = false) String postalCode,
            @Parameter(description = "User for which weather history is requested", required = false) @RequestParam(name = "user", required = false) String user) {

        if (postalCode != null) {
            return ResponseEntity.ok(weatherService.getWeatherHistoryByPostalCode(postalCode));
        } else if (user != null) {
            return ResponseEntity.ok(userService.getHistoryByUser(user));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}