package com.griddynamics.user.common;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import java.sql.Connection;

public class Database {

    private static HikariDataSource dataSource;

    public static void initialize(String url, String user, String password) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        if (dataSource != null) {
            dataSource.close();
        }
        dataSource = new HikariDataSource(config);
    }

    @SneakyThrows
    public static Connection getConnection()  {
        if (dataSource == null) {
            throw new IllegalStateException("DataSource is not initialized.");
        }
        return dataSource.getConnection();
    }
}



