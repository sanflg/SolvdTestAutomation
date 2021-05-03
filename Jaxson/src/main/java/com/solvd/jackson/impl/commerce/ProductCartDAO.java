package com.solvd.jackson.impl.commerce;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.jackson.interfaces.commerce.IProductCartDAO;
import com.solvd.jackson.models.commerce.ProductCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductCartDAO implements IProductCartDAO {
    private static final Logger logger = LogManager.getLogger(ProductCartDAO.class);
    private static final File FILE = new File("src/main/resources/json/productcarts.json");
    List<ProductCart> productCarts = new ArrayList<>();

    @Override
    public void create(ProductCart productCart) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            productCarts = objectMapper.readValue(FILE, new TypeReference<List<ProductCart>>(){});
            productCarts.add(productCart);
            objectMapper.writeValue(FILE, productCarts);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public ProductCart getByID(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            productCarts = objectMapper.readValue(FILE, new TypeReference<List<ProductCart>>(){});
        } catch (IOException e){
            logger.error(e);
        }
        return productCarts.stream()
                .filter(productCart -> productCart.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(ProductCart productCart) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            productCarts = objectMapper.readValue(FILE, new TypeReference<List<ProductCart>>(){});
            productCarts.stream()
                    .filter(productCartElement -> productCartElement.getId() == productCart.getId())
                    .findAny()
                    .ifPresent(updateProductCart -> {
                        updateProductCart.setQuantity(productCart.getQuantity());
                        updateProductCart.setCartId(productCart.getCartId());
                        updateProductCart.setProductId(productCart.getCartId());
                    });
            objectMapper.writeValue(FILE, productCarts);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public void delete(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            productCarts = objectMapper.readValue(FILE, new TypeReference<List<ProductCart>>(){});
            productCarts.stream()
                    .filter(productCartElement -> productCartElement.getId() == id)
                    .findAny()
                    .ifPresent(deleteProductCart -> productCarts.remove(deleteProductCart));
        } catch (IOException e){
            logger.error(e);
        }
    }
}
