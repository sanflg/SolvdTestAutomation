package com.solvd.jaxB.dao.jaxB.impl.comerce;

import com.solvd.jaxB.dao.interfaces.comerce.ICartDAO;
import com.solvd.jaxB.models.comerce.Cart;
import com.solvd.jaxB.wrappers.comerce.Carts;
import com.solvd.jaxB.wrappers.comerce.Categories;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.*;
import java.io.File;
import java.util.List;

public class CartDAO implements ICartDAO {
    private static final Logger logger = LogManager.getLogger(CartDAO.class);
    private static final File FILE = new File("src/main/resources/xml/carts.xml");

    @Override
    public void create(Cart cart) {
        Carts carts = new Carts();
        carts.setCarts(unmarshall());
        carts.getCarts().add(cart);
        marshall(carts);
    }

    @Override
    public Cart getByID(int id) {
        Carts carts = new Carts();
        carts.setCarts(unmarshall());
        return carts.getCarts().stream()
                .filter(cart -> cart.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(Cart cart) {
        Carts carts = new Carts();
        carts.setCarts(unmarshall());
        carts.getCarts().stream()
                .filter(cartStream -> cartStream.getId() == cart.getId())
                .findAny()
                .ifPresent(editCart -> editCart.setIndividualId(cart.getIndividualId()));
        marshall(carts);
    }

    @Override
    public void delete(int id) {
        Carts carts = new Carts();
        carts.setCarts(unmarshall());
        carts.getCarts().stream()
                .filter(cartStream -> cartStream.getId() == id)
                .findAny()
                .ifPresent(editCart -> carts.getCarts().remove(editCart));
        marshall(carts);
    }

    private static synchronized List<Cart> unmarshall(){
        Carts categories = new Carts();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Categories.class);
            Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();
            categories = (Carts) jaxUnmarshaller.unmarshal(FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
        return categories.getCarts();
    }

    private static synchronized void marshall(Carts carts){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Categories.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(carts, FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }
}
