package com.solvd.jackson.impl.commerce;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.jackson.interfaces.commerce.ICategoryDAO;
import com.solvd.jackson.models.commerce.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO {
    private static final Logger logger = LogManager.getLogger(CategoryDAO.class);
    private static final File FILE = new File("src/main/resources/json/categories.json");
    List<Category> categories = new ArrayList<>();

    @Override
    public void create(Category category) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            categories = objectMapper.readValue(FILE, new TypeReference<List<Category>>(){});
            categories.add(category);
            objectMapper.writeValue(FILE, categories);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public Category getByID(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            categories = objectMapper.readValue(FILE, new TypeReference<List<Category>>(){});
        } catch (IOException e){
            logger.error(e);
        }
        return categories.stream()
                .filter(category -> category.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(Category category) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            categories = objectMapper.readValue(FILE, new TypeReference<List<Category>>(){});
            categories.stream()
                    .filter(categoryElement -> categoryElement.getId() == category.getId())
                    .findAny()
                    .ifPresent(updateCategory -> updateCategory.setName(category.getName()));
            objectMapper.writeValue(FILE, categories);
        } catch (IOException e){
            logger.error(e);
        }
    }

    @Override
    public void delete(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            categories = objectMapper.readValue(FILE, new TypeReference<List<Category>>(){});
            categories.stream()
                    .filter(categoryElement -> categoryElement.getId() == id)
                    .findAny()
                    .ifPresent(deleteCategory -> categories.remove(deleteCategory));
        } catch (IOException e){
            logger.error(e);
        }
    }
}
