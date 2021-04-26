package com.solvd.dataBaseOnlineShop.services.comerce;

import com.solvd.dataBaseOnlineShop.dao.jdbc.impl.comerce.CurrencyDAO;
import com.solvd.dataBaseOnlineShop.models.comerce.Currency;
import com.solvd.dataBaseOnlineShop.services.IAbstractServ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CurrencyServ implements IAbstractServ<Currency> {
    private final static Logger logger = LogManager.getLogger(CurrencyServ.class);
    private final CurrencyDAO currencyDAO = new CurrencyDAO();

    public CurrencyServ() {
    }

    @Override
    public void create(Currency currency) {
        currencyDAO.create(currency);
        logger.info("Currency created in DB: " + currency.toString());
    }

    @Override
    public Currency getByID(int id) {
        Currency currency = currencyDAO.getByID(id);
        logger.info("Getting currency by " + id + ": "+ currency.toString());
        return currency;
    }

    @Override
    public void update(Currency currency) {
        currencyDAO.update(currency);
        logger.info("Updating currency: " + currency.toString());
    }

    @Override
    public void delete(int id) {
        currencyDAO.delete(id);
        logger.info("Deleting currency with id " + id);
    }
}
