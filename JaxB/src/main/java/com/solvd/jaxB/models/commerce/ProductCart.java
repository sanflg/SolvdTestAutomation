package com.solvd.jaxB.models.commerce;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProductCart{
    @XmlAttribute(name = "id")
    private int id;
    @XmlElement(name = "quantity")
    private int quantity;
    @XmlElement(name = "Carts_id")
    private int cartId;
    @XmlElement(name = "Products_id")
    private int productId;

    public ProductCart(){}

    public ProductCart(int id,
                       int quantity,
                       int cartId,
                       int productId) {
        this.id = id;
        this.quantity = quantity;
        this.cartId = cartId;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductCart{" +
                "quantity=" + quantity +
                ", cartId=" + cartId +
                ", productId=" + productId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCart that = (ProductCart) o;
        return getQuantity() == that.getQuantity() &&
                getCartId() == that.getCartId() &&
                getProductId() == that.getProductId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuantity(), getCartId(), getProductId());
    }
}
