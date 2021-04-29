package com.solvd.jaxB.dao.jaxB.impl.comerce;

import com.solvd.jaxB.dao.interfaces.comerce.IProductCartDAO;
import com.solvd.jaxB.models.comerce.ProductCart;
import com.solvd.jaxB.wrappers.comerce.ProductCarts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class ProductCartDAO implements IProductCartDAO {
    private static final Logger logger = LogManager.getLogger(ProductCartDAO.class);
    private static final File FILE = new File("src/main/resources/xml/productcarts.xml");

    @Override
    public void create(ProductCart productcart) {
        ProductCarts productcarts = new ProductCarts();
        productcarts.setProductCarts(unmarshall());
        productcarts.getProductCarts().add(productcart);
        marshall(productcarts);
    }

    @Override
    public ProductCart getByID(int id) {
        ProductCarts productcarts = new ProductCarts();
        productcarts.setProductCarts(unmarshall());
        return productcarts.getProductCarts().stream()
                .filter(productcart -> productcart.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(ProductCart productcart) {
        ProductCarts productcarts = new ProductCarts();
        productcarts.setProductCarts(unmarshall());
        productcarts.getProductCarts().stream()
                .filter(productcartStream -> productcartStream.getId() == productcart.getId())
                .findAny()
                .ifPresent(editProductCart ->{
                    editProductCart.setCartId(productcart.getCartId());
                    editProductCart.setProductId(productcart.getProductId());
                });
        marshall(productcarts);
    }

    @Override
    public void delete(int id) {
        ProductCarts productcarts = new ProductCarts();
        productcarts.setProductCarts(unmarshall());
        productcarts.getProductCarts().stream()
                .filter(productcartStream -> productcartStream.getId() == id)
                .findAny()
                .ifPresent(editProductCart -> productcarts.getProductCarts().remove(editProductCart));
        marshall(productcarts);
    }

    private static synchronized List<ProductCart> unmarshall(){
        ProductCarts productcarts = new ProductCarts();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ProductCarts.class);
            Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();
            productcarts = (ProductCarts) jaxUnmarshaller.unmarshal(FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
        return productcarts.getProductCarts();
    }

    private static synchronized void marshall(ProductCarts productcarts){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ProductCarts.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(productcarts, FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }
}
