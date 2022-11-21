package com.ruchita.learningspringboot.client;

import com.ruchita.learningspringboot.model.FoodTruck;

import java.util.List;

public interface OpenDataClient {

    List<FoodTruck> getAllFoodTrucks(String filter);
}
