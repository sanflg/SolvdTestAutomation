package com.solvd.dataBaseOnlineShop.services.commerce;

import com.solvd.dataBaseOnlineShop.dao.jdbc.impl.commerce.SupplierDAO;
import com.solvd.dataBaseOnlineShop.models.commerce.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SupplierServ {
    private final static Logger logger = LogManager.getLogger(SupplierServ.class);
    private final SupplierDAO supplierDAO = new SupplierDAO();

    public void create(Supplier supplier) {
        supplierDAO.create(supplier);
        logger.info("Supplier created in DB: " + supplier.toString());
    }

    public Supplier getByID(int id) {
        Supplier supplier = supplierDAO.getByID(id);
        logger.info("Getting supplier by " + id + ": "+ supplier.toString());
        return supplier;
    }

    public void update(Supplier supplier) {
        supplierDAO.update(supplier);
        logger.info("Updating supplier: " + supplier.toString());
    }

    public void delete(int id) {
        supplierDAO.delete(id);
        logger.info("Deleting supplier with id " + id);
    }
}
