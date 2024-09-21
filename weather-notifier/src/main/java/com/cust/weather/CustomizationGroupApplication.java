package com.cust.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class CustomizationGroupApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomizationGroupApplication.class, args);
	}


	/**
	 * Bean definition for WebClient.
	 *
	 * @return a configured WebClient instance
	 */
	@Bean
	public WebClient getWebclientBean() {
		return WebClient.builder().build();
	}
}
