package com.solvd.dataBaseOnlineShop.dao.jdbc.impl.comerce;

import com.solvd.dataBaseOnlineShop.dao.interfaces.comerce.IOrderDAO;
import com.solvd.dataBaseOnlineShop.dao.jdbc.AbstractJDBC;
import com.solvd.dataBaseOnlineShop.model.comerce.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class OrderDAO extends AbstractJDBC implements IOrderDAO {
    private static final Logger logger = LogManager.getLogger(OrderDAO.class);
    private static final String CREATE_ORDER =
            "INSERT INTO Orders (Carts_id) VALUES (?)";
    private static final String GET_ORDER_BY_ID =
            "SELECT * FROM Orders WHERE id = ?";
    private static final String UPDATE_CART =
            "UPDATE Orders SET Carts_id=? WHERE id=?";
    private static final String DELETE_ORDER =
            "DELETE FROM Orders WHERE id=?";

    @Override
    public void create(Order order) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(CREATE_ORDER, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getCartId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to create order: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public Order getByID(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order order = new Order();
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(GET_ORDER_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            order.setCartId(rs.getInt("Carts_id"));

        } catch (SQLException e) {
            logger.error("SQLException trying to get order by ID: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
        return order;
    }

    @Override
    public void update(Order order, int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(UPDATE_CART, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getCartId());
            ps.setInt(2, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to update order: ", e);
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
            ps = c.prepareStatement(DELETE_ORDER, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to delete order: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }
}
