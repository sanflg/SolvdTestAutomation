package com.solvd.jackson.models.commerce;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.jackson.models.AbstractEntity;

import java.util.Objects;

public class ProductCart extends AbstractEntity {
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("Carts_id")
    private int cartId;
    @JsonProperty("Products_id")
    private int productId;

    public ProductCart(){}

    public ProductCart(int id,
                       int quantity,
                       int cartId,
                       int productId) {
        super(id);
        this.quantity = quantity;
        this.cartId = cartId;
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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
