package com.cust.weather.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.cust.weather.dto.WeatherInfoDto;
import com.cust.weather.entity.Weather;
import com.cust.weather.entity.WeatherApi;
import com.cust.weather.excetion.PostalCodeNotFoundException;
import com.cust.weather.repo.WeatherRepository;
import com.cust.weather.response.WeatherInfo;
import com.cust.weather.service.WeatherInfoService;

import reactor.core.publisher.Mono;

@Service
public class WeatherInfoServiceImpl implements WeatherInfoService {

	@Autowired
	private WebClient webClient;

	@Autowired
	private WeatherApi weatherApi;

	@Autowired
	private WeatherRepository weatherRepository;

	/**
	 * Get weather information for a given zip code.
	 *
	 * @param zipCode the zip code for which to fetch weather information
	 * @return WeatherInfo the weather information
	 */
	@Override
	public WeatherInfo getWeatherInfo(Long zipCode) {
		// Construct the weather API URL
		String weatherUrl = weatherApi.getWeatherUrl() + "?zip=" + zipCode + ",us&appid=" + weatherApi.getWeatherApiAppKey();

		try {
			// Fetch weather information from the API
			WeatherInfoDto weatherInfoDto = webClient.get().uri(weatherUrl).retrieve()
					.onStatus(HttpStatusCode::is4xxClientError, response ->
							response.bodyToMono(String.class).flatMap(
									errorBody -> Mono.error(new RuntimeException("Client error occurred: " + errorBody))
							)
					).onStatus(HttpStatusCode::is5xxServerError, response ->
							response.bodyToMono(String.class).flatMap(
									errorBody -> Mono.error(new RuntimeException("Server error occurred: " + errorBody))
							)
					).onStatus(HttpStatus.INTERNAL_SERVER_ERROR::equals, response ->
							response.bodyToMono(String.class).flatMap(
									errorBody -> Mono.error(new RuntimeException("Internal server error: " + errorBody))
							)
					).bodyToMono(WeatherInfoDto.class).block(); // Blocking call since it's a synchronous call

			// Check if weatherInfoDto is null
			if (weatherInfoDto == null) {
				throw new RuntimeException("Failed to fetch weather information. Response is null.");
			}

			// Prepare and return the weather information response
			WeatherInfo weatherInfoResponse = prepareWeatherInfoResponse(weatherInfoDto);

			// Save response to the DB
			weatherRepository.save(prepareWeatherEntity(weatherInfoResponse));

			return weatherInfoResponse;

		} catch (WebClientResponseException ex) {
			// Handle errors like timeouts or unreachable APIs
			throw new RuntimeException("Error during weather API call: " + ex.getMessage(), ex);
		} catch (Exception ex) {
			// General exception handling
			throw new RuntimeException("Unexpected error occurred while fetching weather information: " + ex.getMessage(), ex);
		}
	}

	/**
	 * Prepare the Weather entity from WeatherInfo response.
	 *
	 * @param weatherInfoResponse the response object containing weather information
	 * @return Weather the weather entity
	 */
	private Weather prepareWeatherEntity(WeatherInfo weatherInfoResponse) {
		Weather weather = new Weather();

		if (weatherInfoResponse != null) {
			weather.setTemprature(weatherInfoResponse.getTemp());
			weather.setTempMin(weatherInfoResponse.getTempMin());
			weather.setTempMax(weatherInfoResponse.getTempMax());
			weather.setFeelsLike(weatherInfoResponse.getFeelsLike());
			weather.setHumidity(weatherInfoResponse.getHumidity());
			weather.setPressure(weatherInfoResponse.getPressure());
		}

		return weather;
	}

	/**
	 * Prepare the WeatherInfo response from WeatherInfoDto.
	 *
	 * @param weatherInfoDto the DTO containing weather information
	 * @return WeatherInfo the response object containing weather information
	 */
	private WeatherInfo prepareWeatherInfoResponse(WeatherInfoDto weatherInfoDto) {
		WeatherInfo weatherInfo = new WeatherInfo();

		// Check if main property is null
		if (weatherInfoDto.getMain() != null) {
			weatherInfo.setTemp(weatherInfoDto.getMain().getTemp());
			weatherInfo.setTempMin(weatherInfoDto.getMain().getTempMin());
			weatherInfo.setTempMax(weatherInfoDto.getMain().getTempMax());
			weatherInfo.setFeelsLike(weatherInfoDto.getMain().getFeelsLike());
			weatherInfo.setHumidity(weatherInfoDto.getMain().getHumidity());
			weatherInfo.setPressure(weatherInfoDto.getMain().getPressure());
		}

		weatherInfo.setVisibility(weatherInfoDto.getVisibility());
		return weatherInfo;
	}

	/**
	 * Get weather history by postal code.
	 *
	 * @param postalCode the postal code for which to fetch weather history
	 * @return List<Weather> the list of weather entities
	 * @throws PostalCodeNotFoundException if the postal code is not found
	 */
	@Override
	public List<Weather> getWeatherHistoryByPostalCode(String postalCode) {
		return weatherRepository.findByPostalCode(postalCode)
				.orElseThrow(() -> new PostalCodeNotFoundException("Postal code " + postalCode + " not found"));
	}
}