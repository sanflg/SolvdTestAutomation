package com.solvd.dataBaseOnlineShop.dao.jdbc.impl.individual;

import com.solvd.dataBaseOnlineShop.dao.interfaces.individual.ILanguageDAO;
import com.solvd.dataBaseOnlineShop.dao.jdbc.AbstractJDBC;
import com.solvd.dataBaseOnlineShop.models.individual.Language;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class LanguageDAO extends AbstractJDBC implements ILanguageDAO {
    private static final Logger logger = LogManager.getLogger(LanguageDAO.class);
    private static final String CREATE_LANGUAGE =
            "INSERT INTO Languages (name, tag) VALUES (?, ?)";
    private static final String GET_LANGUAGE_BY_ID =
            "SELECT * FROM Languages WHERE id = ?";
    private static final String UPDATE_LANGUAGE =
            "UPDATE Languages SET name=?, tag=? WHERE id=?";
    private static final String DELETE_LANGUAGE =
            "DELETE FROM Languages WHERE id=?";

    @Override
    public void create(Language language) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(CREATE_LANGUAGE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, language.getName());
            ps.setString(2, language.getTag());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to create language: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }

    @Override
    public Language getByID(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Language language = new Language();
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(GET_LANGUAGE_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            language.setId(rs.getInt("id"));
            language.setName(rs.getString("name"));
            language.setTag(rs.getString("tag"));
        } catch (SQLException e) {
            logger.error("SQLException trying to get language by ID: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
        return language;
    }

    @Override
    public void update(Language language) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = getCp().getConnection();
            ps = c.prepareStatement(UPDATE_LANGUAGE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, language.getName());
            ps.setString(2, language.getTag());
            ps.setInt(3, language.getId());
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to update language: ", e);
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
            ps = c.prepareStatement(DELETE_LANGUAGE, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            logger.error("SQLException trying to delete language: ", e);
        }finally {
            closeResources(c, ps, rs);
        }
    }
}
