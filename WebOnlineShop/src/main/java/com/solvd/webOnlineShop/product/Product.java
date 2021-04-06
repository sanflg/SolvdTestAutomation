package com.solvd.webOnlineShop.product;

import com.solvd.webOnlineShop.DatabaseSimulation;
import com.solvd.webOnlineShop.exceptions.NoObjectException;
import com.solvd.webOnlineShop.exceptions.NoProductsException;
import com.solvd.webOnlineShop.generics.AbstractDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Objects;

public class Product implements AbstractDAO<Product, String> {
    private static final Logger logger = LogManager.getLogger(Product.class);

    private static final int DIFFERENCE = 5;
    private String name;
    private float price;
    private String description;

    public Product(String name, float price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        DatabaseSimulation.getProductList().add(this);
        logger.info("New product created");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object newProduct) {
        if (this == newProduct) return true;
        if (newProduct == null || getClass() != newProduct.getClass()) return false;
        Product product = (Product) newProduct;
        return Float.compare(product.getPrice(), getPrice()) == 0 && getName().equals(product.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice()) + DIFFERENCE;
    }

    @Override
    public String toString() {
        return name +
                ", $ " + price +
                ", description:'" + description;
    }

    public static void printAllProducts() throws NoProductsException{

        logger.info("Retrieving all products");

        if(DatabaseSimulation.getProductList().size() == 0){
            throw new NoProductsException("NoProductsException Occurred: ");
        }
        DatabaseSimulation.getProductList().forEach(product -> logger.info(product.toString()));
    }

    @Override
    public void save(Product product) {
        DatabaseSimulation.getProductList().add(product);
    }

    @Override
    public void remove(Product product) throws NoObjectException{
        if (DatabaseSimulation.getProductList().contains(product)){
            DatabaseSimulation.getProductList().remove(product);
        }
        throw new NoObjectException("NoObjectException Occurred: ");
    }
}
