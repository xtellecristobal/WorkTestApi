package com.homecredit.springdemo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.homecredit.springdemo.model.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
	
	@Transactional
	@Modifying
	void deleteByIdIn(List<Long> ids);
}
