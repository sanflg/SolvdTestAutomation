package com.solvd.dataBaseOnlineShop.dao.connectionPool;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBCPDataSource {
    private static BasicDataSource dataSource;

    public static BasicDataSource getDataSource(){
        if (dataSource == null){
            dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/onlineshop");
            dataSource.setUsername("root");
            dataSource.setPassword("Jkks92j(),/KLaj2");
            dataSource.setMaxIdle(10);
            dataSource.setMaxOpenPreparedStatements(100);
        }
        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private DBCPDataSource(){ }
}