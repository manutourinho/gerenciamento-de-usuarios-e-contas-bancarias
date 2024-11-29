package com.maxima.teste_jr.data;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseSeeder {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseSeeder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void seed() {
//        String createTableSql = """
//            CREATE TABLE IF NOT EXISTS users (
//                id BIGINT AUTO_INCREMENT PRIMARY KEY,
//                nome VARCHAR(255),
//                idade INT,
//                cpf VARCHAR(14)
//            )
//        """;
//        jdbcTemplate.execute(createTableSql);

        String insertSql = "INSERT INTO users (nome, idade, cpf) VALUES ('José', 20, '866.315.050-46')";
        jdbcTemplate.execute(insertSql);
    }
}
