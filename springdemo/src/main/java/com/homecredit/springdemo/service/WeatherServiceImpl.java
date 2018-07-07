package com.homecredit.springdemo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.homecredit.springdemo.model.Weather;
import com.homecredit.springdemo.model.WeatherList;
import com.homecredit.springdemo.repository.WeatherRepository;
import com.homecredit.springdemo.util.Constants;
import com.homecredit.springdemo.util.WeatherUtil;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private WeatherRepository weatherRepository;

	RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public WeatherList getAllWeather() {
		ResponseEntity<WeatherList> response
		  = restTemplate.getForEntity(Constants.REST_ENDPOINT + "group?id=5391959,3067696,2643743" + Constants.APP_ID, WeatherList.class);
		
		return response.getBody();
	}

	@Override
	public Weather getWeatherByLocation(String location) {
		
		ResponseEntity<Weather> response
		  = restTemplate.getForEntity(Constants.REST_ENDPOINT+"/weather?q=" + location + Constants.APP_ID, Weather.class);

		return response.getBody();
	}

	@Override
	public List<Weather> getAllWeatherHistory() {
		return retrieveWeatherList();
	}

	@Override
	public List<Weather> deleteAndSaveWeatherList(List<Weather> theWeather, List<Long> ids) {			
		deleteWeatherByIds(ids);
		for(Weather weather: theWeather) {
			saveWeather(weather);
		}
		return theWeather;
	}
	
	@Override
	public Weather deleteAndSaveWeather(Weather theWeather, Long id) {			
		deleteWeatherById(id);
		saveWeather(theWeather);
		return theWeather;
	}
	
	@Override
	public void deleteWeatherById(Long id) {
		weatherRepository.deleteById(id);
	}

	@Override
	public void saveWeather(Weather weather) {
		weather.setResponseId(WeatherUtil.generateUUID());
		weather.setdTimeInsert(LocalDateTime.now());
		weatherRepository.save(weather);
    }
	
	@Override
	public void deleteWeatherByIds(List<Long> ids) {
		weatherRepository.deleteByIdIn(ids);
    }

	@Override
	public List<Weather> retrieveWeatherList() {
		return weatherRepository.findAll();
	}
}
