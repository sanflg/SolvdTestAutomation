package com.solvd.dataBaseOnlineShop.dao.jdbc;

import com.solvd.dataBaseOnlineShop.dao.connectionPool.DBCPDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractJDBC {
    private static final Logger logger = LogManager.getLogger(AbstractJDBC.class);
    private final BasicDataSource cp =
            DBCPDataSource.getDataSource();

    public BasicDataSource getCp() {
        return cp;
    }

    public void closeResources(Connection c, PreparedStatement ps, ResultSet rs){
        try{
            if (c!=null){
                c.close();
            }if (ps!=null){
                ps.close();
            }if (rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            logger.error("Error closing resources in closeResources(), AbstractJDBC: ", e);
        }
    }

}
