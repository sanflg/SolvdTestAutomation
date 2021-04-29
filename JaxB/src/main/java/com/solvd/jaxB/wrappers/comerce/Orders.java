package com.solvd.jaxB.wrappers.comerce;

import com.solvd.jaxB.models.comerce.Order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;

@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class Orders{
    private final File FILE = new File("src/main/resources/xml/orders.xml");

    @XmlElement(name = "order")
    private List<Order> orders = null;

    public File getFILE() {
        return FILE;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
