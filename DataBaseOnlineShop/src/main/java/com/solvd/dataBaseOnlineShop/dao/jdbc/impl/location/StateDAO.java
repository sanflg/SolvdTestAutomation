package com.solvd.dataBaseOnlineShop.dao.jdbc.impl.location;

import com.solvd.dataBaseOnlineShop.dao.interfaces.location.IStateDAO;
import com.solvd.dataBaseOnlineShop.dao.jdbc.AbstractJDBC;
import com.solvd.dataBaseOnlineShop.models.location.State;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class StateDAO extends AbstractJDBC implements IStateDAO {
    private static final Logger logger = LogManager.getLogger(StateDAO.class);
    private static final String CREATE_STATE =
            "INSERT INTO States (name, Countries_id) VALUES (?, ?)";
    private static final String GET_STATE_BY_ID =
            "SELECT * FROM States WHERE id = ?";
    private static final String UPDATE_STATE =
            "UPDATE States SET name=?, Countries_id=? WHERE id=?";
    private static final String DELETE_STATE =
            "DELETE FROM States WHERE id=?";

    @Override
    public void create(State state) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(CREATE_STATE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, state.getName());
            ps.setInt(2, state.getCountry());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to create state: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public State getByID(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        State state = new State();
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(GET_STATE_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            state.setId(rs.getInt("id"));
            state.setName(rs.getString("name"));
            state.setCountryId(rs.getInt("Countries_id"));

        } catch (SQLException e) {
            logger.error("SQLException trying to get state by ID: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
        return state;
    }

    @Override
    public void update(State state) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(UPDATE_STATE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, state.getName());
            ps.setInt(2, state.getCountry());
            ps.setInt(3, state.getId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to update state: ", e);
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
            ps = c.prepareStatement(DELETE_STATE, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to delete state: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }
}
