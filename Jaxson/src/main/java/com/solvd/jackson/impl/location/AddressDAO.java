package com.solvd.jackson.impl.location;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.jackson.interfaces.location.IAddressDAO;
import com.solvd.jackson.models.location.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO implements IAddressDAO {
    private static final Logger logger = LogManager.getLogger(AddressDAO.class);
    private static final File FILE = new File("src/main/resources/json/addresses.json");
    List<Address> addresses = new ArrayList<>();

    @Override
    public void create(Address address) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            addresses = objectMapper.readValue(FILE, new TypeReference<List<Address>>(){});
            addresses.add(address);
            objectMapper.writeValue(FILE, addresses);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public Address getByID(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            addresses = objectMapper.readValue(FILE, new TypeReference<List<Address>>(){});
        } catch (IOException e){
            logger.error(e);
        }
        return addresses.stream()
                .filter(address -> address.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(Address address) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            addresses = objectMapper.readValue(FILE, new TypeReference<List<Address>>(){});
            addresses.stream()
                    .filter(addressElement -> addressElement.getId() == address.getId())
                    .findAny()
                    .ifPresent(updateAddress -> {
                        updateAddress.setName(address.getName());
                        updateAddress.setCityId(address.getCity());
                    });
            objectMapper.writeValue(FILE, addresses);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public void delete(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            addresses = objectMapper.readValue(FILE, new TypeReference<List<Address>>(){});
            addresses.stream()
                    .filter(addressElement -> addressElement.getId() == id)
                    .findAny()
                    .ifPresent(deleteAddress -> addresses.remove(deleteAddress));
        } catch (IOException e){
            logger.error(e);
        }
    }
}
