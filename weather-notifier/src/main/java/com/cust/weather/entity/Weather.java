package com.cust.weather.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Weather {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column
	private double temprature;

	@Column
	private double feelsLike;

	@Column
	private double tempMax;

	@Column
	private double tempMin;

	@Column
	private int pressure;

	@Column
	private int humidity;

	@Column
	private int visibility;

	@Column
	private String user;

	@Column
	private int postalCode;
	
	@Column
	private Date updateDate;

	public Weather() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Weather(Long id, double temprature, double feelsLike, double tempMax, double tempMin, int pressure,
			int humidity, int visibility, String user, int postalCode) {
		super();
		Id = id;
		this.temprature = temprature;
		this.feelsLike = feelsLike;
		this.tempMax = tempMax;
		this.tempMin = tempMin;
		this.pressure = pressure;
		this.humidity = humidity;
		this.visibility = visibility;
		this.user = user;
		this.postalCode = postalCode;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public double getTemprature() {
		return temprature;
	}

	public void setTemprature(double temprature) {
		this.temprature = temprature;
	}

	public double getFeelsLike() {
		return feelsLike;
	}

	public void setFeelsLike(double feelsLike) {
		this.feelsLike = feelsLike;
	}

	public double getTempMax() {
		return tempMax;
	}

	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}

	public double getTempMin() {
		return tempMin;
	}

	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}

	public int getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
}
