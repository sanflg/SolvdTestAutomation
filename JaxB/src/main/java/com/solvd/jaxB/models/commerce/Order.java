package com.solvd.jaxB.models.commerce;

import javax.xml.bind.annotation.*;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Order{
    @XmlAttribute(name = "id")
    private int id;
    @XmlElement (name = "Carts_id")
    private int cartId;

    public Order(){}

    public Order(int id, int cartId) {
        this.id = id;
        this.cartId = cartId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "cartId=" + cartId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getCartId() == order.getCartId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCartId());
    }
}
