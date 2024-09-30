package com.cust.weather.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cust.weather.response.WeatherInfo;
import com.cust.weather.service.UserService;
import com.cust.weather.service.WeatherInfoService;

public class WeatherControllerTest {

    @Mock
    private WeatherInfoService weatherService;

    @Mock
    private UserService userService;

    @InjectMocks
    private WeatherController weatherController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeatherInfo_Success() {
        Long postalCode = 12345L;
        String userName = "testUser";
        WeatherInfo weatherInfo = new WeatherInfo();

        when(userService.getUser(userName)).thenReturn(userName);
        when(weatherService.getWeatherInfo(postalCode)).thenReturn(weatherInfo);

        ResponseEntity<WeatherInfo> response = weatherController.getWeatherInfo(postalCode, userName);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(weatherInfo, response.getBody());
    }

    @Test
    public void testGetWeatherInfo_Unauthorized() {
        Long postalCode = 12345L;
        String userName = "testUser";

        when(userService.getUser(userName)).thenReturn(null);

        ResponseEntity<WeatherInfo> response = weatherController.getWeatherInfo(postalCode, userName);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void testGetWeatherInfo_BadRequest() {
        Long postalCode = null;
        String userName = "testUser";

        ResponseEntity<WeatherInfo> response = weatherController.getWeatherInfo(postalCode, userName);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetWeatherInfo_InternalServerError() {
        Long postalCode = 12345L;
        String userName = "testUser";

        when(userService.getUser(userName)).thenReturn(userName);
        when(weatherService.getWeatherInfo(postalCode)).thenReturn(null);

        ResponseEntity<WeatherInfo> response = weatherController.getWeatherInfo(postalCode, userName);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}