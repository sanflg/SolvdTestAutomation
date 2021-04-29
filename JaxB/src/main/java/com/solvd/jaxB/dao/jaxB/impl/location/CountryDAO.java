package com.solvd.jaxB.dao.jaxB.impl.location;

import com.solvd.jaxB.dao.interfaces.location.ICountryDAO;
import com.solvd.jaxB.models.location.Country;
import com.solvd.jaxB.wrappers.location.Countries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;


public class CountryDAO implements ICountryDAO {
    private static final Logger logger = LogManager.getLogger(StateDAO.class);
    private static final File FILE = new File("src/main/resources/xml/countries.xml");

    @Override
    public void create(Country country) {
        Countries countries = new Countries();
        countries.setCountries(unmarshall());
        countries.getCountries().add(country);
        marshall(countries);
    }

    @Override
    public Country getByID(int id) {
        Countries countries = new Countries();
        countries.setCountries(unmarshall());
        return countries.getCountries().stream()
                .filter(country -> country.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(Country country) {
        Countries countries = new Countries();
        countries.setCountries(unmarshall());
        countries.getCountries().stream()
                .filter(countryStream -> countryStream.getId() == country.getId())
                .findAny()
                .ifPresent(editCountry ->{
                    editCountry.setName(country.getName());
                    editCountry.setTag(country.getTag());
                });
        marshall(countries);
    }

    @Override
    public void delete(int id) {
        Countries countries = new Countries();
        countries.setCountries(unmarshall());
        countries.getCountries().stream()
                .filter(countryStream -> countryStream.getId() == id)
                .findAny()
                .ifPresent(editCountry ->
                        countries.getCountries().remove(editCountry));
        marshall(countries);
    }

    private static synchronized List<Country> unmarshall(){
        Countries countries = new Countries();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Countries.class);
            Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();
            countries = (Countries) jaxUnmarshaller.unmarshal(FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
        return countries.getCountries();
    }

    private static synchronized void marshall(Countries countries){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Countries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(countries, FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }
}
