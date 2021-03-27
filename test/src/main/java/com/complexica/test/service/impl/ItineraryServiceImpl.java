package com.complexica.test.service.impl;

import java.util.List;

import com.complexica.test.model.ItineraryEntity;
import com.complexica.test.repository.IItineraryRepo;
import com.complexica.test.service.ItineraryService;

import org.springframework.beans.factory.annotation.Autowired;

public class ItineraryServiceImpl implements ItineraryService {

    @Autowired
    private IItineraryRepo itineraryRepository;

    @Override
    public List<ItineraryEntity> findAllItineraryData() {
        return itineraryRepository.findAll();
    }

    @Override
    public ItineraryEntity findItineraryOfCity(String city) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ItineraryEntity findItineraryOnDate(String travelDate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createItineraryData(String itineraryName, String cityName, String country, Float temperature,
            String weather, String travelDate, String advice) {
        // TODO Auto-generated method stub
        
    }
    
}
