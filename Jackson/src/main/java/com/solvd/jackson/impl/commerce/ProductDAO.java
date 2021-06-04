package com.solvd.jackson.impl.commerce;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.jackson.interfaces.commerce.IProductDAO;
import com.solvd.jackson.models.commerce.Product;
import com.solvd.jackson.models.commerce.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    private static final Logger logger = LogManager.getLogger(ProductDAO.class);
    private static final File FILE = new File("src/main/resources/json/products.json");
    List<Product> products = new ArrayList<>();

    @Override
    public void create(Product product) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            products = objectMapper.readValue(FILE, new TypeReference<List<Product>>(){});
            products.add(product);
            objectMapper.writeValue(FILE, products);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public Product getByID(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            products = objectMapper.readValue(FILE, new TypeReference<List<Product>>(){});
        } catch (IOException e){
            logger.error(e);
        }
        return products.stream()
                .filter(product -> product.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(Product product) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            products = objectMapper.readValue(FILE, new TypeReference<List<Product>>(){});
            products.stream()
                    .filter(productElement -> productElement.getId() == product.getId())
                    .findAny()
                    .ifPresent(updateProduct -> {
                        updateProduct.setName(product.getName());
                        updateProduct.setPrice(product.getPrice());
                        updateProduct.setDescription(product.getDescription());
                        updateProduct.setCategoryId(product.getCategoryId());
                        updateProduct.setCurrencyId(product.getCurrencyId());
                        updateProduct.setSupplierId(product.getSupplierId());
                    });
            objectMapper.writeValue(FILE, products);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public void delete(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            products = objectMapper.readValue(FILE, new TypeReference<List<Product>>(){});
            products.stream()
                    .filter(productElement -> productElement.getId() == id)
                    .findAny()
                    .ifPresent(deleteProduct -> products.remove(deleteProduct));
        } catch (IOException e){
            logger.error(e);
        }
    }
}
