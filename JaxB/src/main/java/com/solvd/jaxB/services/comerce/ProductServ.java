package com.solvd.jaxB.services.comerce;

import com.solvd.jaxB.dao.jaxB.impl.comerce.ProductDAO;
import com.solvd.jaxB.models.comerce.Product;
import com.solvd.jaxB.services.IAbstractServ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductServ implements IAbstractServ<Product> {
    private final static Logger logger = LogManager.getLogger(ProductServ.class);
    private final ProductDAO productDAO = new ProductDAO();

    public ProductServ() {
    }

    @Override
    public void create(Product product) {
        productDAO.create(product);
        logger.info("Product created in DB: " + product.toString());
    }

    @Override
    public Product getByID(int id) {
        Product product = productDAO.getByID(id);
        logger.info("Getting product by " + id + ": "+ product.toString());
        return product;
    }

    @Override
    public void update(Product product) {
        productDAO.update(product);
        logger.info("Updating product: " + product.toString());
    }

    @Override
    public void delete(int id) {
        productDAO.delete(id);
        logger.info("Deleting product with id " + id);
    }

}
