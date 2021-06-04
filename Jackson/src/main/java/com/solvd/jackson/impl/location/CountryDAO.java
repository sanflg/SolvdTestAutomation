package com.solvd.jackson.impl.location;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.jackson.interfaces.location.ICountryDAO;
import com.solvd.jackson.models.location.Country;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO implements ICountryDAO {
    private static final Logger logger = LogManager.getLogger(CountryDAO.class);
    private static final File FILE = new File("src/main/resources/json/countries.json");
    List<Country> countries = new ArrayList<>();

    @Override
    public void create(Country country) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            countries = objectMapper.readValue(FILE, new TypeReference<List<Country>>(){});
            countries.add(country);
            objectMapper.writeValue(FILE, countries);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public Country getByID(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            countries = objectMapper.readValue(FILE, new TypeReference<List<Country>>(){});
        } catch (IOException e){
            logger.error(e);
        }
        return countries.stream()
                .filter(country -> country.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(Country country) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            countries = objectMapper.readValue(FILE, new TypeReference<List<Country>>(){});
            countries.stream()
                    .filter(countryElement -> countryElement.getId() == country.getId())
                    .findAny()
                    .ifPresent(updateCountry -> {
                        updateCountry.setName(country.getName());
                        updateCountry.setTag(country.getTag());
                    });
            objectMapper.writeValue(FILE, countries);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public void delete(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            countries = objectMapper.readValue(FILE, new TypeReference<List<Country>>(){});
            countries.stream()
                    .filter(countryElement -> countryElement.getId() == id)
                    .findAny()
                    .ifPresent(deleteCountry -> countries.remove(deleteCountry));
        } catch (IOException e){
            logger.error(e);
        }
    }
}
