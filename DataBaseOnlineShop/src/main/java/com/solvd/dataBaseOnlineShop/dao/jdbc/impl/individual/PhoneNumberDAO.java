package com.solvd.dataBaseOnlineShop.dao.jdbc.impl.individual;

import com.solvd.dataBaseOnlineShop.dao.interfaces.individual.IPhoneNumberDAO;
import com.solvd.dataBaseOnlineShop.dao.jdbc.AbstractJDBC;
import com.solvd.dataBaseOnlineShop.models.individual.PhoneNumber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class PhoneNumberDAO extends AbstractJDBC implements IPhoneNumberDAO {
    private static final Logger logger = LogManager.getLogger(PhoneNumberDAO.class);
    private static final String CREATE_PHONENUMBER =
            "INSERT INTO PhoneNumbers (number, Individuals_id) VALUES (?, ?)";
    private static final String GET_PHONENUMBER_BY_ID =
            "SELECT * FROM PhoneNumbers WHERE id = ?";
    private static final String UPDATE_PHONENUMBER =
            "UPDATE PhoneNumbers SET number=?, Individuals_id=? WHERE id=?";
    private static final String DELETE_PHONENUMBER =
            "DELETE FROM PhoneNumbers WHERE id=?";

    @Override
    public void create(PhoneNumber phoneNumber) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(CREATE_PHONENUMBER, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, phoneNumber.getNumber());
            ps.setInt(2, phoneNumber.getIndividualId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to create phoneNumber: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public PhoneNumber getByID(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PhoneNumber phoneNumber = new PhoneNumber();
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(GET_PHONENUMBER_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            phoneNumber.setId(rs.getInt("id"));
            phoneNumber.setNumber(rs.getInt("number"));
            phoneNumber.setIndividualId(rs.getInt("Individuals_id"));
        } catch (SQLException e) {
            logger.error("SQLException trying to get phoneNumber by ID: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
        return phoneNumber;
    }

    @Override
    public void update(PhoneNumber phoneNumber) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(UPDATE_PHONENUMBER, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, phoneNumber.getNumber());
            ps.setInt(2, phoneNumber.getIndividualId());
            ps.setInt(3, phoneNumber.getId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to update phoneNumber: ", e);
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
            ps = c.prepareStatement(DELETE_PHONENUMBER, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to delete phoneNumber: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }
}
