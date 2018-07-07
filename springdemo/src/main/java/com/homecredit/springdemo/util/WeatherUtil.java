package com.homecredit.springdemo.util;

import java.util.UUID;

public class WeatherUtil {

	public static String generateUUID() {
		 UUID uuid = UUID.randomUUID();
	        return uuid.toString();
	}
}
