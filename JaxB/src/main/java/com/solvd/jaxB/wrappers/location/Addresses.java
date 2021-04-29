package com.solvd.jaxB.wrappers.location;


import com.solvd.jaxB.models.location.Address;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

@XmlRootElement(name = "addresses")
@XmlAccessorType (XmlAccessType.FIELD)
public class Addresses{
    private final File FILE = new File("src/main/resources/xml/addresses.xml");

    @XmlElement(name = "address")
    private List<Address> addresses = null;

    public File getFILE() {
        return FILE;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
