package com.solvd.jaxB.wrappers.comerce;

import com.solvd.jaxB.models.comerce.Cart;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

@XmlRootElement(name = "carts")
@XmlAccessorType (XmlAccessType.FIELD)
public class Carts{
    private final File FILE = new File("src/main/resources/xml/carts.xml");

    @XmlElement(name = "cart")
    private List<Cart> carts = null;

    public File getFILE() {
        return FILE;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}
