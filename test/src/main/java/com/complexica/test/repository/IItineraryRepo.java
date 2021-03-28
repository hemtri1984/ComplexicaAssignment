package com.complexica.test.repository;

import java.util.List;

import com.complexica.test.model.ItineraryEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IItineraryRepo extends JpaRepository<ItineraryEntity, Long> {

    @Query("select itinerary from ItineraryEntity itinerary")
    List<ItineraryEntity> findAll();

    /*@Query("select max(timestamp) iti from ItineraryEntity where iti.cityName = :cityname")
    ItineraryEntity getItineraryOfCity(@Param("cityname") String cityName);

    @Query("select max(timestamp) iti from ItineraryEntity where iti.travelDate = :traveldate")
    ItineraryEntity getItineraryOfDate(@Param("traveldate") String travelDate);*/

    @Modifying
    @Query(value = "insert into itinerary (itineraryName, cityname, country, temperature, weather, travelDate, advice, timestamp) VALUES (:itiname,:cityname, :country, :temp, :weather, :tdate, :advice, :timestamp)", nativeQuery = true)
    @Transactional
    void createItinerary(@Param("itiname") String itineraryName, @Param("cityname") String cityName, @Param("country") String country, @Param("temp") Float temperature, @Param("weather") String weather, @Param("tdate") String travelDate, @Param("advice") String advice, @Param("timestamp") Long timestamp);

}
