package com.solvd.dataBaseOnlineShop.dao.jdbc.impl.commerce;

import com.solvd.dataBaseOnlineShop.dao.interfaces.commerce.ICurrencyDAO;
import com.solvd.dataBaseOnlineShop.dao.jdbc.AbstractJDBC;
import com.solvd.dataBaseOnlineShop.models.commerce.Currency;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class CurrencyDAO extends AbstractJDBC implements ICurrencyDAO {
    private static final Logger logger = LogManager.getLogger(CurrencyDAO.class);
    private static final String CREATE_CURRENCY =
            "INSERT INTO Currencies (name, tag) VALUES (?, ?)";
    private static final String GET_CURRENCY_BY_ID =
            "SELECT * FROM Currencies WHERE id = ?";
    private static final String UPDATE_CURRENCY =
            "UPDATE Currencies SET name=?, tag=? WHERE id=?";
    private static final String DELETE_CURRENCY =
            "DELETE FROM Currencies WHERE id=?";

    @Override
    public void create(Currency currency) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(CREATE_CURRENCY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, currency.getName());
            ps.setString(2, currency.getTag());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to create currency: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public Currency getByID(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Currency currency = new Currency();
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(GET_CURRENCY_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            currency.setId(rs.getInt("id"));
            currency.setName(rs.getString("name"));
            currency.setTag(rs.getString("tag"));
        } catch (SQLException e) {
            logger.error("SQLException trying to get currency by ID: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
        return currency;
    }

    @Override
    public void update(Currency currency) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(UPDATE_CURRENCY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, currency.getName());
            ps.setString(2, currency.getTag());
            ps.setInt(3, currency.getId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to update currency: ", e);
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
            ps = c.prepareStatement(DELETE_CURRENCY, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to delete currency: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }
}
