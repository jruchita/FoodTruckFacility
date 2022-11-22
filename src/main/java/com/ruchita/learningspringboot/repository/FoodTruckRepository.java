package com.ruchita.learningspringboot.repository;

import com.ruchita.learningspringboot.model.FoodTruckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodTruckRepository extends JpaRepository<FoodTruckEntity, Long> {

    public List<FoodTruckEntity> getAllFoodTrucks(String filter);
    public FoodTruckEntity getNearestFoodTruck(String location);

}
