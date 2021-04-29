package com.solvd.jaxB.dao.jaxB.impl.comerce;

import com.solvd.jaxB.dao.interfaces.comerce.ISupplierDAO;
import com.solvd.jaxB.models.comerce.Supplier;
import com.solvd.jaxB.models.comerce.Supplier;
import com.solvd.jaxB.wrappers.comerce.Suppliers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class SupplierDAO implements ISupplierDAO {
    private static final Logger logger = LogManager.getLogger(SupplierDAO.class);
    private static final File FILE = new File("src/main/resources/xml/suppliers.xml");

    @Override
    public void create(Supplier supplier) {
        Suppliers suppliers = new Suppliers();
        suppliers.setSuppliers(unmarshall());
        suppliers.getSuppliers().add(supplier);
        marshall(suppliers);
    }

    @Override
    public Supplier getByID(int id) {
        Suppliers suppliers = new Suppliers();
        suppliers.setSuppliers(unmarshall());
        return suppliers.getSuppliers().stream()
                .filter(supplier -> supplier.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(Supplier supplier) {
        Suppliers suppliers = new Suppliers();
        suppliers.setSuppliers(unmarshall());
        suppliers.getSuppliers().stream()
                .filter(supplierStream -> supplierStream.getId() == supplier.getId())
                .findAny()
                .ifPresent(editSupplier ->
                    editSupplier.setIndividualId(supplier.getIndividualId()));
        marshall(suppliers);
    }

    @Override
    public void delete(int id) {
        Suppliers suppliers = new Suppliers();
        suppliers.setSuppliers(unmarshall());
        suppliers.getSuppliers().stream()
                .filter(supplierStream -> supplierStream.getId() == id)
                .findAny()
                .ifPresent(editSupplier -> suppliers.getSuppliers().remove(editSupplier));
        marshall(suppliers);
    }

    private static synchronized List<Supplier> unmarshall(){
        Suppliers suppliers = new Suppliers();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Suppliers.class);
            Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();
            suppliers = (Suppliers) jaxUnmarshaller.unmarshal(FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
        return suppliers.getSuppliers();
    }

    private static synchronized void marshall(Suppliers suppliers){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Suppliers.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(suppliers, FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }
}
