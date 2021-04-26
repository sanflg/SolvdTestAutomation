package com.solvd.dataBaseOnlineShop.dao.jdbc.impl.comerce;

import com.solvd.dataBaseOnlineShop.dao.interfaces.comerce.ISupplierDAO;
import com.solvd.dataBaseOnlineShop.dao.jdbc.AbstractJDBC;
import com.solvd.dataBaseOnlineShop.models.comerce.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class SupplierDAO extends AbstractJDBC implements ISupplierDAO {
    private static final Logger logger = LogManager.getLogger(SupplierDAO.class);
    private static final String CREATE_SUPPLIER =
            "INSERT INTO Suppliers (Individuals_id) VALUES (?)";
    private static final String GET_SUPPLIER_BY_ID =
            "SELECT * FROM Suppliers WHERE id=?";
    private static final String UPDATE_SUPPLIER =
            "UPDATE Suppliers SET Individuals_id=? WHERE id=?";
    private static final String DELETE_SUPPLIER =
            "DELETE FROM Suppliers WHERE id=?";

    @Override
    public void create(Supplier supplier) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(CREATE_SUPPLIER, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, supplier.getIndividualId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to create supplier: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public Supplier getByID(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Supplier supplier = new Supplier();
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(GET_SUPPLIER_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            supplier.setId(rs.getInt("id"));
            supplier.setIndividualId(rs.getInt("Individuals_id"));
        } catch (SQLException e) {
            logger.error("SQLException trying to get supplier by ID: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
        return supplier;
    }

    @Override
    public void update(Supplier supplier) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(UPDATE_SUPPLIER, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, supplier.getIndividualId());
            ps.setInt(2, supplier.getId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to update supplier: ", e);
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
            ps = c.prepareStatement(DELETE_SUPPLIER, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to delete supplier: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }
}
