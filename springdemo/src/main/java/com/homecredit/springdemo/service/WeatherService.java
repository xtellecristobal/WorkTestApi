package com.homecredit.springdemo.service;

import java.util.List;

import com.homecredit.springdemo.model.Weather;
import com.homecredit.springdemo.model.WeatherList;


public interface WeatherService {
	
	WeatherList getAllWeather();
	
	Weather getWeatherByLocation(String location);

	List<Weather> getAllWeatherHistory();

    List<Weather> retrieveWeatherList();

	List<Weather> deleteAndSaveWeatherList(List<Weather> theWeather, List<Long> ids);
	
	Weather deleteAndSaveWeather(Weather theWeather, Long id);

	void deleteWeatherByIds(List<Long> ids);
	
	void saveWeather(Weather weather);
	
	void deleteWeatherById(Long id);

}
