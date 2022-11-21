package com.ruchita.learningspringboot.resource;

import com.ruchita.learningspringboot.model.FoodTruck;
import com.ruchita.learningspringboot.service.FoodTruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
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
    public List<FoodTruck> fetchFoodTrucks(@QueryParam("filter") String filter) {
         return foodTruckService.getAllFoodTrucks(filter);
    }
}
