package com.maxima.teste_jr.data;

import com.maxima.teste_jr.config.DatabaseUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    private final DatabaseUtil util;

    public DatabaseInitializer(final DatabaseUtil util) {
        this.util = util;
    }

    public void createUsersTable() {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS users (
                ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                nome VARCHAR(255) NOT NULL,
                idade INT NOT NULL,
                cpf VARCHAR(14) NOT NULL UNIQUE
            )
        """;

        try (Connection connection = util.getLocalConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(createTableSQL);
            System.out.println("a tabela USERS foi criada com sucesso!");

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("erro ao criar tabela USERS: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
