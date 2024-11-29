package com.maxima.teste_jr.config;

import lombok.Builder;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
@Component
public class DatabaseUtil {

    private final String url = "jdbc:h2:mem:maxima";
    private final String username = "maxima";
    private final String password = "";

    private static Connection connection;

    public Connection getLocalConnection() throws SQLException, ClassNotFoundException {
        if (connection == null || connection.isClosed()) {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
        }

        return connection;
    }



}
