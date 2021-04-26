package com.solvd.dataBaseOnlineShop.dao.jdbc.impl.individual;

import com.solvd.dataBaseOnlineShop.dao.interfaces.individual.IIndividualDAO;
import com.solvd.dataBaseOnlineShop.dao.jdbc.AbstractJDBC;
import com.solvd.dataBaseOnlineShop.model.individual.Individual;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class IndividualDAO
        extends AbstractJDBC implements IIndividualDAO {
    private static final Logger logger = LogManager.getLogger(IndividualDAO.class);
    private static final String CREATE_INDIVIDUAL =
            "INSERT INTO Individuals (username, password, email, first_name, last_name, birth_date, languages_id) VALUES (?,?,?,?,?,?,?)";
    private static final String GET_INDIVIDUAL_BY_ID =
            "SELECT * FROM Individuals WHERE id = ?";
    private static final String UPDATE_CART =
            "UPDATE Individuals SET username=?, password=?, email=?, first_name=?, last_name=?, birth_date=?, languages_id=? WHERE id=?";
    private static final String DELETE_INDIVIDUAL =
            "DELETE FROM Individuals WHERE id = ?";

    @Override
    public void create(Individual individual) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(CREATE_INDIVIDUAL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, individual.getUsername());
            ps.setString(2, individual.getPassword());
            ps.setString(3, individual.getEmail());
            ps.setString(4, individual.getFirstName());
            ps.setString(5, individual.getLastName());
            ps.setDate(6, individual.getDate());
            ps.setInt(7, individual.getLanguage());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to create individual: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public Individual getByID(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Individual individual = new Individual();
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(GET_INDIVIDUAL_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            individual.setUsername(rs.getString("username"));
            individual.setPassword(rs.getString("password"));
            individual.setEmail(rs.getString("email"));
            individual.setFirstName(rs.getString("first_name"));
            individual.setLastName(rs.getString("last_name"));
            individual.setDate(rs.getDate("birth_date"));
            individual.setLanguageId(rs.getInt("Languages_id"));
        } catch (SQLException e) {
            logger.error("SQLException trying to get individual by ID: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
        return individual;
    }

    @Override
    public void update(Individual individual, int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(UPDATE_CART, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, individual.getUsername());
            ps.setString(2, individual.getPassword());
            ps.setString(3, individual.getEmail());
            ps.setString(4, individual.getFirstName());
            ps.setString(5, individual.getLastName());
            ps.setDate(6, individual.getDate());
            ps.setInt(7, individual.getLanguage());
            ps.setInt(8, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to update individual: ", e);
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
            ps = c.prepareStatement(DELETE_INDIVIDUAL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to delete individual: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }
}
