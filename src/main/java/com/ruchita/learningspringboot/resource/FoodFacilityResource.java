package com.ruchita.learningspringboot.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruchita.learningspringboot.model.FoodTruckEntity;
import com.ruchita.learningspringboot.service.FoodTruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping(
        path = "/api/v1/foodtruck"
)
public class FoodFacilityResource {

    private FoodTruckService foodTruckService;

    @Autowired
    public FoodFacilityResource(FoodTruckService foodTruckService) {
        this.foodTruckService = foodTruckService;
    }

    @RequestMapping(method = RequestMethod.GET,
            path = "/getFoodTrucks"
    )
    public ResponseEntity<List<FoodTruckEntity>> fetchFoodTrucks(@QueryParam("filter") String filter) {
        List<FoodTruckEntity> foodTruckList = new ArrayList<>();
        foodTruckList = foodTruckService.getAllFoodTrucks(filter);
        return ResponseEntity.ok(foodTruckList);
    }

    @RequestMapping(method = RequestMethod.POST,
            path = "/getNearestTruck"
    )
    public ResponseEntity<FoodTruckEntity> nearestFoodTruck(@RequestBody HashMap<String, String> body ) {
        FoodTruckEntity foodTruck = null;
        if(body.containsKey("location")) {
            foodTruck = foodTruckService.getNearestFoodTruck(body.get("location"));
        }
        return ResponseEntity.ok(foodTruck);
    }
}