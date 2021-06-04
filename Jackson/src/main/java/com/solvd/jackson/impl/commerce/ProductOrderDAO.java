package com.solvd.jackson.impl.commerce;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.jackson.interfaces.commerce.IProductOrderDAO;
import com.solvd.jackson.models.commerce.ProductOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductOrderDAO implements IProductOrderDAO {
    private static final Logger logger = LogManager.getLogger(ProductOrderDAO.class);
    private static final File FILE = new File("src/main/resources/json/productorders.json");
    List<ProductOrder> productOrders = new ArrayList<>();

    @Override
    public void create(ProductOrder productOrder) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            productOrders = objectMapper.readValue(FILE, new TypeReference<List<ProductOrder>>(){});
            productOrders.add(productOrder);
            objectMapper.writeValue(FILE, productOrders);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public ProductOrder getByID(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            productOrders = objectMapper.readValue(FILE, new TypeReference<List<ProductOrder>>(){});
        } catch (IOException e){
            logger.error(e);
        }
        return productOrders.stream()
                .filter(productOrder -> productOrder.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(ProductOrder productOrder) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            productOrders = objectMapper.readValue(FILE, new TypeReference<List<ProductOrder>>(){});
            productOrders.stream()
                    .filter(productElement -> productElement.getId() == productOrder.getId())
                    .findAny()
                    .ifPresent(updateProductOrder -> {
                        updateProductOrder.setOrderId(productOrder.getOrderId());
                        updateProductOrder.setProductId(productOrder.getOrderId());
                    });
            objectMapper.writeValue(FILE, productOrders);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public void delete(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            productOrders = objectMapper.readValue(FILE, new TypeReference<List<ProductOrder>>(){});
            productOrders.stream()
                    .filter(productOrderElement -> productOrderElement.getId() == id)
                    .findAny()
                    .ifPresent(deleteProductOrder -> productOrders.remove(deleteProductOrder));
        } catch (IOException e){
            logger.error(e);
        }
    }
}
