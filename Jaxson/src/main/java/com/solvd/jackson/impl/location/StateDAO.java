package com.solvd.jackson.impl.location;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.jackson.interfaces.location.IStateDAO;
import com.solvd.jackson.models.location.State;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StateDAO implements IStateDAO {
    private static final Logger logger = LogManager.getLogger(StateDAO.class);
    private static final File FILE = new File("src/main/resources/json/states.json");
    List<State> states = new ArrayList<>();

    @Override
    public void create(State state) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            states = objectMapper.readValue(FILE, new TypeReference<List<State>>(){});
            states.add(state);
            objectMapper.writeValue(FILE, states);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public State getByID(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            states = objectMapper.readValue(FILE, new TypeReference<List<State>>(){});
        } catch (IOException e){
            logger.error(e);
        }
        return states.stream()
                .filter(state -> state.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(State state) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            states = objectMapper.readValue(FILE, new TypeReference<List<State>>(){});
            states.stream()
                    .filter(stateElement -> stateElement.getId() == state.getId())
                    .findAny()
                    .ifPresent(updateState -> {
                        updateState.setName(state.getName());
                        updateState.setCountryId(state.getCountry());
                    });
            objectMapper.writeValue(FILE, states);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public void delete(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            states = objectMapper.readValue(FILE, new TypeReference<List<State>>(){});
            states.stream()
                    .filter(stateElement -> stateElement.getId() == id)
                    .findAny()
                    .ifPresent(deleteState -> states.remove(deleteState));
        } catch (IOException e){
            logger.error(e);
        }
    }
}
