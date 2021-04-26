package com.solvd.dataBaseOnlineShop.dao.jdbc.impl.comerce;

import com.solvd.dataBaseOnlineShop.dao.interfaces.comerce.IProductDAO;
import com.solvd.dataBaseOnlineShop.dao.jdbc.AbstractJDBC;
import com.solvd.dataBaseOnlineShop.model.comerce.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ProductDAO extends AbstractJDBC implements IProductDAO {
    private static final Logger logger = LogManager.getLogger(ProductDAO.class);
    private static final String CREATE_PRODUCT =
            "INSERT INTO Products (name, price, description, Suppliers_id, Currencies_id, Categories_id) VALUES (?,?,?,?,?,?)";
    private static final String GET_PRODUCT_BY_ID =
            "SELECT * FROM Products WHERE id = ?";
    private static final String UPDATE_CART =
            "UPDATE Products SET name=?, price=?, description=?, Suppliers_id=?, Currencies_id=?, Categories_id=? WHERE id=?";
    private static final String DELETE_PRODUCT =
            "DELETE FROM Product sWHERE id = ?";

    @Override
    public void create(Product products) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(CREATE_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, products.getName());
            ps.setDouble(2, products.getPrice());
            ps.setString(3, products.getDescription());
            ps.setInt(4, products.getSupplierId());
            ps.setInt(5, products.getCurrencyId());
            ps.setInt(6, products.getCategoryId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to create products: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public Product getByID(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Product products = new Product();
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(GET_PRODUCT_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            products.setName(rs.getString("name"));
            products.setPrice(rs.getDouble("price"));
            products.setDescription(rs.getString("description"));
            products.setSupplierId(rs.getInt("Suppliers_id"));
            products.setCurrencyId(rs.getInt("Currencies_id"));
            products.setCategoryId(rs.getInt("Categories_id"));
        } catch (SQLException e) {
            logger.error("SQLException trying to get products by ID: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
        return products;
    }

    @Override
    public void update(Product products, int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(UPDATE_CART, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, products.getName());
            ps.setDouble(2, products.getPrice());
            ps.setString(3, products.getDescription());
            ps.setInt(4, products.getSupplierId());
            ps.setInt(5, products.getCurrencyId());
            ps.setInt(6, products.getCategoryId());
            ps.setInt(7, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to update products: ", e);
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
            ps = c.prepareStatement(DELETE_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to delete products: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }
}
