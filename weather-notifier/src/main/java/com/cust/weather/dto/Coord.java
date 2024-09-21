package com.cust.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coord {
	@JsonProperty("lon")
	double lon;

	@JsonProperty("lat")
	double lat;

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLon() {
		return lon;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLat() {
		return lat;
	}
}
