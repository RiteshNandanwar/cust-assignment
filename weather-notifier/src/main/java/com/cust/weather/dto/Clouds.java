package com.cust.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Clouds {
	@JsonProperty("all")
	int all;

	public void setAll(int all) {
		this.all = all;
	}

	public int getAll() {
		return all;
	}
}
