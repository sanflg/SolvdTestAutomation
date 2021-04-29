package com.solvd.jaxB.dao.jaxB.impl.individual;

import com.solvd.jaxB.dao.interfaces.individual.IIndividualStatusesDAO;

import com.solvd.jaxB.models.individual.IndividualStatus;
import com.solvd.jaxB.wrappers.individual.IndividualStatuses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class IndividualStatusDAO implements IIndividualStatusesDAO {
    private static final Logger logger = LogManager.getLogger(IndividualStatusDAO.class);
    private static final File FILE = new File("src/main/resources/xml/individualstatuses.xml");

    @Override
    public void create(IndividualStatus individualstatus) {
        IndividualStatuses individualstatuses = new IndividualStatuses();
        individualstatuses.setIndividualStatuses(unmarshall());
        individualstatuses.getIndividualStatuses().add(individualstatus);
        marshall(individualstatuses);
    }

    @Override
    public IndividualStatus getByID(int id) {
        IndividualStatuses individualstatuses = new IndividualStatuses();
        individualstatuses.setIndividualStatuses(unmarshall());
        return individualstatuses.getIndividualStatuses().stream()
                .filter(individualstatus -> individualstatus.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(IndividualStatus individualstatus) {
        IndividualStatuses individualstatuses = new IndividualStatuses();
        individualstatuses.setIndividualStatuses(unmarshall());
        individualstatuses.getIndividualStatuses().stream()
                .filter(individualstatusStream -> individualstatusStream.getId() == individualstatus.getId())
                .findAny()
                .ifPresent(editIndividualStatus ->{
                    editIndividualStatus.setAdmin(individualstatus.isAdmin());
                    editIndividualStatus.setNew(individualstatus.isNew());
                    editIndividualStatus.setBanned(individualstatus.isBanned());
                    editIndividualStatus.setIndividualId(individualstatus.getIndividualId());
                });
        marshall(individualstatuses);
    }

    @Override
    public void delete(int id) {
        IndividualStatuses individualstatuses = new IndividualStatuses();
        individualstatuses.setIndividualStatuses(unmarshall());
        individualstatuses.getIndividualStatuses().stream()
                .filter(individualstatusStream -> individualstatusStream.getId() == id)
                .findAny()
                .ifPresent(editIndividualStatus ->
                        individualstatuses.getIndividualStatuses().remove(editIndividualStatus));
        marshall(individualstatuses);
    }

    private static synchronized List<IndividualStatus> unmarshall(){
        IndividualStatuses individualstatuses = new IndividualStatuses();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(IndividualStatuses.class);
            Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();
            individualstatuses = (IndividualStatuses) jaxUnmarshaller.unmarshal(FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
        return individualstatuses.getIndividualStatuses();
    }

    private static synchronized void marshall(IndividualStatuses individualstatuses){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(IndividualStatuses.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(individualstatuses, FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }
}
