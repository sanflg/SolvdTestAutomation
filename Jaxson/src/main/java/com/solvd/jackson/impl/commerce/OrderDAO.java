package com.solvd.jackson.impl.commerce;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.jackson.interfaces.commerce.IOrderDAO;
import com.solvd.jackson.models.commerce.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements IOrderDAO {
    private static final Logger logger = LogManager.getLogger(OrderDAO.class);
    private static final File FILE = new File("src/main/resources/json/orders.json");
    List<Order> orders = new ArrayList<>();

    @Override
    public void create(Order order) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            orders = objectMapper.readValue(FILE, new TypeReference<List<Order>>(){});
            orders.add(order);
            objectMapper.writeValue(FILE, orders);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public Order getByID(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            orders = objectMapper.readValue(FILE, new TypeReference<List<Order>>(){});
        } catch (IOException e){
            logger.error(e);
        }
        return orders.stream()
                .filter(order -> order.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(Order order) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            orders = objectMapper.readValue(FILE, new TypeReference<List<Order>>(){});
            orders.stream()
                    .filter(orderElement -> orderElement.getId() == order.getId())
                    .findAny()
                    .ifPresent(updateOrder -> updateOrder.setCartId(order.getCartId()));
            objectMapper.writeValue(FILE, orders);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public void delete(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            orders = objectMapper.readValue(FILE, new TypeReference<List<Order>>(){});
            orders.stream()
                    .filter(orderElement -> orderElement.getId() == id)
                    .findAny()
                    .ifPresent(deleteOrder -> orders.remove(deleteOrder));
        } catch (IOException e){
            logger.error(e);
        }
    }
}
