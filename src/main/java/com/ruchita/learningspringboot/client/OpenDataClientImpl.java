package com.ruchita.learningspringboot.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruchita.learningspringboot.model.FoodTruck;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Repository
public class OpenDataClientImpl implements OpenDataClient {
    public static final String URL = "https://data.sfgov.org/resource/rqzj-sfat.json";

    @Override
    public List<FoodTruck> getAllFoodTrucks(String filter) {
        HttpClient httpClient =  HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(
                URL);
        List<NameValuePair> paramList = getParameters(filter);
        try {
            URI uri = new URIBuilder(getRequest.getURI())
                    .addParameters(paramList)
                    .build();
            getRequest.setURI(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpResponse response = null;
        try {
            response = httpClient.execute(getRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }

        return getFoodTrucksMappedResponse(response);
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

    private List<FoodTruck> getFoodTrucksMappedResponse(HttpResponse response) {
        String output = null;
        List<FoodTruck> mappedResponse = null;
        BufferedReader br = null;
        StringBuilder responseBody = new StringBuilder();
        try {
            br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));
            while ((output = br.readLine()) != null) {
                responseBody.append(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            mappedResponse =  jsonMapper.readValue(responseBody.toString(),new TypeReference<List<FoodTruck>>(){});
            System.out.println(mappedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return mappedResponse;
    }

    private List<NameValuePair> buildParamList(String lhs, String rhs, List<NameValuePair> paramList) {
        paramList.add(new BasicNameValuePair(lhs, rhs));
        return paramList;
    }
}
