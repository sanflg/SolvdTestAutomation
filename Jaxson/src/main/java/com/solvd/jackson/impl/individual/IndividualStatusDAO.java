package com.solvd.jackson.impl.individual;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.jackson.interfaces.individual.IIndividualStatusDAO;
import com.solvd.jackson.models.individual.IndividualStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndividualStatusDAO implements IIndividualStatusDAO {
    private static final Logger logger = LogManager.getLogger(IndividualStatusDAO.class);
    private static final File FILE = new File("src/main/resources/json/individualstatuses.json");
    List<IndividualStatus> individualStatuses = new ArrayList<>();

    @Override
    public void create(IndividualStatus individualStatus) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            individualStatuses = objectMapper.readValue(FILE, new TypeReference<List<IndividualStatus>>(){});
            individualStatuses.add(individualStatus);
            objectMapper.writeValue(FILE, individualStatuses);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public IndividualStatus getByID(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            individualStatuses = objectMapper.readValue(FILE, new TypeReference<List<IndividualStatus>>(){});
        } catch (IOException e){
            logger.error(e);
        }
        return individualStatuses.stream()
                .filter(individualStatus -> individualStatus.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(IndividualStatus individualStatus) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            individualStatuses = objectMapper.readValue(FILE, new TypeReference<List<IndividualStatus>>(){});
            individualStatuses.stream()
                    .filter(individualElement -> individualElement.getId() == individualStatus.getId())
                    .findAny()
                    .ifPresent(updateIndividualStatus -> {
                        updateIndividualStatus.setAdmin(individualStatus.isAdmin());
                        updateIndividualStatus.setNew(individualStatus.isNew());
                        updateIndividualStatus.setBanned(individualStatus.isBanned());
                    });
            objectMapper.writeValue(FILE, individualStatuses);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public void delete(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            individualStatuses = objectMapper.readValue(FILE, new TypeReference<List<IndividualStatus>>(){});
            individualStatuses.stream()
                    .filter(individualElement -> individualElement.getId() == id)
                    .findAny()
                    .ifPresent(deleteIndividualStatus -> individualStatuses.remove(deleteIndividualStatus));
        } catch (IOException e){
            logger.error(e);
        }
    }
}
