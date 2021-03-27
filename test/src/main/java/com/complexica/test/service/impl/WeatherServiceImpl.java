package com.complexica.test.service.impl;

import java.util.Date;
import java.util.List;

import com.complexica.test.model.WeatherEntity;
import com.complexica.test.repository.IWeatherRepo;
import com.complexica.test.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private IWeatherRepo weatherRepository;

    @Override
    public List<WeatherEntity> findAllWeatherData() {
        return weatherRepository.findAll();
    }

    @Override
    public WeatherEntity findWeatherOfCity(String city) {
        return weatherRepository.getWeather(city);
    }

    @Override
    public WeatherEntity findWeatherOfCity(String city, String date) {
        return weatherRepository.getWeather(city, date);
    }

    @Override
    public WeatherEntity invalidateAndRefreshWeatherData(String city, String countryName, String countryCode, Float temperature, String weather, String date) {
        return weatherRepository.updateWeather(city, countryName, countryCode, temperature, weather, date, new Date().getTime());
    }

    @Override
    public void createWeatherData(String city, String countryName, String countryCode, Float temperature, String weather, String date) {
        weatherRepository.createWeather(city, countryName, countryCode, temperature, weather, date, new Date());
    }
    
}
