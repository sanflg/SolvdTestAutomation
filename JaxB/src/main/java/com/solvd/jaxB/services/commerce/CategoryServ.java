package com.solvd.jaxB.services.commerce;

import com.solvd.jaxB.dao.jaxB.impl.commerce.CategoryDAO;
import com.solvd.jaxB.models.commerce.Category;
import com.solvd.jaxB.services.IAbstractServ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CategoryServ implements IAbstractServ<Category> {
    private final static Logger logger = LogManager.getLogger(CategoryServ.class);
    private final CategoryDAO categoryDAO = new CategoryDAO();

    public CategoryServ() {
    }

    @Override
    public void create(Category category) {
        categoryDAO.create(category);
        logger.info("Category created in DB: " + category.toString());
    }

    @Override
    public Category getByID(int id) {
        Category category = categoryDAO.getByID(id);
        logger.info("Getting category by " + id + ": "+ category.toString());
        return category;
    }

    @Override
    public void update(Category category) {
        categoryDAO.update(category);
        logger.info("Updating category: " + category.toString());
    }

    @Override
    public void delete(int id) {
        categoryDAO.delete(id);
        logger.info("Deleting category with id " + id);
    }
}
