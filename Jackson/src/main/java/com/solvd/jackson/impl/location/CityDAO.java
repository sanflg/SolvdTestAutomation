package com.solvd.jackson.impl.location;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.jackson.interfaces.location.ICityDAO;
import com.solvd.jackson.models.location.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CityDAO implements ICityDAO {
    private static final Logger logger = LogManager.getLogger(CityDAO.class);
    private static final File FILE = new File("src/main/resources/json/cities.json");
    List<City> cities = new ArrayList<>();

    @Override
    public void create(City city) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            cities = objectMapper.readValue(FILE, new TypeReference<List<City>>(){});
            cities.add(city);
            objectMapper.writeValue(FILE, cities);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public City getByID(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            cities = objectMapper.readValue(FILE, new TypeReference<List<City>>(){});
        } catch (IOException e){
            logger.error(e);
        }
        return cities.stream()
                .filter(city -> city.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(City city) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            cities = objectMapper.readValue(FILE, new TypeReference<List<City>>(){});
            cities.stream()
                    .filter(cityElement -> cityElement.getId() == city.getId())
                    .findAny()
                    .ifPresent(updateCity -> {
                        updateCity.setName(city.getName());
                        updateCity.setStateId(city.getState());
                    });
            objectMapper.writeValue(FILE, cities);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public void delete(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            cities = objectMapper.readValue(FILE, new TypeReference<List<City>>(){});
            cities.stream()
                    .filter(cityElement -> cityElement.getId() == id)
                    .findAny()
                    .ifPresent(deleteCity -> cities.remove(deleteCity));
        } catch (IOException e){
            logger.error(e);
        }
    }
}
