package com.solvd.jackson.impl.commerce;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.jackson.interfaces.commerce.ICartDAO;
import com.solvd.jackson.models.commerce.Cart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartDAO implements ICartDAO {
    private static final Logger logger = LogManager.getLogger(CartDAO.class);
    private static final File FILE = new File("src/main/resources/json/carts.json");
    List<Cart> carts = new ArrayList<>();

    @Override
    public void create(Cart city) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            carts = objectMapper.readValue(FILE, new TypeReference<List<Cart>>(){});
            carts.add(city);
            objectMapper.writeValue(FILE, carts);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public Cart getByID(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            carts = objectMapper.readValue(FILE, new TypeReference<List<Cart>>(){});
        } catch (IOException e){
            logger.error(e);
        }
        return carts.stream().filter(city -> city.getId()==id).findAny().orElse(null);
    }

    @Override
    public void update(Cart city) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            carts = objectMapper.readValue(FILE, new TypeReference<List<Cart>>(){});
            carts.stream()
                    .filter(cityElement -> cityElement.getId() == city.getId())
                    .findAny()
                    .ifPresent(updateCart -> updateCart.setIndividualId(city.getIndividualId()));
            objectMapper.writeValue(FILE, carts);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public void delete(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            carts = objectMapper.readValue(FILE, new TypeReference<List<Cart>>(){});
            carts.stream()
                    .filter(cityElement -> cityElement.getId() == id)
                    .findAny()
                    .ifPresent(deleteCart -> carts.remove(deleteCart));
        } catch (IOException e){
            logger.error(e);
        }
    }
}
