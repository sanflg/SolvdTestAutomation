package com.solvd.jaxB.services.commerce;

import com.solvd.jaxB.dao.jaxB.impl.commerce.CartDAO;
import com.solvd.jaxB.dao.jaxB.impl.commerce.OrderDAO;
import com.solvd.jaxB.dao.jaxB.impl.commerce.ProductCartDAO;
import com.solvd.jaxB.dao.jaxB.impl.commerce.ProductOrderDAO;
import com.solvd.jaxB.models.commerce.Cart;
import com.solvd.jaxB.models.commerce.Order;
import com.solvd.jaxB.models.commerce.ProductCart;
import com.solvd.jaxB.models.commerce.ProductOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CartServ {
    private final static Logger logger = LogManager.getLogger(CartServ.class);
    private final CartDAO cartDAO = new CartDAO();
    private final ProductCartDAO productCartDAO = new ProductCartDAO();
    private final OrderDAO orderDAO = new OrderDAO();
    private final ProductOrderDAO productOrderDAO = new ProductOrderDAO();

    //Cart
    public void create(Cart cart) {
        cartDAO.create(cart);
        logger.info("Cart created in DB: " + cart.toString());
    }

    public Cart getByID(int id) {
        Cart cart = cartDAO.getByID(id);
        logger.info("Getting cart by " + id + ": "+ cart.toString());
        return cart;
    }

    public void update(Cart cart) {
        cartDAO.update(cart);
        logger.info("Updating cart: " + cart.toString());
    }

    public void delete(int id) {
        cartDAO.delete(id);
        logger.info("Deleting cart with id " + id);
    }

    //ProductCart
    public void createProductCart(ProductCart productCart) {
        productCartDAO.create(productCart);
        logger.info("ProductCart created in DB: " + productCart.toString());
    }

    public ProductCart getProductCartByID(int id) {
        ProductCart productCart = productCartDAO.getByID(id);
        logger.info("Getting productCart by " + id + ": "+ productCart.toString());
        return productCart;
    }

    public void updateProductCart(ProductCart productCart) {
        productCartDAO.update(productCart);
        logger.info("Updating productCart: " + productCart.toString());
    }

    public void deleteProductCart(int id) {
        productCartDAO.delete(id);
        logger.info("Deleting productCart with id " + id);
    }

    //Order
    public void createOrder(Order order) {
        orderDAO.create(order);
        logger.info("Order created in DB: " + order.toString());
    }

    public Order getOrderByID(int id) {
        Order order = orderDAO.getByID(id);
        logger.info("Getting order by " + id + ": "+ order.toString());
        return order;
    }

    public void updateOrder(Order order) {
        orderDAO.update(order);
        logger.info("Updating order: " + order.toString());
    }

    public void deleteOrder(int id) {
        orderDAO.delete(id);
        logger.info("Deleting order with id " + id);
    }

    //Product Order
    public void createProductOrder(ProductOrder productOrder) {
        productOrderDAO.create(productOrder);
        logger.info("ProductOrder created in DB: " + productOrder.toString());
    }

    public ProductOrder getProductOrderByID(int id) {
        ProductOrder productOrder = productOrderDAO.getByID(id);
        logger.info("Getting productOrder by " + id + ": "+ productOrder.toString());
        return productOrder;
    }

    public void updateProductOrder(ProductOrder productOrder) {
        productOrderDAO.update(productOrder);
        logger.info("Updating productOrder: " + productOrder.toString());
    }

    public void deleteProductOrder(int id) {
        productOrderDAO.delete(id);
        logger.info("Deleting productOrder with id " + id);
    }
}
