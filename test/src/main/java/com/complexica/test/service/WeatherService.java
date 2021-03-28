package com.complexica.test.service;

import java.util.List;
import com.complexica.test.model.WeatherEntity;

public interface WeatherService {
    List<WeatherEntity> findAllWeatherData();
    List<WeatherEntity> findWeatherOfCity(String city);
    List<WeatherEntity> findWeatherOfCity(String city, String date);
    WeatherEntity invalidateAndRefreshWeatherData(String city, String countryName, String countryCode, Float temperature, String weather, String date, String time);
    void createWeatherData(String city, String countryName, String countryCode, Float temperature, String weather, String date, String time);
}
