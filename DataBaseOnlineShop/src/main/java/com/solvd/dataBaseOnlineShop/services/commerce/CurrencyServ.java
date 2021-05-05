package com.solvd.dataBaseOnlineShop.services.commerce;

import com.solvd.dataBaseOnlineShop.dao.jdbc.impl.commerce.CurrencyDAO;
import com.solvd.dataBaseOnlineShop.models.commerce.Currency;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CurrencyServ{
    private final static Logger logger = LogManager.getLogger(CurrencyServ.class);
    private final CurrencyDAO currencyDAO = new CurrencyDAO();

    public void create(Currency currency) {
        currencyDAO.create(currency);
        logger.info("Currency created in DB: " + currency.toString());
    }

    public Currency getByID(int id) {
        Currency currency = currencyDAO.getByID(id);
        logger.info("Getting currency by " + id + ": "+ currency.toString());
        return currency;
    }

    public void update(Currency currency) {
        currencyDAO.update(currency);
        logger.info("Updating currency: " + currency.toString());
    }

    public void delete(int id) {
        currencyDAO.delete(id);
        logger.info("Deleting currency with id " + id);
    }
}
