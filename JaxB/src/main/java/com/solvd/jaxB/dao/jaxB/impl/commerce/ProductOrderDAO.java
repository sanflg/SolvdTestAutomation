package com.solvd.jaxB.dao.jaxB.impl.commerce;

import com.solvd.jaxB.dao.interfaces.commerce.IProductOrderDAO;
import com.solvd.jaxB.models.commerce.ProductOrder;
import com.solvd.jaxB.wrappers.commerce.ProductOrders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class ProductOrderDAO implements IProductOrderDAO {
    private static final Logger logger = LogManager.getLogger(ProductOrderDAO.class);
    private static final File FILE = new File("src/main/resources/xml/productorders.xml");

    @Override
    public void create(ProductOrder productorder) {
        ProductOrders productorders = new ProductOrders();
        productorders.setProductOrders(unmarshall());
        productorders.getProductOrders().add(productorder);
        marshall(productorders);
    }

    @Override
    public ProductOrder getByID(int id) {
        ProductOrders productorders = new ProductOrders();
        productorders.setProductOrders(unmarshall());
        return productorders.getProductOrders().stream()
                .filter(productorder -> productorder.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(ProductOrder productorder) {
        ProductOrders productorders = new ProductOrders();
        productorders.setProductOrders(unmarshall());
        productorders.getProductOrders().stream()
                .filter(productorderStream -> productorderStream.getId() == productorder.getId())
                .findAny()
                .ifPresent(editProductOrder ->{
                    editProductOrder.setOrderId(productorder.getOrderId());
                    editProductOrder.setProductId(productorder.getProductId());
                });
        marshall(productorders);
    }

    @Override
    public void delete(int id) {
        ProductOrders productorders = new ProductOrders();
        productorders.setProductOrders(unmarshall());
        productorders.getProductOrders().stream()
                .filter(productorderStream -> productorderStream.getId() == id)
                .findAny()
                .ifPresent(editProductOrder -> productorders.getProductOrders().remove(editProductOrder));
        marshall(productorders);
    }

    private static synchronized List<ProductOrder> unmarshall(){
        ProductOrders productorders = new ProductOrders();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ProductOrders.class);
            Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();
            productorders = (ProductOrders) jaxUnmarshaller.unmarshal(FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
        return productorders.getProductOrders();
    }

    private static synchronized void marshall(ProductOrders productorders){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ProductOrders.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(productorders, FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }
}
