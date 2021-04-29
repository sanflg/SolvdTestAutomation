package com.solvd.jaxB.dao.jaxB.impl.location;

import com.solvd.jaxB.dao.interfaces.location.IAddressDAO;

import com.solvd.jaxB.models.location.Address;
import com.solvd.jaxB.wrappers.location.Addresses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class AddressDAO implements IAddressDAO {
    private static final Logger logger = LogManager.getLogger(AddressDAO.class);
    private static final File FILE = new File("src/main/resources/xml/addresses.xml");

    @Override
    public void create(Address address) {
        Addresses addresses = new Addresses();
        addresses.setAddresses(unmarshall());
        addresses.getAddresses().add(address);
        marshall(addresses);
    }

    @Override
    public Address getByID(int id) {
        Addresses addresses = new Addresses();
        addresses.setAddresses(unmarshall());
        return addresses.getAddresses().stream()
                .filter(address -> address.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(Address address) {
        Addresses addresses = new Addresses();
        addresses.setAddresses(unmarshall());
        addresses.getAddresses().stream()
                .filter(addressStream -> addressStream.getId() == address.getId())
                .findAny()
                .ifPresent(editAddress ->{
                    editAddress.setName(address.getName());
                    editAddress.setCityId(address.getCityId());
                });
        marshall(addresses);
    }

    @Override
    public void delete(int id) {
        Addresses addresses = new Addresses();
        addresses.setAddresses(unmarshall());
        addresses.getAddresses().stream()
                .filter(addressStream -> addressStream.getId() == id)
                .findAny()
                .ifPresent(editAddress ->
                        addresses.getAddresses().remove(editAddress));
        marshall(addresses);
    }

    private static synchronized List<Address> unmarshall(){
        Addresses addresses = new Addresses();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Addresses.class);
            Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();
            addresses = (Addresses) jaxUnmarshaller.unmarshal(FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
        return addresses.getAddresses();
    }

    private static synchronized void marshall(Addresses addresses){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Addresses.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(addresses, FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }
}
