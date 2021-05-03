package com.solvd.dataBaseOnlineShop.dao.jdbc.impl.commerce;

import com.solvd.dataBaseOnlineShop.dao.interfaces.commerce.IProductOrderDAO;
import com.solvd.dataBaseOnlineShop.dao.jdbc.AbstractJDBC;
import com.solvd.dataBaseOnlineShop.models.commerce.ProductOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ProductOrderDAO extends AbstractJDBC implements IProductOrderDAO {
    private static final Logger logger = LogManager.getLogger(ProductOrderDAO.class);
    private static final String CREATE_PRODUCTORDER =
            "INSERT INTO ProductOrders (Orders_id, Products_id) VALUES (?, ?)";
    private static final String GET_PRODUCTORDER_BY_ID =
            "SELECT * FROM ProductOrders WHERE id = ?";
    private static final String UPDATE_PRODUCTORDER =
            "UPDATE ProductOrders SET Orders_id=?, Products_id=? WHERE id=?";
    private static final String DELETE_PRODUCTORDER =
            "DELETE FROM ProductOrders WHERE id=?";

    @Override
    public void create(ProductOrder productOrder) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(CREATE_PRODUCTORDER, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, productOrder.getOrderId());
            ps.setInt(2, productOrder.getProductId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to create productOrder: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public ProductOrder getByID(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProductOrder productOrder = new ProductOrder();
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(GET_PRODUCTORDER_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            productOrder.setId(rs.getInt("id"));
            productOrder.setOrderId(rs.getInt("Orders_id"));
            productOrder.setProductId(rs.getInt("Products_id"));
        } catch (SQLException e) {
            logger.error("SQLException trying to get productOrder by ID: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
        return productOrder;
    }

    @Override
    public void update(ProductOrder productOrder) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(UPDATE_PRODUCTORDER, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, productOrder.getOrderId());
            ps.setInt(2, productOrder.getProductId());
            ps.setInt(3, productOrder.getId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to update productOrder: ", e);
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
            ps = c.prepareStatement(DELETE_PRODUCTORDER, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to delete productOrder: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }
}
