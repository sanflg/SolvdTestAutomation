package com.solvd.jaxB.dao.jaxB.impl.comerce;

import com.solvd.jaxB.dao.interfaces.comerce.IOrderDAO;
import com.solvd.jaxB.models.comerce.Order;
import com.solvd.jaxB.wrappers.comerce.Orders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class OrderDAO implements IOrderDAO {
    private static final Logger logger = LogManager.getLogger(OrderDAO.class);
    private static final File FILE = new File("src/main/resources/xml/orders.xml");

    @Override
    public void create(Order order) {
        Orders orders = new Orders();
        orders.setOrders(unmarshall());
        orders.getOrders().add(order);
        marshall(orders);
    }

    @Override
    public Order getByID(int id) {
        Orders orders = new Orders();
        orders.setOrders(unmarshall());
        return orders.getOrders().stream()
                .filter(order -> order.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(Order order) {
        Orders orders = new Orders();
        orders.setOrders(unmarshall());
        orders.getOrders().stream()
                .filter(orderStream -> orderStream.getId() == order.getId())
                .findAny()
                .ifPresent(editOrder ->{
                    editOrder.setCartId(order.getCartId());
                });
        marshall(orders);
    }

    @Override
    public void delete(int id) {
        Orders orders = new Orders();
        orders.setOrders(unmarshall());
        orders.getOrders().stream()
                .filter(orderStream -> orderStream.getId() == id)
                .findAny()
                .ifPresent(editOrder -> orders.getOrders().remove(editOrder));
        marshall(orders);
    }

    private static synchronized List<Order> unmarshall(){
        Orders orders = new Orders();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Orders.class);
            Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();
            orders = (Orders) jaxUnmarshaller.unmarshal(FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
        return orders.getOrders();
    }

    private static synchronized void marshall(Orders orders){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Orders.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(orders, FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }
}
