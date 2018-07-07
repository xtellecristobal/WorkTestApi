package com.homecredit.springdemo.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homecredit.springdemo.model.Weather;
import com.homecredit.springdemo.model.WeatherList;
import com.homecredit.springdemo.service.WeatherService;
import com.homecredit.springdemo.util.Constants;

@RestController
@RequestMapping("/api")
public class WeatherRestController {
	
	@Autowired
	private WeatherService theWeatherService;

	@GetMapping("/weather")
	public WeatherList getWeatherList(){
		
		return theWeatherService.getAllWeather(); 
	
	}
	
	@PostMapping("/weather")
	public List<Weather> getAndSaveWeatherList() {
		WeatherList weatherList = theWeatherService.getAllWeather();
		List<Weather> list =  theWeatherService.retrieveWeatherList();
		List<Weather> saveWeather = new ArrayList<Weather>();
		for(Weather theWeather: weatherList.getList()) {
			if(!isRecordExists(list, theWeather)) {
				saveWeather.add(theWeather);
				}
		}
		List<Long> ids = getIdsForDeletion(list, (list.size() + saveWeather.size() - Constants.MAX_ROW));
		return theWeatherService.deleteAndSaveWeatherList(saveWeather, ids); 
	
	}

	
	@GetMapping("/weather/{location}")
	public Weather getWeather(@PathVariable String location){
		
		if(!Arrays.asList(Constants.LOCATIONS).contains(location)) {
			throw new WeatherNotFoundException("Location not found - " + location);
		}
		
		return theWeatherService.getWeatherByLocation(location);
		
	}
	
	@PostMapping("/weather/{location}")
	public Weather getAndSaveWeather(@PathVariable String location){
		
		if(!Arrays.asList(Constants.LOCATIONS).contains(location)) {
			throw new WeatherNotFoundException("Location not found - " + location);
		}
		
		Weather weather = theWeatherService.getWeatherByLocation(location);
		List<Weather> list =  theWeatherService.retrieveWeatherList();
		Long id = null;
		Weather saveWeather = new Weather();
			if(!isRecordExists(list, weather)) {
				saveWeather = weather;
			 if(list.size() == Constants.MAX_ROW)
				 id = list.get(0).getId();
			}
			return theWeatherService.deleteAndSaveWeather(saveWeather, id); 
		}
	
	@GetMapping("/weather/history")
	public List<Weather> geWeatherHistory(){
		
		return theWeatherService.getAllWeatherHistory(); 
	
	}
	
	
	private List<Long> getIdsForDeletion(List<Weather> list, int size){
		List<Long> ids = new ArrayList<Long>();
		if(size>0) {
			for(int i=0; i<size; i++) {
				ids.add(list.get(i).getId());
			}
		}
		return ids;
	}
	
	private boolean isRecordExists(List<Weather> fromDB , Weather weather) {
		for(Weather theWeather: fromDB) {
			if (weather.getLocation().equalsIgnoreCase(theWeather.getLocation())
					&& weather.getActualWeather().equalsIgnoreCase(theWeather.getActualWeather())
					&& weather.getTemperature().equalsIgnoreCase(weather.getTemperature())) {
				return true;
			}
		}
		return false;
	}	
	
}
