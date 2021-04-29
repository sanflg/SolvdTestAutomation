package com.solvd.jaxB.dao.jaxB.impl.location;

import com.solvd.jaxB.dao.interfaces.location.ICityDAO;
import com.solvd.jaxB.models.location.City;
import com.solvd.jaxB.models.location.City;
import com.solvd.jaxB.wrappers.location.Cities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class CityDAO implements ICityDAO {
    private static final Logger logger = LogManager.getLogger(CityDAO.class);
    private static final File FILE = new File("src/main/resources/xml/cities.xml");

    @Override
    public void create(City city) {
        Cities cities = new Cities();
        cities.setCities(unmarshall());
        cities.getCities().add(city);
        marshall(cities);
    }

    @Override
    public City getByID(int id) {
        Cities cities = new Cities();
        cities.setCities(unmarshall());
        return cities.getCities().stream()
                .filter(city -> city.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(City city) {
        Cities cities = new Cities();
        cities.setCities(unmarshall());
        cities.getCities().stream()
                .filter(cityStream -> cityStream.getId() == city.getId())
                .findAny()
                .ifPresent(editCity ->{
                    editCity.setName(city.getName());
                    editCity.setStateId(city.getStateId());
                });
        marshall(cities);
    }

    @Override
    public void delete(int id) {
        Cities cities = new Cities();
        cities.setCities(unmarshall());
        cities.getCities().stream()
                .filter(cityStream -> cityStream.getId() == id)
                .findAny()
                .ifPresent(editCity ->
                        cities.getCities().remove(editCity));
        marshall(cities);
    }

    private static synchronized List<City> unmarshall(){
        Cities cities = new Cities();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Cities.class);
            Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();
            cities = (Cities) jaxUnmarshaller.unmarshal(FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
        return cities.getCities();
    }

    private static synchronized void marshall(Cities cities){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Cities.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(cities, FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }
}
