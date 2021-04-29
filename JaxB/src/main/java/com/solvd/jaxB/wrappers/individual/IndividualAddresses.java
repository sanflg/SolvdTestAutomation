package com.solvd.jaxB.wrappers.individual;


import com.solvd.jaxB.models.individual.IndividualAddress;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

@XmlRootElement(name = "individualaddresses")
@XmlAccessorType (XmlAccessType.FIELD)
public class IndividualAddresses{
    private final File FILE = new File("src/main/resources/xml/individualaddresses.xml");

    @XmlElement(name = "individualaddress")
    private List<IndividualAddress> individualaddresses = null;

    public File getFILE() {
        return FILE;
    }

    public List<IndividualAddress> getIndividualAddresses() {
        return individualaddresses;
    }

    public void setIndividualAddresses(List<IndividualAddress> individualaddresses) {
        this.individualaddresses = individualaddresses;
    }
}
