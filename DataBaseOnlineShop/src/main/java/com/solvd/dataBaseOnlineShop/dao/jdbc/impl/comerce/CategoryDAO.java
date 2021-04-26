package com.solvd.dataBaseOnlineShop.dao.jdbc.impl.comerce;

import com.solvd.dataBaseOnlineShop.dao.interfaces.comerce.ICategoryDAO;
import com.solvd.dataBaseOnlineShop.dao.jdbc.AbstractJDBC;
import com.solvd.dataBaseOnlineShop.models.comerce.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class CategoryDAO extends AbstractJDBC implements ICategoryDAO {
    private static final Logger logger = LogManager.getLogger(CategoryDAO.class);
    private static final String CREATE_CATEGORY =
            "INSERT INTO Categories (Individuals_id) VALUES (?)";
    private static final String GET_CATEGORY_BY_ID =
            "SELECT * FROM Categories WHERE id=?";
    private static final String UPDATE_CATEGORY =
            "UPDATE Categories SET name=? WHERE id=?";
    private static final String DELETE_CATEGORY =
            "DELETE FROM Categories WHERE id=?";

    @Override
    public void create(Category category) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(CREATE_CATEGORY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, category.getName());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to create category: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public Category getByID(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Category category = new Category();
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(GET_CATEGORY_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
        } catch (SQLException e) {
            logger.error("SQLException trying to get category by ID: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
        return category;
    }

    @Override
    public void update(Category category) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(UPDATE_CATEGORY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to update category: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public void delete(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(DELETE_CATEGORY, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to delete category: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }
}
