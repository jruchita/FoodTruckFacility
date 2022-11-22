package com.ruchita.learningspringboot.repository;

import com.ruchita.learningspringboot.helper.DistanceHelper;
import com.ruchita.learningspringboot.model.FoodTruckEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Function;

@Transactional
public class FoodTruckRepositoryImpl implements  FoodTruckRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<FoodTruckEntity> getAllFoodTrucks(String filter) {
        StringBuilder sb = new StringBuilder("select f from FoodTruckEntity f ");
        if(filter !=null) {
            String lhs = filter.split("=")[0];
            String rhs = filter.split("=")[1];
            sb.append("where f."+lhs+" = '"+rhs+"'");
        }
        Query query = em.createQuery(sb.toString(), FoodTruckEntity.class);
        return query.getResultList();
    }

    @Override
    public FoodTruckEntity getNearestFoodTruck(String location) {
        location = location.substring(1, location.length() - 1);
        Double latitude =  Double.valueOf(location.split(",")[0]);
        Double longitude =  Double.valueOf(location.split(",")[1]);
        List<FoodTruckEntity> foodTruckList = getAllFoodTrucks(null);
        FoodTruckEntity nearestFoodTruck = null;
        Map<Long, FoodTruckEntity> objectIdFoodTruckMap =  new HashMap<>();
        Map<Long, Double> objectIdDistanceMap =  new HashMap<>();

        foodTruckList.stream().forEach(item-> objectIdFoodTruckMap.put(item.getObjectId(), item));
        for (Map.Entry<Long, FoodTruckEntity> entry : objectIdFoodTruckMap.entrySet()) {
            Long key = entry.getKey();
            FoodTruckEntity value = entry.getValue();
            String locationFoodTruck =  value.getLocation();
            locationFoodTruck = locationFoodTruck.substring(1, locationFoodTruck.length() - 1);
            Double latitudeFoodTruck =  Double.valueOf(locationFoodTruck.split(",")[0]);
            Double longitudeFoodTruck =  Double.valueOf(locationFoodTruck.split(",")[1]);
            Double distance = DistanceHelper.distance(latitude, longitude, latitudeFoodTruck, longitudeFoodTruck);
            objectIdDistanceMap.put(key, distance);
        }
        Double minDistance = Collections.min(objectIdDistanceMap.values());
        for(Map.Entry<Long, Double> entry: objectIdDistanceMap.entrySet()) {
            // if give value is equal to value from entry
            // print the corresponding key
            if(entry.getValue() == minDistance) {
                System.out.println("The key for value " + minDistance + " is " + entry.getKey());
                nearestFoodTruck = objectIdFoodTruckMap.get(entry.getKey());
                break;
            }
        }
        return nearestFoodTruck;
    }

    @Override
    public void insertFoodTruck(FoodTruckEntity foodTruck) {
        em.persist(foodTruck);
    }

    @Override
    public List<FoodTruckEntity> findAll() {
        return null;
    }

    @Override
    public List<FoodTruckEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<FoodTruckEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<FoodTruckEntity> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(FoodTruckEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends FoodTruckEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends FoodTruckEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends FoodTruckEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<FoodTruckEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends FoodTruckEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends FoodTruckEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<FoodTruckEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public FoodTruckEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public FoodTruckEntity getById(Long aLong) {
        return null;
    }

    @Override
    public FoodTruckEntity getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends FoodTruckEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends FoodTruckEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends FoodTruckEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends FoodTruckEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends FoodTruckEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends FoodTruckEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends FoodTruckEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
