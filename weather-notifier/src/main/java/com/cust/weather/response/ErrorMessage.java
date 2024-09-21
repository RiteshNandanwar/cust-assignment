package com.cust.weather.response;

public class ErrorMessage {
	private int errorId;

	private String errorMessage;

	public ErrorMessage(int errorId, String errorMessage) {
		super();
		this.errorId = errorId;
		this.errorMessage = errorMessage;
	}
	
	public ErrorMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getErrorId() {
		return errorId;
	}

	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
