package com.solvd.jackson.impl.individual;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.jackson.interfaces.individual.IIndividualAddressDAO;
import com.solvd.jackson.models.individual.IndividualAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndividualAddressDAO implements IIndividualAddressDAO {
    private static final Logger logger = LogManager.getLogger(IndividualAddressDAO.class);
    private static final File FILE = new File("src/main/resources/json/individualaddresses.json");
    List<IndividualAddress> individualAddresses = new ArrayList<>();

    @Override
    public void create(IndividualAddress individualAddress) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            individualAddresses = objectMapper.readValue(FILE, new TypeReference<List<IndividualAddress>>(){});
            individualAddresses.add(individualAddress);
            objectMapper.writeValue(FILE, individualAddresses);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public IndividualAddress getByID(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            individualAddresses = objectMapper.readValue(FILE, new TypeReference<List<IndividualAddress>>(){});
        } catch (IOException e){
            logger.error(e);
        }
        return individualAddresses.stream()
                .filter(individualAddress -> individualAddress.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(IndividualAddress individualAddress) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            individualAddresses = objectMapper.readValue(FILE, new TypeReference<List<IndividualAddress>>(){});
            individualAddresses.stream()
                    .filter(individualAddressElement -> individualAddressElement.getId() == individualAddress.getId())
                    .findAny()
                    .ifPresent(updateIndividualAddress -> {
                        updateIndividualAddress.setNumber(individualAddress.getNumber());
                        updateIndividualAddress.setAddressId(individualAddress.getAddress());
                        updateIndividualAddress.setIndividualId(individualAddress.getIndividual());
                    });
            objectMapper.writeValue(FILE, individualAddresses);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public void delete(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            individualAddresses = objectMapper.readValue(FILE, new TypeReference<List<IndividualAddress>>(){});
            individualAddresses.stream()
                    .filter(individualAddressElement -> individualAddressElement.getId() == id)
                    .findAny()
                    .ifPresent(deleteIndividualAddress -> individualAddresses.remove(deleteIndividualAddress));
        } catch (IOException e){
            logger.error(e);
        }
    }
}
