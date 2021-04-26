package com.solvd.dataBaseOnlineShop.services.location;

import com.solvd.dataBaseOnlineShop.dao.jdbc.impl.location.CountryDAO;
import com.solvd.dataBaseOnlineShop.models.location.Country;
import com.solvd.dataBaseOnlineShop.services.IAbstractServ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CountryServ implements IAbstractServ<Country> {
    private final static Logger logger = LogManager.getLogger(CountryServ.class);
    private final CountryDAO countryDAO = new CountryDAO();

    public CountryServ() {
    }

    @Override
    public void create(Country country) {
        countryDAO.create(country);
        logger.info("Country created in DB: " + country.toString());
    }

    @Override
    public Country getByID(int id) {
        Country country = countryDAO.getByID(id);
        logger.info("Getting country by " + id + ": "+ country.toString());
        return country;
    }

    @Override
    public void update(Country country) {
        countryDAO.update(country);
        logger.info("Updating country: " + country.toString());
    }

    @Override
    public void delete(int id) {
        countryDAO.delete(id);
        logger.info("Deleting country with id " + id);
    }
}
