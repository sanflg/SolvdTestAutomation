package com.solvd.jaxB.models.commerce;

import javax.xml.bind.annotation.*;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Product{
    @XmlAttribute(name = "id")
    private int id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "price")
    private double price;
    @XmlElement(name = "Suppliers_id")
    private int supplierId;
    @XmlElement(name = "Currencies_id")
    private int currencyId;
    @XmlElement(name = "Categories_id")
    private int categoryId;
    @XmlElement(name = "description")
    private String description;

    public Product(){}

    //Constructor without category or description
    public Product(int id,
                   String name,
                   double price,
                   int supplierId,
                   int currencyId) {
        this.id = id;
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
        this.id = id;
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
        this.id = id;
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
        this.id = id;
        this.name = name;
        this.price = price;
        this.supplierId = supplierId;
        this.currencyId = currencyId;
        this.categoryId = categoryId;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
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
