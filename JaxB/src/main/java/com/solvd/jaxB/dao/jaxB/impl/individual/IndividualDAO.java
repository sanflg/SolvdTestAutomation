package com.solvd.jaxB.dao.jaxB.impl.individual;

import com.solvd.jaxB.dao.interfaces.individual.IIndividualDAO;

import com.solvd.jaxB.models.individual.Individual;
import com.solvd.jaxB.wrappers.individual.Individuals;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class IndividualDAO implements IIndividualDAO {
    private static final Logger logger = LogManager.getLogger(IndividualDAO.class);
    private static final File FILE = new File("src/main/resources/xml/individuals.xml");

    @Override
    public void create(Individual individual) {
        Individuals individuals = new Individuals();
        individuals.setIndividuals(unmarshall());
        individuals.getIndividuals().add(individual);
        marshall(individuals);
    }

    @Override
    public Individual getByID(int id) {
        Individuals individuals = new Individuals();
        individuals.setIndividuals(unmarshall());
        return individuals.getIndividuals().stream()
                .filter(individual -> individual.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(Individual individual) {
        Individuals individuals = new Individuals();
        individuals.setIndividuals(unmarshall());
        individuals.getIndividuals().stream()
                .filter(individualStream -> individualStream.getId() == individual.getId())
                .findAny()
                .ifPresent(editIndividual ->{
                    editIndividual.setUsername(individual.getUsername());
                    editIndividual.setPassword(individual.getPassword());
                    editIndividual.setEmail(individual.getEmail());
                    editIndividual.setFirstName(individual.getFirstName());
                    editIndividual.setLastName(individual.getLastName());
                    editIndividual.setDate(individual.getDate());
                    editIndividual.setLanguageId(individual.getLanguageId());
                });
        marshall(individuals);
    }

    @Override
    public void delete(int id) {
        Individuals individuals = new Individuals();
        individuals.setIndividuals(unmarshall());
        individuals.getIndividuals().stream()
                .filter(individualStream -> individualStream.getId() == id)
                .findAny()
                .ifPresent(editIndividual ->
                        individuals.getIndividuals().remove(editIndividual));
        marshall(individuals);
    }

    private static synchronized List<Individual> unmarshall(){
        Individuals individuals = new Individuals();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Individuals.class);
            Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();
            individuals = (Individuals) jaxUnmarshaller.unmarshal(FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
        return individuals.getIndividuals();
    }

    private static synchronized void marshall(Individuals individuals){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Individuals.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(individuals, FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }
}
