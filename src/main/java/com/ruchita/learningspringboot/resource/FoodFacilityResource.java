package com.ruchita.learningspringboot.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruchita.learningspringboot.model.FoodTruck;
import com.ruchita.learningspringboot.model.Location;
import com.ruchita.learningspringboot.service.FoodTruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.ArrayList;
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

    @RequestMapping(method = RequestMethod.POST,
            path = "/getFoodTrucks"
    )
    public ResponseEntity<List<FoodTruck>> fetchFoodTrucks(@QueryParam("filter") String filter, @RequestBody LinkedHashMap body) {
        List<FoodTruck> foodTruckList = new ArrayList<>();
        ObjectMapper oMapper = new ObjectMapper();
        if (body.containsKey("location")) {
            Location location = oMapper.convertValue(body.get("location"), Location.class);
            foodTruckList = foodTruckService.getNearestFoodTruck(location);
        } else {
            foodTruckList = foodTruckService.getAllFoodTrucks(filter);
        }
        return ResponseEntity.ok(foodTruckList);
    }
}