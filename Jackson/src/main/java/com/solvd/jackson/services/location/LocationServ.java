package com.solvd.jackson.services.location;

import com.solvd.jackson.impl.location.AddressDAO;
import com.solvd.jackson.impl.location.CityDAO;
import com.solvd.jackson.impl.location.CountryDAO;
import com.solvd.jackson.impl.location.StateDAO;
import com.solvd.jackson.models.location.Address;
import com.solvd.jackson.models.location.City;
import com.solvd.jackson.models.location.Country;
import com.solvd.jackson.models.location.State;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LocationServ {
    private final static Logger logger = LogManager.getLogger(LocationServ.class);
    private final AddressDAO addressDAO = new AddressDAO();
    private final CityDAO cityDAO = new CityDAO();
    private final StateDAO stateDAO = new StateDAO();
    private final CountryDAO countryDAO = new CountryDAO();

    //Address
    public void createAddress(Address address) {
        addressDAO.create(address);
        logger.info("Address created in DB: " + address.toString());
    }

    public Address getAddressByID(int id) {
        Address address = addressDAO.getByID(id);
        logger.info("Getting address by " + id + ": "+ address.toString());
        return address;
    }

    public void updateAddress(Address address) {
        addressDAO.update(address);
        logger.info("Updating address: " + address.toString());
    }

    public void deleteAddress(int id) {
        addressDAO.delete(id);
        logger.info("Deleting address with id " + id);
    }

    //City
    public void createCity(City city) {
        cityDAO.create(city);
        logger.info("City created in DB: " + city.toString());
    }

    public City getCityByID(int id) {
        City city = cityDAO.getByID(id);
        logger.info("Getting city by " + id + ": "+ city.toString());
        return city;
    }

    public void updateCity(City city) {
        cityDAO.update(city);
        logger.info("Updating city: " + city.toString());
    }

    public void deleteCity(int id) {
        cityDAO.delete(id);
        logger.info("Deleting city with id " + id);
    }

    //State
    public void createState(State state) {
        stateDAO.create(state);
        logger.info("State created in DB: " + state.toString());
    }

    public State getStateByID(int id) {
        State state = stateDAO.getByID(id);
        logger.info("Getting state by " + id + ": "+ state.toString());
        return state;
    }

    public void updateState(State state) {
        stateDAO.update(state);
        logger.info("Updating state: " + state.toString());
    }

    public void deleteState(int id) {
        stateDAO.delete(id);
        logger.info("Deleting state with id " + id);
    }

    //Country
    public void createCountry(Country country) {
        countryDAO.create(country);
        logger.info("Country created in DB: " + country.toString());
    }

    public Country getCountryByID(int id) {
        Country country = countryDAO.getByID(id);
        logger.info("Getting country by " + id + ": "+ country.toString());
        return country;
    }

    public void updateCountry(Country country) {
        countryDAO.update(country);
        logger.info("Updating country: " + country.toString());
    }

    public void deleteCountry(int id) {
        countryDAO.delete(id);
        logger.info("Deleting country with id " + id);
    }
}
