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

    @Query("select we from WeatherEntity we where we.cityname = :cityname")
    List<WeatherEntity> getWeather(@Param("cityname") String cityName);

    @Query("select we from WeatherEntity we where we.cityname = :cityname and we.weatherdate = :date")
    List<WeatherEntity> getWeather(@Param("cityname") String cityName, @Param("date")String date);

    @Query("update WeatherEntity we set we.country = :countryname, we.countrycode = :ccode, we.temperature = :temp, we.weather = :weather, we.weathertime = :weathertime, we.recordtime = :time  where we.cityname = :cityname and we.weatherdate = :date")
    WeatherEntity updateWeather(@Param("cityname") String cityName, @Param("countryname") String countryName, @Param("ccode") String countryCode, @Param("temp") Float temperature, @Param("weather") String weather, @Param("date") String date, @Param("weathertime") String weathertime, @Param("time") Long recordTime);

    @Modifying
    @Query(value = "insert into weather (cityname, country, countrycode, temperature, weather, weatherdate, weathertime, recordtime) VALUES (:cityname,:countryname, :ccode, :temp, :weather, :date, :weathertime, :time)", nativeQuery = true)
    @Transactional
    void createWeather(@Param("cityname") String cityName, @Param("countryname") String countryName, @Param("ccode") String countryCode, @Param("temp") Float temperature, @Param("weather") String weather, @Param("date") String date, @Param("weathertime") String weathertime, @Param("time") Date recordTime);

    @Query("select we from WeatherEntity we where we.weatherdate = :date")
    List<WeatherEntity> getWeatherByDate(@Param("date") String date);

}
