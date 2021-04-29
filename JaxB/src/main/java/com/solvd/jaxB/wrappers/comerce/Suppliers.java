package com.solvd.jaxB.wrappers.comerce;

import com.solvd.jaxB.models.comerce.Supplier;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType (XmlAccessType.FIELD)
public class Suppliers{
    private final File FILE = new File("src/main/resources/xml/suppliers.xml");

    @XmlElement(name = "supplier")
    private List<Supplier> suppliers = null;

    public File getFILE() {
        return FILE;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
}
