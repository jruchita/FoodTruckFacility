package com.ruchita.learningspringboot.client;

import com.ruchita.learningspringboot.model.FoodTruckEntity;

import java.util.List;

public interface OpenDataClient {

    List<FoodTruckEntity> getAllFoodTrucks(String filter);
    List<FoodTruckEntity> getNearestFoodTruck(String location);
}
