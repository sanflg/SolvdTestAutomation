package com.solvd.jaxB.dao.jaxB.impl.comerce;

import com.solvd.jaxB.dao.interfaces.comerce.IProductDAO;
import com.solvd.jaxB.models.comerce.Product;
import com.solvd.jaxB.wrappers.comerce.Products;
import com.solvd.jaxB.wrappers.comerce.Categories;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class ProductDAO implements IProductDAO {
    private static final Logger logger = LogManager.getLogger(ProductDAO.class);
    private static final File FILE = new File("src/main/resources/xml/products.xml");

    @Override
    public void create(Product product) {
        Products products = new Products();
        products.setProducts(unmarshall());
        products.getProducts().add(product);
        marshall(products);
    }

    @Override
    public Product getByID(int id) {
        Products products = new Products();
        products.setProducts(unmarshall());
        return products.getProducts().stream()
                .filter(product -> product.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(Product product) {
        Products products = new Products();
        products.setProducts(unmarshall());
        products.getProducts().stream()
                .filter(productStream -> productStream.getId() == product.getId())
                .findAny()
                .ifPresent(editProduct -> {
                        editProduct.setName(product.getName());
                        editProduct.setPrice(product.getPrice());
                        editProduct.setDescription(product.getDescription());
                        editProduct.setCurrencyId(product.getCurrencyId());
                        editProduct.setSupplierId(product.getSupplierId());
                        editProduct.setCategoryId(product.getCategoryId());
                        });
        marshall(products);
    }

    @Override
    public void delete(int id) {
        Products products = new Products();
        products.setProducts(unmarshall());
        products.getProducts().stream()
                .filter(productStream -> productStream.getId() == id)
                .findAny()
                .ifPresent(editProduct -> products.getProducts().remove(editProduct));
        marshall(products);
    }

    private static synchronized List<Product> unmarshall(){
        Products categories = new Products();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Categories.class);
            Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();
            categories = (Products) jaxUnmarshaller.unmarshal(FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
        return categories.getProducts();
    }

    private static synchronized void marshall(Products products){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Categories.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(products, FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }
}
