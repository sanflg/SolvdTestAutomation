package com.solvd.jackson.impl.commerce;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.jackson.interfaces.commerce.ISupplierDAO;
import com.solvd.jackson.models.commerce.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO implements ISupplierDAO {
    private static final Logger logger = LogManager.getLogger(SupplierDAO.class);
    private static final File FILE = new File("src/main/resources/json/suppliers.json");
    List<Supplier> suppliers = new ArrayList<>();

    @Override
    public void create(Supplier supplier) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            suppliers = objectMapper.readValue(FILE, new TypeReference<List<Supplier>>(){});
            suppliers.add(supplier);
            objectMapper.writeValue(FILE, suppliers);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public Supplier getByID(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            suppliers = objectMapper.readValue(FILE, new TypeReference<List<Supplier>>(){});
        } catch (IOException e){
            logger.error(e);
        }
        return suppliers.stream()
                .filter(supplier -> supplier.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(Supplier supplier) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            suppliers = objectMapper.readValue(FILE, new TypeReference<List<Supplier>>(){});
            suppliers.stream()
                    .filter(supplierElement -> supplierElement.getId() == supplier.getId())
                    .findAny()
                    .ifPresent(updateSupplier -> updateSupplier.setIndividualId(supplier.getIndividualId()));
            objectMapper.writeValue(FILE, suppliers);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public void delete(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            suppliers = objectMapper.readValue(FILE, new TypeReference<List<Supplier>>(){});
            suppliers.stream()
                    .filter(supplierElement -> supplierElement.getId() == id)
                    .findAny()
                    .ifPresent(deleteSupplier -> suppliers.remove(deleteSupplier));
        } catch (IOException e){
            logger.error(e);
        }
    }
}
