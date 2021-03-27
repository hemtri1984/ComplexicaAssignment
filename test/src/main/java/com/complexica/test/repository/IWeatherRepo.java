package com.complexica.test.repository;

import java.util.List;
import com.complexica.test.model.WeatherEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IWeatherRepo extends JpaRepository<WeatherEntity, Long> {

    @Query("select we from WeatherEntity we")
    List<WeatherEntity> findAll();

    @Query("select we from WeatherEntity where we.cityName = :cityname")
    WeatherEntity getWeather(@Param("cityname") String cityName);

    @Query("update WeatherEntity we set we.countryName = :countryname, we.countryCode = :ccode, we.temperature = :temp, we.weather = :weather, we.recordTime = :time  where we.cityName = :cityname")
    WeatherEntity updateWeather(@Param("cityname") String cityName, @Param("countryname") String countryName, @Param("ccode") String countryCode, @Param("temp") Float temperature, @Param("weather") String weather, @Param("time") Long recordTime);

    @Query("create WeatherEntity we set we.cityName = :cityname, we.countryName = :countryname, we.countryCode = :ccode, we.temperature = :temp, we.weather = :weather, we.recordTime = :time")
    void createWeather(@Param("cityname") String cityName, @Param("countryname") String countryName, @Param("ccode") String countryCode, @Param("temp") Float temperature, @Param("weather") String weather, @Param("time") Long recordTime);

}
