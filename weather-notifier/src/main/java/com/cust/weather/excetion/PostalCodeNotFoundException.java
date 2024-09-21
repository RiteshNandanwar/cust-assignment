package com.cust.weather.excetion;

/**
 * Custom exception thrown when a postal code is not found.
 */
public class PostalCodeNotFoundException extends RuntimeException {

	/**
	 * Constructs a new PostalCodeNotFoundException with the specified detail message.
	 *
	 * @param errorMessage the detail message
	 */
	public PostalCodeNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}