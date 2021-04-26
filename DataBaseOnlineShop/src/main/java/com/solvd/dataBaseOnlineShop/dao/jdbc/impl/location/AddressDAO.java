package com.solvd.dataBaseOnlineShop.dao.jdbc.impl.location;

import com.solvd.dataBaseOnlineShop.dao.interfaces.location.IAddressDAO;
import com.solvd.dataBaseOnlineShop.dao.jdbc.AbstractJDBC;
import com.solvd.dataBaseOnlineShop.models.location.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class AddressDAO extends AbstractJDBC implements IAddressDAO {
    private static final Logger logger = LogManager.getLogger(AddressDAO.class);
    private static final String CREATE_ADDRESS =
            "INSERT INTO Address (name, Cities_id) VALUES (?, ?)";
    private static final String GET_ADDRESS_BY_ID =
            "SELECT * FROM Address WHERE id = ?";
    private static final String UPDATE_ADDRESS =
            "UPDATE Address SET name=?, Cities_id=? WHERE id=?";
    private static final String DELETE_ADDRESS =
            "DELETE FROM Address WHERE id=?";

    @Override
    public void create(Address address) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(CREATE_ADDRESS, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.getName());
            ps.setInt(2, address.getCity());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to create address: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public Address getByID(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Address address = new Address();
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(GET_ADDRESS_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            address.setId(rs.getInt("id"));
            address.setName(rs.getString("name"));
            address.setCityId(rs.getInt("Cities_id"));
        } catch (SQLException e) {
            logger.error("SQLException trying to get address by ID: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
        return address;
    }

    @Override
    public void update(Address address) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(UPDATE_ADDRESS, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.getName());
            ps.setInt(2, address.getCity());
            ps.setInt(3, address.getId());
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
            ps = c.prepareStatement(DELETE_ADDRESS, Statement.RETURN_GENERATED_KEYS);
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
