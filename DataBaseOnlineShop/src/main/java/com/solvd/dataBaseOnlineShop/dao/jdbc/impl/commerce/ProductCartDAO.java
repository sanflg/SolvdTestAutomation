package com.solvd.dataBaseOnlineShop.dao.jdbc.impl.commerce;

import com.solvd.dataBaseOnlineShop.dao.interfaces.commerce.IProductCartDAO;
import com.solvd.dataBaseOnlineShop.dao.jdbc.AbstractJDBC;
import com.solvd.dataBaseOnlineShop.models.commerce.ProductCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ProductCartDAO extends AbstractJDBC implements IProductCartDAO {
    private static final Logger logger = LogManager.getLogger(ProductCartDAO.class);
    private static final String CREATE_PRODUCTCART =
            "INSERT INTO ProductCarts (quantity, Carts_id, Products_id) VALUES (?, ?, ?)";
    private static final String GET_PRODUCTCART_BY_ID =
            "SELECT * FROM ProductCarts WHERE id = ?";
    private static final String UPDATE_CART =
            "UPDATE ProductCarts SET quantity=?, Carts_id=?, Products_id=? WHERE id=?";
    private static final String DELETE_PRODUCTCART =
            "DELETE FROM ProductCarts WHERE id=?";

    @Override
    public void create(ProductCart productCart) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(CREATE_PRODUCTCART, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, productCart.getQuantity());
            ps.setInt(2, productCart.getCartId());
            ps.setInt(3, productCart.getProductId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to create productCart: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public ProductCart getByID(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProductCart productCart = new ProductCart();
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(GET_PRODUCTCART_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            productCart.setId(rs.getInt("id"));
            productCart.setQuantity(rs.getInt("quantity"));
            productCart.setCartId(rs.getInt("Carts_id"));
            productCart.setProductId(rs.getInt("Products_id"));
        } catch (SQLException e) {
            logger.error("SQLException trying to get productCart by ID: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
        return productCart;
    }

    @Override
    public void update(ProductCart productCart) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(UPDATE_CART, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, productCart.getQuantity());
            ps.setInt(2, productCart.getCartId());
            ps.setInt(3, productCart.getProductId());
            ps.setInt(4, productCart.getId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to update productCart: ", e);
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
            ps = c.prepareStatement(DELETE_PRODUCTCART, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to delete productCart: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }
}
