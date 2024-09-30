package com.cust.weather.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cust.weather.entity.Weather;
import com.cust.weather.service.UserService;
import com.cust.weather.service.WeatherInfoService;

public class WeatherHistoryControllerTest {

    @Mock
    private WeatherInfoService weatherService;

    @Mock
    private UserService userService;

    @InjectMocks
    private WeatherHistoryController weatherHistoryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetHistory_ByPostalCode() {
        String postalCode = "12345";
        List<Weather> weatherList = List.of(new Weather());

        when(weatherService.getWeatherHistoryByPostalCode(postalCode)).thenReturn(weatherList);

        ResponseEntity<List<Weather>> response = weatherHistoryController.getHistory(postalCode, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(weatherList, response.getBody());
    }

    @Test
    public void testGetHistory_ByUser() {
        String user = "testUser";
        List<Weather> weatherList = List.of(new Weather());

        when(userService.getHistoryByUser(user)).thenReturn(weatherList);

        ResponseEntity<List<Weather>> response = weatherHistoryController.getHistory(null, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(weatherList, response.getBody());
    }

    @Test
    public void testGetHistory_BadRequest() {
        ResponseEntity<List<Weather>> response = weatherHistoryController.getHistory(null, null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}