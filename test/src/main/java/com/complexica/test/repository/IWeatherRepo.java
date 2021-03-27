package com.complexica.test.repository;

import java.util.Date;
import java.util.List;
import com.complexica.test.model.WeatherEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IWeatherRepo extends JpaRepository<WeatherEntity, Long> {

    @Query("select we from WeatherEntity we")
    List<WeatherEntity> findAll();

    @Query("select we from WeatherEntity we where we.cityName = :cityname")
    WeatherEntity getWeather(@Param("cityname") String cityName);

    @Query("select we from WeatherEntity we where we.cityName = :cityname and we.weatherDate = :date")
    WeatherEntity getWeather(@Param("cityname") String cityName, @Param("date")String date);

    @Query("update WeatherEntity we set we.country = :countryname, we.countryCode = :ccode, we.temperature = :temp, we.weather = :weather, we.recordTime = :time  where we.cityName = :cityname and we.weatherDate = :date")
    WeatherEntity updateWeather(@Param("cityname") String cityName, @Param("countryname") String countryName, @Param("ccode") String countryCode, @Param("temp") Float temperature, @Param("weather") String weather, @Param("date") String date, @Param("time") Long recordTime);

    @Modifying
    @Query(value = "insert into weather (cityName, country, countryCode, temperature, weather, weatherDate, recordTime) VALUES (:cityname,:countryname, :ccode, :temp, :weather, :date, :time)", nativeQuery = true)
    @Transactional
    void createWeather(@Param("cityname") String cityName, @Param("countryname") String countryName, @Param("ccode") String countryCode, @Param("temp") Float temperature, @Param("weather") String weather, @Param("date") String date, @Param("time") Date recordTime);

}
