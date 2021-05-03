package com.solvd.jaxB.dao.jaxB.impl.commerce;

import com.solvd.jaxB.dao.interfaces.commerce.ICategoryDAO;
import com.solvd.jaxB.models.commerce.Category;
import com.solvd.jaxB.wrappers.commerce.Categories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class CategoryDAO implements ICategoryDAO {
    private static final Logger logger = LogManager.getLogger(CategoryDAO.class);
    private static final File FILE = new File("src/main/resources/xml/categories.xml");

    @Override
    public void create(Category category) {
        Categories categories = new Categories();
        categories.setCategories(unmarshall());
        categories.getCategories().add(category);
        marshall(categories);
    }

    @Override
    public Category getByID(int id) {
        Categories categories = new Categories();
        categories.setCategories(unmarshall());
        return categories.getCategories().stream()
                .filter(category -> category.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(Category category) {
        Categories categories = new Categories();
        categories.setCategories(unmarshall());
        categories.getCategories().stream()
                .filter(categoryStream -> categoryStream.getId() == category.getId())
                .findAny()
                .ifPresent(editCategory ->
                        editCategory.setName(category.getName()));
        marshall(categories);
    }

    @Override
    public void delete(int id) {
        Categories categories = new Categories();
        categories.setCategories(unmarshall());
        categories.getCategories().stream()
                .filter(categoryStream -> categoryStream.getId() == id)
                .findAny()
                .ifPresent(editCategory -> categories.getCategories().remove(editCategory));
        marshall(categories);
    }

    private static synchronized List<Category> unmarshall(){
        Categories categories = new Categories();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Categories.class);
            Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();
            categories = (Categories) jaxUnmarshaller.unmarshal(FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
        return categories.getCategories();
    }

    private static synchronized void marshall(Categories categories){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Categories.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(categories, FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }
}
