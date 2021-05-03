package com.solvd.dataBaseOnlineShop.models.commerce;

import com.solvd.dataBaseOnlineShop.models.AbstractEntity;

import java.util.Objects;

public class Product extends AbstractEntity {
    private String name;
    private double price;
    private int supplierId;
    private int currencyId;
    private int categoryId;
    private String description;

    public Product(){}

    //Constructor without category or description
    public Product(int id,
                   String name,
                   double price,
                   int supplierId,
                   int currencyId) {
        super(id);
        this.name = name;
        this.price = price;
        this.supplierId = supplierId;
        this.currencyId = currencyId;
    }

    //Constructor without category
    public Product(int id,
                   String name,
                   double price,
                   int supplierId,
                   int currencyId,
                   int categoryId) {
        super(id);
        this.name = name;
        this.price = price;
        this.supplierId = supplierId;
        this.currencyId = currencyId;
        this.categoryId = categoryId;
    }

    //Constructor without description
    public Product(int id,
                   String name,
                   double price,
                   int supplierId,
                   int currencyId,
                   String description) {
        super(id);
        this.name = name;
        this.price = price;
        this.supplierId = supplierId;
        this.currencyId = currencyId;
        this.description = description;
    }

    //Full constructor
    public Product(int id,
                   String name,
                   double price,
                   int supplierId,
                   int currencyId,
                   int categoryId,
                   String description) {
        super(id);
        this.name = name;
        this.price = price;
        this.supplierId = supplierId;
        this.currencyId = currencyId;
        this.categoryId = categoryId;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", supplierId=" + supplierId +
                ", currencyId=" + currencyId +
                ", categoryId=" + categoryId +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 &&
                getSupplierId() == product.getSupplierId() &&
                getCurrencyId() == product.getCurrencyId() &&
                getName().equals(product.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(),
                getPrice(),
                getSupplierId(),
                getCurrencyId());
    }
}
