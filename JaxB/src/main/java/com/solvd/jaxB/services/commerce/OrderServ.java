package com.solvd.jaxB.services.commerce;

import com.solvd.jaxB.dao.jaxB.impl.commerce.OrderDAO;
import com.solvd.jaxB.models.commerce.Order;
import com.solvd.jaxB.services.IAbstractServ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderServ implements IAbstractServ<Order> {
    private final static Logger logger = LogManager.getLogger(OrderServ.class);
    private final OrderDAO orderDAO = new OrderDAO();

    public OrderServ() {
    }

    @Override
    public void create(Order order) {
        orderDAO.create(order);
        logger.info("Order created in DB: " + order.toString());
    }

    @Override
    public Order getByID(int id) {
        Order order = orderDAO.getByID(id);
        logger.info("Getting order by " + id + ": "+ order.toString());
        return order;
    }

    @Override
    public void update(Order order) {
        orderDAO.update(order);
        logger.info("Updating order: " + order.toString());
    }

    @Override
    public void delete(int id) {
        orderDAO.delete(id);
        logger.info("Deleting order with id " + id);
    }
}
