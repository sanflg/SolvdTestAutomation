package com.solvd.jackson.services.location;

import com.solvd.jackson.impl.location.CityDAO;
import com.solvd.jackson.models.location.City;
import com.solvd.jackson.services.IAbstractServ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CityServ implements IAbstractServ<City> {
    private final static Logger logger = LogManager.getLogger(CityServ.class);
    private final CityDAO cityDAO = new CityDAO();

    public CityServ() {
    }

    @Override
    public void create(City city) {
        cityDAO.create(city);
        logger.info("City created in DB: " + city.toString());
    }

    @Override
    public City getByID(int id) {
        City city = cityDAO.getByID(id);
        logger.info("Getting city by " + id + ": "+ city.toString());
        return city;
    }

    @Override
    public void update(City city) {
        cityDAO.update(city);
        logger.info("Updating city: " + city.toString());
    }

    @Override
    public void delete(int id) {
        cityDAO.delete(id);
        logger.info("Deleting city with id " + id);
    }
}
