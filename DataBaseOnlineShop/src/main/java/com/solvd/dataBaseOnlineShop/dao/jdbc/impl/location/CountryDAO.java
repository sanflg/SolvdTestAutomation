package com.solvd.dataBaseOnlineShop.dao.jdbc.impl.location;

import com.solvd.dataBaseOnlineShop.dao.interfaces.location.ICountryDAO;
import com.solvd.dataBaseOnlineShop.dao.jdbc.AbstractJDBC;
import com.solvd.dataBaseOnlineShop.model.location.Country;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class CountryDAO extends AbstractJDBC implements ICountryDAO {
    private static final Logger logger = LogManager.getLogger(StateDAO.class);
    private static final String CREATE_COUNTRY =
            "INSERT INTO Countries (name, tag) VALUES (?, ?)";
    private static final String GET_COUNTRY_BY_ID =
            "SELECT * FROM Countries WHERE id = ?";
    private static final String UPDATE_COUNTRY =
            "UPDATE Countries SET name=?, tag=? WHERE id=?";
    private static final String DELETE_COUNTRY =
            "DELETE FROM Countries WHERE id=?";

    @Override
    public void create(Country country) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(CREATE_COUNTRY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, country.getName());
            ps.setString(2, country.getTag());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to create country: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public Country getByID(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Country country = new Country();
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(GET_COUNTRY_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            country.setName(rs.getString("name"));
            country.setTag(rs.getString("tag"));

        } catch (SQLException e) {
            logger.error("SQLException trying to get country by ID: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
        return country;
    }

    @Override
    public void update(Country country, int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(UPDATE_COUNTRY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, country.getName());
            ps.setString(2, country.getTag());
            ps.setInt(3, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to update country: ", e);
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
            ps = c.prepareStatement(DELETE_COUNTRY, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to delete country: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }
}
