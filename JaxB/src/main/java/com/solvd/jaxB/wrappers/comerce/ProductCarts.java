package com.solvd.jaxB.wrappers.comerce;

import com.solvd.jaxB.models.comerce.ProductCart;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;

@XmlRootElement(name = "productcarts")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductCarts{
    private final File FILE = new File("src/main/resources/xml/productcarts.xml");

    @XmlElement(name = "productcart")
    private List<ProductCart> productcarts = null;

    public File getFILE() {
        return FILE;
    }

    public List<ProductCart> getProductCarts() {
        return productcarts;
    }

    public void setProductCarts(List<ProductCart> productcarts) {
        this.productcarts = productcarts;
    }
}
