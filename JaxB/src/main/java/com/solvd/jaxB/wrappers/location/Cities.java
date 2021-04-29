package com.solvd.jaxB.wrappers.location;


import com.solvd.jaxB.models.location.City;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

@XmlRootElement(name = "cities")
@XmlAccessorType (XmlAccessType.FIELD)
public class Cities{
    private final File FILE = new File("src/main/resources/xml/cities.xml");

    @XmlElement(name = "city")
    private List<City> cities = null;

    public File getFILE() {
        return FILE;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
