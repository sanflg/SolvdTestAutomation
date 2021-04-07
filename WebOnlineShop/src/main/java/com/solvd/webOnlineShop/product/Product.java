package com.solvd.webOnlineShop.product;

import com.solvd.webOnlineShop.DatabaseSimulation;
import com.solvd.webOnlineShop.exceptions.NoProductsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Date;
import java.util.Objects;

public class Product {
    private static final Logger logger = LogManager.getLogger(Product.class);

    private final String creationCode;
    private String name;
    private float price;
    private String description;
    private final Date creationDate = new Date();
    private Date lastUpdate = new Date();

    public Product(String name, float price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.creationCode = name + description;
        ProductDAO.getProductDAO().save(this);
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

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreationCode() {
        return creationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getCreationCode().equals(product.getCreationCode()) && getCreationDate().equals(product.getCreationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreationCode(), getCreationDate());
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
}
