package com.ruchita.learningspringboot.service;

import com.ruchita.learningspringboot.client.OpenDataClient;
import com.ruchita.learningspringboot.model.FoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodTruckService {
    private OpenDataClient openDataClient;

    @Autowired
    public FoodTruckService(OpenDataClient openDataClient) {
        this.openDataClient = openDataClient;
    }

    public List<FoodTruck> getAllFoodTrucks(String filter) {
         return openDataClient.getAllFoodTrucks(filter);
    }
}
