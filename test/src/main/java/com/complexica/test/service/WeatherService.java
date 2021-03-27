package com.complexica.test.service;

import java.util.List;
import com.complexica.test.model.WeatherEntity;

public interface WeatherService {
    List<WeatherEntity> findAllWeatherData();
    WeatherEntity findWeatherOfCity(String city);
    WeatherEntity invalidateAndRefreshWeatherData(String city, String countryName, String countryCode, Float temperature, String weather);
    void createWeatherData(String city, String countryName, String countryCode, Float temperature, String weather);
}
