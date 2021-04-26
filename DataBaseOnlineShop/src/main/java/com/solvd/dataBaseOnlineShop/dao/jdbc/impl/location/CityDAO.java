package com.solvd.dataBaseOnlineShop.dao.jdbc.impl.location;

import com.solvd.dataBaseOnlineShop.dao.interfaces.location.ICityDAO;
import com.solvd.dataBaseOnlineShop.dao.jdbc.AbstractJDBC;
import com.solvd.dataBaseOnlineShop.model.location.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class CityDAO extends AbstractJDBC implements ICityDAO {
    private static final Logger logger = LogManager.getLogger(CityDAO.class);
    private static final String CREATE_CITY =
            "INSERT INTO City (name, States_id) VALUES (?, ?)";
    private static final String GET_CITY_BY_ID =
            "SELECT * FROM City WHERE id = ?";
    private static final String UPDATE_CITY =
            "UPDATE City SET name=?, States_id=? WHERE id=?";
    private static final String DELETE_CITY =
            "DELETE FROM City WHERE id=?";

    @Override
    public void create(City address) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(CREATE_CITY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.getName());
            ps.setInt(2, address.getState());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to create address: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public City getByID(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        City address = new City();
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(GET_CITY_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            address.setName(rs.getString("name"));
            address.setStateId(rs.getInt("States_id"));

        } catch (SQLException e) {
            logger.error("SQLException trying to get address by ID: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
        return address;
    }

    @Override
    public void update(City address, int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(UPDATE_CITY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.getName());
            ps.setInt(2, address.getState());
            ps.setInt(3, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to update address: ", e);
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
            ps = c.prepareStatement(DELETE_CITY, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to delete address: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }
}
