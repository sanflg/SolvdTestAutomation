package com.solvd.jaxB.services.commerce;

import com.solvd.jaxB.dao.jaxB.impl.commerce.ProductDAO;
import com.solvd.jaxB.models.commerce.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductServ {
    private final static Logger logger = LogManager.getLogger(ProductServ.class);
    private final ProductDAO productDAO = new ProductDAO();

    public void create(Product product) {
        productDAO.create(product);
        logger.info("Product created in DB: " + product.toString());
    }

    public Product getByID(int id) {
        Product product = productDAO.getByID(id);
        logger.info("Getting product by " + id + ": "+ product.toString());
        return product;
    }

    public void update(Product product) {
        productDAO.update(product);
        logger.info("Updating product: " + product.toString());
    }

    public void delete(int id) {
        productDAO.delete(id);
        logger.info("Deleting product with id " + id);
    }

}
