package com.solvd.jaxB.wrappers.commerce;

import com.solvd.jaxB.models.commerce.Product;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class Products{
    private final File FILE = new File("src/main/resources/xml/products.xml");

    @XmlElement(name = "product")
    private List<Product> products = null;

    public File getFILE() {
        return FILE;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
