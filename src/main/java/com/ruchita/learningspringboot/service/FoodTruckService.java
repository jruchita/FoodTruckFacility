package com.ruchita.learningspringboot.service;

import com.ruchita.learningspringboot.model.FoodTruckEntity;
import com.ruchita.learningspringboot.repository.FoodTruckRepository;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FoodTruckService {
    @Autowired
    private FoodTruckRepository foodTruckRepository;

    @Autowired
    public FoodTruckService(FoodTruckRepository foodTruckRepository) {
        this.foodTruckRepository = foodTruckRepository;
    }

    public List<FoodTruckEntity> getAllFoodTrucks(String filter) {
         return foodTruckRepository.getAllFoodTrucks(filter);
    }

    public FoodTruckEntity getNearestFoodTruck(String location) {
        return foodTruckRepository.getNearestFoodTruck(location);
    }

    public void insertFoodTruck(HashMap<String, String> body) {
        FoodTruckEntity foodTruck =  new FoodTruckEntity();
        foodTruck.setObjectId(Long.valueOf(body.get("objectid")));
        foodTruck.setApplicant(body.get("applicant"));
        foodTruckRepository.insertFoodTruck(foodTruck);
    }
    private List<NameValuePair> getParameters(String filter) {
        List<NameValuePair> paramList = new ArrayList<>();
        if(filter !=null) {
            if(filter.contains("AND")) {
                String[] paramArray =  filter.split(" AND ");
                for(String param : paramArray) {
                    String lhs = param.split("=")[0];
                    String rhs = param.split("=")[1];
                    if (lhs.equalsIgnoreCase("expirationdate")) {
                        rhs = rhs.substring(0, 10);
                    }
                    buildParamList(lhs, rhs, paramList);
                }
            }
            else {
                String lhs = filter.split("=")[0];
                String rhs = filter.split("=")[1];
                if (lhs.equalsIgnoreCase("expirationdate")) {
                    rhs = rhs.substring(0, 10);
                }
                buildParamList(lhs, rhs, paramList);
            }
        }
        return paramList;
    }
    private List<NameValuePair> buildParamList(String lhs, String rhs, List<NameValuePair> paramList) {
        paramList.add(new BasicNameValuePair(lhs, rhs));
        return paramList;
    }
}
