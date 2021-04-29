package com.solvd.jaxB.dao.jaxB.impl.individual;

import com.solvd.jaxB.dao.interfaces.individual.IPhoneNumberDAO;
import com.solvd.jaxB.models.individual.PhoneNumber;
import com.solvd.jaxB.wrappers.individual.PhoneNumbers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class PhoneNumberDAO implements IPhoneNumberDAO {
    private static final Logger logger = LogManager.getLogger(PhoneNumberDAO.class);
    private static final File FILE = new File("src/main/resources/xml/phonenumbers.xml");

    @Override
    public void create(PhoneNumber phonenumber) {
        PhoneNumbers phonenumbers = new PhoneNumbers();
        phonenumbers.setPhoneNumbers(unmarshall());
        phonenumbers.getPhoneNumbers().add(phonenumber);
        marshall(phonenumbers);
    }

    @Override
    public PhoneNumber getByID(int id) {
        PhoneNumbers phonenumbers = new PhoneNumbers();
        phonenumbers.setPhoneNumbers(unmarshall());
        return phonenumbers.getPhoneNumbers().stream()
                .filter(phonenumber -> phonenumber.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(PhoneNumber phonenumber) {
        PhoneNumbers phonenumbers = new PhoneNumbers();
        phonenumbers.setPhoneNumbers(unmarshall());
        phonenumbers.getPhoneNumbers().stream()
                .filter(phonenumberStream -> phonenumberStream.getId() == phonenumber.getId())
                .findAny()
                .ifPresent(editPhoneNumber ->{
                    editPhoneNumber.setNumber(phonenumber.getNumber());
                    editPhoneNumber.setIndividualId(phonenumber.getIndividualId());
                });
        marshall(phonenumbers);
    }

    @Override
    public void delete(int id) {
        PhoneNumbers phonenumbers = new PhoneNumbers();
        phonenumbers.setPhoneNumbers(unmarshall());
        phonenumbers.getPhoneNumbers().stream()
                .filter(phonenumberStream -> phonenumberStream.getId() == id)
                .findAny()
                .ifPresent(editPhoneNumber ->
                        phonenumbers.getPhoneNumbers().remove(editPhoneNumber));
        marshall(phonenumbers);
    }

    private static synchronized List<PhoneNumber> unmarshall(){
        PhoneNumbers phonenumbers = new PhoneNumbers();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PhoneNumbers.class);
            Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();
            phonenumbers = (PhoneNumbers) jaxUnmarshaller.unmarshal(FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
        return phonenumbers.getPhoneNumbers();
    }

    private static synchronized void marshall(PhoneNumbers phonenumbers){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PhoneNumbers.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(phonenumbers, FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }
}
