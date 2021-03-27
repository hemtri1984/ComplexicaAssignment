package com.complexica.test.repository;

import java.util.List;

import com.complexica.test.model.ItineraryEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IItineraryRepo extends JpaRepository<ItineraryEntity, Long> {

    @Query("select itinerary from ItineraryEntity itinerary")
    List<ItineraryEntity> findAll();

}
