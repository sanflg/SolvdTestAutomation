package com.solvd.jaxB.wrappers.location;

import com.solvd.jaxB.models.location.Country;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

@XmlRootElement(name = "countries")
@XmlAccessorType (XmlAccessType.FIELD)
public class Countries{
    private final File FILE = new File("src/main/resources/xml/countries.xml");

    @XmlElement(name = "country")
    private List<Country> countries = null;

    public File getFILE() {
        return FILE;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
