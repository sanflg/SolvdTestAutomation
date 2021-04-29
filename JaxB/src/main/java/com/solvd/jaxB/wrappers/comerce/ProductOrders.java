package com.solvd.jaxB.wrappers.comerce;

import com.solvd.jaxB.models.comerce.ProductOrder;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

@XmlRootElement(name = "productorders")
@XmlAccessorType (XmlAccessType.FIELD)
public class ProductOrders{
    private final File FILE = new File("src/main/resources/xml/productorders.xml");

    @XmlElement(name = "productorder")
    private List<ProductOrder> productorders = null;

    public File getFILE() {
        return FILE;
    }

    public List<ProductOrder> getProductOrders() {
        return productorders;
    }

    public void setProductOrders(List<ProductOrder> productorders) {
        this.productorders = productorders;
    }
}
