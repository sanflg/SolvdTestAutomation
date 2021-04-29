package com.solvd.jaxB.wrappers.comerce;

import com.solvd.jaxB.models.comerce.Category;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class Categories{
    private final File FILE = new File("src/main/resources/xml/categories.xml");

    @XmlElement(name = "category")
    private List<Category> categories = null;

    public File getFILE() {
        return FILE;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
