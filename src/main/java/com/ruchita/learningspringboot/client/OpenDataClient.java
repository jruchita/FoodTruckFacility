package com.ruchita.learningspringboot.client;

import com.ruchita.learningspringboot.model.FoodTruck;
import com.ruchita.learningspringboot.model.Location;

import java.util.List;

public interface OpenDataClient {

    List<FoodTruck> getAllFoodTrucks(String filter);
    List<FoodTruck> getNearestFoodTruck(Location location);
}
