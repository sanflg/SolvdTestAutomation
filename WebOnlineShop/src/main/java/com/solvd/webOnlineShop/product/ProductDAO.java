package com.solvd.webOnlineShop.product;

import com.solvd.webOnlineShop.DatabaseSimulation;
import com.solvd.webOnlineShop.generics.IAbstractDAO;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class ProductDAO implements IAbstractDAO<Product, String, HashMap<String,String>> {
    private static final ProductDAO productDAO = new ProductDAO();

    //Singleton
    public static ProductDAO getProductDAO() {
        return productDAO;
    }

    @Override
    public void save(Product product) {
        DatabaseSimulation.getProductList().add(product);
    }

    @Override
    public Product get(String name) {

        AtomicReference<Product> storeProduct = new AtomicReference<>();

        DatabaseSimulation.getProductList().forEach(product->{
            if (name.equals(product.getName())) storeProduct.set(product);});
        return storeProduct.get();
    }

    @Override
    public void update(Product product, HashMap<String,String> values) {
        if (values.containsKey("name")) product.setName(values.get("name"));
        if (values.containsKey("price")) product.setPrice(Float.parseFloat(values.get("price")));
        if (values.containsKey("description")) product.setName(values.get("description"));
        Date newUpdate = new Date();
        product.setLastUpdate(newUpdate);
    }

    @Override
    public void remove(Product product) {
        DatabaseSimulation.getProductList().remove(product);
    }
}
