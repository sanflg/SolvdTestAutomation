package com.solvd.jackson.impl.individual;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.jackson.interfaces.individual.IIndividualDAO;
import com.solvd.jackson.models.individual.Individual;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndividualDAO implements IIndividualDAO {
    private static final Logger logger = LogManager.getLogger(IndividualDAO.class);
    private static final File FILE = new File("src/main/resources/json/individuals.json");
    List<Individual> individuals = new ArrayList<>();

    @Override
    public void create(Individual individual) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            individuals = objectMapper.readValue(FILE, new TypeReference<List<Individual>>(){});
            individuals.add(individual);
            objectMapper.writeValue(FILE, individuals);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public Individual getByID(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            individuals = objectMapper.readValue(FILE, new TypeReference<List<Individual>>(){});
        } catch (IOException e){
            logger.error(e);
        }
        return individuals.stream()
                .filter(individual -> individual.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(Individual individual) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            individuals = objectMapper.readValue(FILE, new TypeReference<List<Individual>>(){});
            individuals.stream()
                    .filter(individualElement -> individualElement.getId() == individual.getId())
                    .findAny()
                    .ifPresent(updateIndividual -> {
                        updateIndividual.setUsername(individual.getUsername());
                        updateIndividual.setPassword(individual.getPassword());
                        updateIndividual.setEmail(individual.getEmail());
                        updateIndividual.setFirstName(individual.getFirstName());
                        updateIndividual.setLastName(individual.getLastName());
                        updateIndividual.setDate(individual.getDate());
                        updateIndividual.setLanguageId(individual.getLanguage());
                    });
            objectMapper.writeValue(FILE, individuals);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public void delete(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            individuals = objectMapper.readValue(FILE, new TypeReference<List<Individual>>(){});
            individuals.stream()
                    .filter(individualElement -> individualElement.getId() == id)
                    .findAny()
                    .ifPresent(deleteIndividual -> individuals.remove(deleteIndividual));
        } catch (IOException e){
            logger.error(e);
        }
    }
}
