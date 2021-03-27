package com.complexica.test.repository;

import java.util.List;
import com.complexica.test.model.WeatherEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IWeatherRepo extends JpaRepository<WeatherEntity, Long> {

    @Query("select weather from WeatherEntity weather")
    List<WeatherEntity> findAll();

}
