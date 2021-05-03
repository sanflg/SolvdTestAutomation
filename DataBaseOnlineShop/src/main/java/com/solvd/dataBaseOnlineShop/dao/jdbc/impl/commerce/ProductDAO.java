package com.solvd.dataBaseOnlineShop.dao.jdbc.impl.commerce;

import com.solvd.dataBaseOnlineShop.dao.interfaces.commerce.IProductDAO;
import com.solvd.dataBaseOnlineShop.dao.jdbc.AbstractJDBC;
import com.solvd.dataBaseOnlineShop.models.commerce.Product;
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
    public void create(Product product) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(CREATE_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setInt(4, product.getSupplierId());
            ps.setInt(5, product.getCurrencyId());
            ps.setInt(6, product.getCategoryId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to create product: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public Product getByID(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Product product = new Product();
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(GET_PRODUCT_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setDescription(rs.getString("description"));
            product.setSupplierId(rs.getInt("Suppliers_id"));
            product.setCurrencyId(rs.getInt("Currencies_id"));
            product.setCategoryId(rs.getInt("Categories_id"));
        } catch (SQLException e) {
            logger.error("SQLException trying to get product by ID: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
        return product;
    }

    @Override
    public void update(Product product) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(UPDATE_CART, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setInt(4, product.getSupplierId());
            ps.setInt(5, product.getCurrencyId());
            ps.setInt(6, product.getCategoryId());
            ps.setInt(7, product.getId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to update product: ", e);
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
            logger.error("SQLException trying to delete product: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }
}
