package com.cust.weather.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.cust.weather.dto.WeatherInfoDto;
import com.cust.weather.entity.Weather;
import com.cust.weather.entity.WeatherApi;
import com.cust.weather.excetion.PostalCodeNotFoundException;
import com.cust.weather.repo.WeatherRepository;
import com.cust.weather.response.WeatherInfo;

import reactor.core.publisher.Mono;

public class WeatherInfoServiceImplTest {

	@Mock
	private WebClient webClient;

	@Mock
	private WeatherApi weatherApi;

	@Mock
	private WeatherRepository weatherRepository;

	@InjectMocks
	private WeatherInfoServiceImpl weatherInfoServiceImpl;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetWeatherInfo_Success() {
		Long zipCode = 12345L;
		String weatherUrl = "http://api.weather.com?zip=12345,us&appid=testKey";
		WeatherInfoDto weatherInfoDto = new WeatherInfoDto();
		WebClient.RequestHeadersUriSpec requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
		WebClient.RequestHeadersSpec requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
		WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);

		when(weatherApi.getWeatherUrl()).thenReturn("http://api.weather.com");
		when(weatherApi.getWeatherApiAppKey()).thenReturn("testKey");
		when(webClient.get()).thenReturn(requestHeadersUriSpec);
		when(requestHeadersUriSpec.uri(weatherUrl)).thenReturn(requestHeadersSpec);
		when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(WeatherInfoDto.class)).thenReturn(Mono.just(weatherInfoDto));

		WeatherInfo weatherInfo = weatherInfoServiceImpl.getWeatherInfo(zipCode);
		assertNotNull(weatherInfo);
		verify(weatherRepository, times(1)).save(any(Weather.class));
	}

	@Test
	public void testGetWeatherInfo_ClientError() {
		Long zipCode = 12345L;
		String weatherUrl = "http://api.weather.com?zip=12345,us&appid=testKey";
		WebClient.RequestHeadersUriSpec requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
		WebClient.RequestHeadersSpec requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
		WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);

		when(weatherApi.getWeatherUrl()).thenReturn("http://api.weather.com");
		when(weatherApi.getWeatherApiAppKey()).thenReturn("testKey");
		when(webClient.get()).thenReturn(requestHeadersUriSpec);
		when(requestHeadersUriSpec.uri(weatherUrl)).thenReturn(requestHeadersSpec);
		when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(WeatherInfoDto.class)).thenReturn(Mono.error(new WebClientResponseException(400, "Client error", null, null, null)));

		assertThrows(RuntimeException.class, () -> weatherInfoServiceImpl.getWeatherInfo(zipCode));
	}

	@Test
	public void testGetWeatherInfo_ServerError() {
		Long zipCode = 12345L;
		String weatherUrl = "http://api.weather.com?zip=12345,us&appid=testKey";
		WebClient.RequestHeadersUriSpec requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
		WebClient.RequestHeadersSpec requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
		WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);

		when(weatherApi.getWeatherUrl()).thenReturn("http://api.weather.com");
		when(weatherApi.getWeatherApiAppKey()).thenReturn("testKey");
		when(webClient.get()).thenReturn(requestHeadersUriSpec);
		when(requestHeadersUriSpec.uri(weatherUrl)).thenReturn(requestHeadersSpec);
		when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(WeatherInfoDto.class)).thenReturn(Mono.error(new WebClientResponseException(500, "Server error", null, null, null)));

		assertThrows(RuntimeException.class, () -> weatherInfoServiceImpl.getWeatherInfo(zipCode));
	}

	@Test
	public void testGetWeatherHistoryByPostalCode_Success() {
		String postalCode = "12345";
		List<Weather> weatherList = List.of(new Weather());
		when(weatherRepository.findByPostalCode(postalCode)).thenReturn(Optional.of(weatherList));

		List<Weather> result = weatherInfoServiceImpl.getWeatherHistoryByPostalCode(postalCode);
		assertEquals(weatherList, result);
	}

	@Test
	public void testGetWeatherHistoryByPostalCode_PostalCodeNotFoundException() {
		String postalCode = "12345";
		when(weatherRepository.findByPostalCode(postalCode)).thenReturn(Optional.empty());

		assertThrows(PostalCodeNotFoundException.class, () -> weatherInfoServiceImpl.getWeatherHistoryByPostalCode(postalCode));
	}
}