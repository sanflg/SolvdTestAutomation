package com.solvd.jaxB.wrappers.individual;


import com.solvd.jaxB.models.individual.IndividualStatus;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

@XmlRootElement(name = "individualstatuses")
@XmlAccessorType (XmlAccessType.FIELD)
public class IndividualStatuses{
    private final File FILE = new File("src/main/resources/xml/individualstatuses.xml");

    @XmlElement(name = "individualstatus")
    private List<IndividualStatus> individualstatuses = null;

    public File getFILE() {
        return FILE;
    }

    public List<IndividualStatus> getIndividualStatuses() {
        return individualstatuses;
    }

    public void setIndividualStatuses(List<IndividualStatus> individualstatuses) {
        this.individualstatuses = individualstatuses;
    }
}
