package com.solvd.jaxB.wrappers.individual;

import com.solvd.jaxB.models.individual.Individual;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

@XmlRootElement(name = "individuals")
@XmlAccessorType (XmlAccessType.FIELD)
public class Individuals{
    private final File FILE = new File("src/main/resources/xml/individuals.xml");

    @XmlElement(name = "individual")
    private List<Individual> individuals = null;

    public File getFILE() {
        return FILE;
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(List<Individual> individuals) {
        this.individuals = individuals;
    }
}
