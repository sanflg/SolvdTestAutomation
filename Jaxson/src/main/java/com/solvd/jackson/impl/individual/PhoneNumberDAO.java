package com.solvd.jackson.impl.individual;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.jackson.interfaces.individual.IPhoneNumberDAO;
import com.solvd.jackson.models.individual.PhoneNumber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhoneNumberDAO implements IPhoneNumberDAO {
    private static final Logger logger = LogManager.getLogger(PhoneNumberDAO.class);
    private static final File FILE = new File("src/main/resources/json/phonenumbers.json");
    List<PhoneNumber> phoneNumbers = new ArrayList<>();

    @Override
    public void create(PhoneNumber phoneNumber) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            phoneNumbers = objectMapper.readValue(FILE, new TypeReference<List<PhoneNumber>>(){});
            phoneNumbers.add(phoneNumber);
            objectMapper.writeValue(FILE, phoneNumbers);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public PhoneNumber getByID(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            phoneNumbers = objectMapper.readValue(FILE, new TypeReference<List<PhoneNumber>>(){});
        } catch (IOException e){
            logger.error(e);
        }
        return phoneNumbers.stream()
                .filter(phoneNumber -> phoneNumber.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(PhoneNumber phoneNumber) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            phoneNumbers = objectMapper.readValue(FILE, new TypeReference<List<PhoneNumber>>(){});
            phoneNumbers.stream()
                    .filter(phoneNumberElement -> phoneNumberElement.getId() == phoneNumber.getId())
                    .findAny()
                    .ifPresent(updatePhoneNumber -> {
                        updatePhoneNumber.setNumber(phoneNumber.getNumber());
                        updatePhoneNumber.setIndividualId(phoneNumber.getIndividualId());
                    });
            objectMapper.writeValue(FILE, phoneNumbers);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public void delete(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            phoneNumbers = objectMapper.readValue(FILE, new TypeReference<List<PhoneNumber>>(){});
            phoneNumbers.stream()
                    .filter(phoneNumberElement -> phoneNumberElement.getId() == id)
                    .findAny()
                    .ifPresent(deletePhoneNumber -> phoneNumbers.remove(deletePhoneNumber));
        } catch (IOException e){
            logger.error(e);
        }
    }
}
