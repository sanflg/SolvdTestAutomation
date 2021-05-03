package com.solvd.jackson.impl.commerce;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.jackson.interfaces.commerce.ICurrencyDAO;
import com.solvd.jackson.models.commerce.Currency;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDAO implements ICurrencyDAO {
    private static final Logger logger = LogManager.getLogger(CurrencyDAO.class);
    private static final File FILE = new File("src/main/resources/json/currencies.json");
    List<Currency> currencies = new ArrayList<>();

    @Override
    public void create(Currency currency) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            currencies = objectMapper.readValue(FILE, new TypeReference<List<Currency>>(){});
            currencies.add(currency);
            objectMapper.writeValue(FILE, currencies);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public Currency getByID(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            currencies = objectMapper.readValue(FILE, new TypeReference<List<Currency>>(){});
        } catch (IOException e){
            logger.error(e);
        }
        return currencies.stream()
                .filter(currency -> currency.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(Currency currency) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            currencies = objectMapper.readValue(FILE, new TypeReference<List<Currency>>(){});
            currencies.stream()
                    .filter(currencyElement -> currencyElement.getId() == currency.getId())
                    .findAny()
                    .ifPresent(updateCurrency -> {
                        updateCurrency.setName(currency.getName());
                        updateCurrency.setTag(currency.getTag());
                    });
            objectMapper.writeValue(FILE, currencies);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public void delete(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            currencies = objectMapper.readValue(FILE, new TypeReference<List<Currency>>(){});
            currencies.stream()
                    .filter(currencyElement -> currencyElement.getId() == id)
                    .findAny()
                    .ifPresent(deleteCurrency -> currencies.remove(deleteCurrency));
        } catch (IOException e){
            logger.error(e);
        }
    }
}
