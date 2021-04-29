package com.solvd.jaxB.dao.jaxB.impl.individual;

import com.solvd.jaxB.dao.interfaces.individual.IIndividualAddressDAO;
import com.solvd.jaxB.models.individual.IndividualAddress;
import com.solvd.jaxB.wrappers.individual.IndividualAddresses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class IndividualAddressDAO implements IIndividualAddressDAO {
    private static final Logger logger = LogManager.getLogger(IndividualAddressDAO.class);
    private static final File FILE = new File("src/main/resources/xml/individualaddresses.xml");

    @Override
    public void create(IndividualAddress individualaddress) {
        IndividualAddresses individualaddresses = new IndividualAddresses();
        individualaddresses.setIndividualAddresses(unmarshall());
        individualaddresses.getIndividualAddresses().add(individualaddress);
        marshall(individualaddresses);
    }

    @Override
    public IndividualAddress getByID(int id) {
        IndividualAddresses individualaddresses = new IndividualAddresses();
        individualaddresses.setIndividualAddresses(unmarshall());
        return individualaddresses.getIndividualAddresses().stream()
                .filter(individualaddress -> individualaddress.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(IndividualAddress individualaddress) {
        IndividualAddresses individualaddresses = new IndividualAddresses();
        individualaddresses.setIndividualAddresses(unmarshall());
        individualaddresses.getIndividualAddresses().stream()
                .filter(individualaddressStream -> individualaddressStream.getId() == individualaddress.getId())
                .findAny()
                .ifPresent(editIndividualAddress ->{
                    editIndividualAddress.setNumber(individualaddress.getNumber());
                    editIndividualAddress.setAddressId(individualaddress.getAddressId());
                    editIndividualAddress.setIndividualId((individualaddress.getIndividualId()));
                });
        marshall(individualaddresses);
    }

    @Override
    public void delete(int id) {
        IndividualAddresses individualaddresses = new IndividualAddresses();
        individualaddresses.setIndividualAddresses(unmarshall());
        individualaddresses.getIndividualAddresses().stream()
                .filter(individualaddressStream -> individualaddressStream.getId() == id)
                .findAny()
                .ifPresent(editIndividualAddress ->
                        individualaddresses.getIndividualAddresses().remove(editIndividualAddress));
        marshall(individualaddresses);
    }

    private static synchronized List<IndividualAddress> unmarshall(){
        IndividualAddresses individualaddresses = new IndividualAddresses();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(IndividualAddresses.class);
            Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();
            individualaddresses = (IndividualAddresses) jaxUnmarshaller.unmarshal(FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
        return individualaddresses.getIndividualAddresses();
    }

    private static synchronized void marshall(IndividualAddresses individualaddresses){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(IndividualAddresses.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(individualaddresses, FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }
}
