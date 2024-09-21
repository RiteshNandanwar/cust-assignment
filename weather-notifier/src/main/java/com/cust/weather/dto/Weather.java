package com.cust.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

	@JsonProperty("id")
	int id;

	@JsonProperty("main")
	String main;

	@JsonProperty("description")
	String description;

	@JsonProperty("icon")
	String icon;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getMain() {
		return main;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIcon() {
		return icon;
	}
}
