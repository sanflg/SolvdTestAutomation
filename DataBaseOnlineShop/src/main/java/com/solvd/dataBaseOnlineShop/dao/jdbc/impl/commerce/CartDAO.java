package com.solvd.dataBaseOnlineShop.dao.jdbc.impl.commerce;

import com.solvd.dataBaseOnlineShop.dao.interfaces.commerce.ICartDAO;
import com.solvd.dataBaseOnlineShop.dao.jdbc.AbstractJDBC;
import com.solvd.dataBaseOnlineShop.models.commerce.Cart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class CartDAO extends AbstractJDBC implements ICartDAO {
    private static final Logger logger = LogManager.getLogger(CartDAO.class);
    private static final String CREATE_CART =
            "INSERT INTO Carts (Individuals_id) VALUES (?)";
    private static final String GET_CART_BY_ID =
            "SELECT * FROM Carts WHERE id=?";
    private static final String UPDATE_CART =
            "UPDATE Carts SET Individuals_id=? WHERE id=?";
    private static final String DELETE_CART =
            "DELETE FROM Carts WHERE id=?";

    @Override
    public void create(Cart cart) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = getCp().getConnection();
            ps = c.prepareStatement(CREATE_CART, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cart.getIndividualId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to create cart: ", e);
        } finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public Cart getByID(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cart cart = new Cart();
        try {
            c = getCp().getConnection();
            ps = c.prepareStatement(GET_CART_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            cart.setId(rs.getInt("id"));
            cart.setIndividualId(rs.getInt("Individuals_id"));
        } catch (SQLException e) {
            logger.error("SQLException trying to get cart by ID: ", e);
        } finally {
            closeResources(c, ps, rs);
        }
        return cart;
    }

    @Override
    public void update(Cart cart) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = getCp().getConnection();
            ps = c.prepareStatement(UPDATE_CART, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cart.getIndividualId());
            ps.setInt(2, cart.getId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to update cart: ", e);
        } finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public void delete(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = getCp().getConnection();
            ps = c.prepareStatement(DELETE_CART, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to delete cart: ", e);
        } finally {
            closeResources(c, ps, rs);
        }
    }
}
