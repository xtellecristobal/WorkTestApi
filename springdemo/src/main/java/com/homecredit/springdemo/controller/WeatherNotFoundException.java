package com.homecredit.springdemo.controller;

public class WeatherNotFoundException extends RuntimeException {

	public WeatherNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public WeatherNotFoundException(String message) {
		super(message);
	}

	public WeatherNotFoundException(Throwable cause) {
		super(cause);
	}
}
