package com.solvd.dataBaseOnlineShop.dao.jdbc.impl.individual;

import com.solvd.dataBaseOnlineShop.dao.interfaces.individual.IIndividualAddressDAO;
import com.solvd.dataBaseOnlineShop.dao.jdbc.AbstractJDBC;
import com.solvd.dataBaseOnlineShop.model.individual.IndividualAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class IndividualAddressDAO extends AbstractJDBC implements IIndividualAddressDAO {
    private static final Logger logger = LogManager.getLogger(IndividualAddressDAO.class);
    private static final String CREATE_INDIVIDUALADDRESS =
            "INSERT INTO IndividualAddresses (number, Addresses_id, Individuals_id) VALUES (?, ?, ?)";
    private static final String GET_INDIVIDUALADDRESS_BY_ID =
            "SELECT * FROM IndividualAddresses WHERE id = ?";
    private static final String UPDATE_CART =
            "UPDATE IndividualAddresses SET number=?, Addresses_id=?, Individuals_id=? WHERE id=?";
    private static final String DELETE_INDIVIDUALADDRESS =
            "DELETE FROM IndividualAddresses WHERE id=?";

    @Override
    public void create(IndividualAddress individualAddress) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(CREATE_INDIVIDUALADDRESS, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, individualAddress.getNumber());
            ps.setInt(2, individualAddress.getAddress());
            ps.setInt(3, individualAddress.getIndividual());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to create individualAddress: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public IndividualAddress getByID(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        IndividualAddress individualAddress = new IndividualAddress();
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(GET_INDIVIDUALADDRESS_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            individualAddress.setNumber(rs.getInt("number"));
            individualAddress.setAddressId(rs.getInt("Addresses_id"));
            individualAddress.setIndividualId(rs.getInt("Individuals_id"));

        } catch (SQLException e) {
            logger.error("SQLException trying to get individualAddress by ID: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
        return individualAddress;
    }

    @Override
    public void update(IndividualAddress individualAddress, int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(UPDATE_CART, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, individualAddress.getNumber());
            ps.setInt(2, individualAddress.getAddress());
            ps.setInt(3, individualAddress.getIndividual());
            ps.setInt(4, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to update individualAddress: ", e);
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
            ps = c.prepareStatement(DELETE_INDIVIDUALADDRESS, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to delete individualAddress: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }
}
