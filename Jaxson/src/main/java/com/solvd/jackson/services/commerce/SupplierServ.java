package com.solvd.jackson.services.commerce;

import com.solvd.jackson.impl.commerce.SupplierDAO;
import com.solvd.jackson.models.commerce.Supplier;
import com.solvd.jackson.services.IAbstractServ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SupplierServ implements IAbstractServ<Supplier> {
    private final static Logger logger = LogManager.getLogger(SupplierServ.class);
    private final SupplierDAO supplierDAO = new SupplierDAO();

    public SupplierServ() {
    }

    @Override
    public void create(Supplier supplier) {
        supplierDAO.create(supplier);
        logger.info("Supplier created in DB: " + supplier.toString());
    }

    @Override
    public Supplier getByID(int id) {
        Supplier supplier = supplierDAO.getByID(id);
        logger.info("Getting supplier by " + id + ": "+ supplier.toString());
        return supplier;
    }

    @Override
    public void update(Supplier supplier) {
        supplierDAO.update(supplier);
        logger.info("Updating supplier: " + supplier.toString());
    }

    @Override
    public void delete(int id) {
        supplierDAO.delete(id);
        logger.info("Deleting supplier with id " + id);
    }
}
