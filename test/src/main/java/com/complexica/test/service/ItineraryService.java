package com.complexica.test.service;

import java.util.List;

import com.complexica.test.model.ItineraryEntity;

public interface ItineraryService {
    List<ItineraryEntity> findAllItineraryData();
    ItineraryEntity findItineraryOfCity(String city);
    ItineraryEntity findItineraryOnDate(String travelDate);
    void createItineraryData(String itineraryName, String cityName, String country, Float temperature, String weather, String travelDate, String advice);

}
