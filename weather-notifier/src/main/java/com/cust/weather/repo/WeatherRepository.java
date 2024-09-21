package com.cust.weather.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cust.weather.entity.Weather;

/**
 * Repository interface for Weather entity.
 */
@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

	/**
	 * Find weather information by postal code.
	 *
	 * @param postalCode the postal code for which to find weather information
	 * @return an Optional containing a list of Weather entities if found, otherwise empty
	 */
	Optional<List<Weather>> findByPostalCode(String postalCode);
}