package com.solvd.dataBaseOnlineShop.models.comerce;

import com.solvd.dataBaseOnlineShop.models.AbstractEntity;

import java.util.Objects;

public class ProductOrder extends AbstractEntity {
    private int orderId;
    private int productId;

    public ProductOrder(){}

    public ProductOrder(int id, int orderId, int productId) {
        super(id);
        this.orderId = orderId;
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrder that = (ProductOrder) o;
        return getOrderId() == that.getOrderId() &&
                getProductId() == that.getProductId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getProductId());
    }
}
