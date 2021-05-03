package com.solvd.dataBaseOnlineShop.dao.jdbc.impl.individual;

import com.solvd.dataBaseOnlineShop.dao.interfaces.individual.IIndividualStatusDAO;
import com.solvd.dataBaseOnlineShop.dao.jdbc.AbstractJDBC;
import com.solvd.dataBaseOnlineShop.models.individual.IndividualStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class IndividualStatusDAO extends AbstractJDBC implements IIndividualStatusDAO {
    private static final Logger logger = LogManager.getLogger(IndividualStatusDAO.class);
    private static final String CREATE_INDIVIDUALSTATUS =
            "INSERT INTO IndividualStatuses (IsAdmin, IsNew, IsBanned, Individuals_id) VALUES (?, ?, ?, ?)";
    private static final String GET_INDIVIDUALSTATUS_BY_ID =
            "SELECT * FROM IndividualStatuses WHERE id = ?";
    private static final String UPDATE_INDIVIDUALSTATUS =
            "UPDATE IndividualStatuses SET IsAdmin=?, IsNew=?, IsBanned=?, Individuals_id=? WHERE id=?";
    private static final String DELETE_INDIVIDUALSTATUS =
            "DELETE FROM IndividualStatuses WHERE id=?";

    @Override
    public void create(IndividualStatus individualStatus) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(CREATE_INDIVIDUALSTATUS, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, individualStatus.isAdmin());
            ps.setBoolean(2, individualStatus.isNew());
            ps.setBoolean(3, individualStatus.isBanned());
            ps.setInt(4, individualStatus.getIndividualId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to create individualStatus: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public IndividualStatus getByID(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        IndividualStatus individualStatus = new IndividualStatus();
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(GET_INDIVIDUALSTATUS_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            individualStatus.setId(rs.getInt("id"));
            individualStatus.setAdmin(rs.getBoolean("IsAdmin"));
            individualStatus.setNew(rs.getBoolean("IsNew"));
            individualStatus.setBanned(rs.getBoolean("IsBanned"));
            individualStatus.setIndividualId(rs.getInt("Individuals_id"));
        } catch (SQLException e) {
            logger.error("SQLException trying to get individualStatus by ID: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
        return individualStatus;
    }

    @Override
    public void update(IndividualStatus individualStatus) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(UPDATE_INDIVIDUALSTATUS, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, individualStatus.isAdmin());
            ps.setBoolean(2, individualStatus.isNew());
            ps.setBoolean(3, individualStatus.isBanned());
            ps.setInt(4, individualStatus.getIndividualId());
            ps.setInt(5, individualStatus.getId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to update individualStatus: ", e);
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
            ps = c.prepareStatement(DELETE_INDIVIDUALSTATUS, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to delete individualStatus: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }
}
