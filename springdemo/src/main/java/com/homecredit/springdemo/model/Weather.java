package com.homecredit.springdemo.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="Weatherlog")
public class Weather {

	public Weather() {
		
	}
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@JsonIgnore
	private String responseId;
	
	@JsonProperty("name")
	private String location;
	
	@JsonProperty(access= Access.WRITE_ONLY)
	@Transient
	private List<WeatherDescription> weather;
	
 	private String actualWeather;
	
	private String temperature;
	
	@JsonIgnore
	private LocalDateTime dTimeInserted;
	

	@JsonProperty("main")
	private void unpackNested(Map<String,Object> main) {
      this.temperature = String.valueOf(main.get("temp"));
	 }

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getResponseId() {
		return responseId;
	}
	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public List<WeatherDescription> getWeather() {
		return weather;
	}
	
	public void setWeather(List<WeatherDescription> weather) {
		this.weather = weather;
		setActualWeather(weather.get(0).getDescription());
	}
	
	@JsonProperty("temperature")
	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getActualWeather() {
		return actualWeather;
	}

	public void setActualWeather(String actualWeather) {
		this.actualWeather = actualWeather;
	}

	public LocalDateTime getdTimeInserted() {
		return dTimeInserted;
	}

	public void setdTimeInsert(LocalDateTime dTimeInserted) {
		this.dTimeInserted = dTimeInserted;
	}
}
