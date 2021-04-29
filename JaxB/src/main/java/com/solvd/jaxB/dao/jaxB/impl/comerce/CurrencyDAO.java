package com.solvd.jaxB.dao.jaxB.impl.comerce;

import com.solvd.jaxB.dao.interfaces.comerce.ICurrencyDAO;
import com.solvd.jaxB.models.comerce.Currency;
import com.solvd.jaxB.wrappers.comerce.Currencies;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class CurrencyDAO implements ICurrencyDAO {
    private static final Logger logger = LogManager.getLogger(CurrencyDAO.class);
    private static final File FILE = new File("src/main/resources/xml/currencies.xml");

    @Override
    public void create(Currency currency) {
        Currencies currencies = new Currencies();
        currencies.setCurrencies(unmarshall());
        currencies.getCurrencies().add(currency);
        marshall(currencies);
    }

    @Override
    public Currency getByID(int id) {
        Currencies currencies = new Currencies();
        currencies.setCurrencies(unmarshall());
        return currencies.getCurrencies().stream()
                .filter(currency -> currency.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(Currency currency) {
        Currencies currencies = new Currencies();
        currencies.setCurrencies(unmarshall());
        currencies.getCurrencies().stream()
                .filter(currencyStream -> currencyStream.getId() == currency.getId())
                .findAny()
                .ifPresent(editCurrency ->{
                        editCurrency.setName(currency.getName());
                        editCurrency.setTag(currency.getTag());
                        });
        marshall(currencies);
    }

    @Override
    public void delete(int id) {
        Currencies currencies = new Currencies();
        currencies.setCurrencies(unmarshall());
        currencies.getCurrencies().stream()
                .filter(currencyStream -> currencyStream.getId() == id)
                .findAny()
                .ifPresent(editCurrency -> currencies.getCurrencies().remove(editCurrency));
        marshall(currencies);
    }

    private static synchronized List<Currency> unmarshall(){
        Currencies currencies = new Currencies();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Currencies.class);
            Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();
            currencies = (Currencies) jaxUnmarshaller.unmarshal(FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
        return currencies.getCurrencies();
    }

    private static synchronized void marshall(Currencies currencies){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Currencies.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(currencies, FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }
}
