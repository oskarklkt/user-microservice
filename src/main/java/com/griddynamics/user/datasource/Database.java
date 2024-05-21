package com.griddynamics.user.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import java.sql.Connection;

@Getter
@Setter
public class Database {

    private HikariDataSource dataSource;
    private String url;
    private String username;
    private String password;

    @SneakyThrows
    public Connection getConnection() {
        return dataSource.getConnection();
    }

}



