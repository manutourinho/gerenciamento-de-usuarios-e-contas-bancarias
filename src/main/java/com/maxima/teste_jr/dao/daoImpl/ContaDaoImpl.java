package com.maxima.teste_jr.dao.daoImpl;

import com.maxima.teste_jr.config.DatabaseUtil;
import com.maxima.teste_jr.dao.ContaDao;
import com.maxima.teste_jr.model.Conta;
import com.maxima.teste_jr.model.User;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class ContaDaoImpl implements ContaDao {

    private final DatabaseUtil util;

    public ContaDaoImpl() {
        util = new DatabaseUtil();
    }

    @Override
    public void createContaTable() {
        try (Statement statement = util.getLocalConnection().createStatement()) {
            statement.executeUpdate("""
                        CREATE TABLE IF NOT EXISTS contas (
                            ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                            numero_conta BIGINT DEFAULT FLOOR(RAND() * (999999 - 100000 + 1)) + 100000 UNIQUE NOT NULL,
                            titular_id BIGINT NOT NULL,
                            saldo DECIMAL(15, 2) DEFAULT 0 NOT NULL,
                            FOREIGN KEY (titular_id) REFERENCES users(ID)
                        )
                    """);

            System.out.println("a tabela contas foi criada!");

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("erro ao criar o tabela contas: " + e.getMessage());
            e.getStackTrace();

        }

    }

    @Override
    public void dropContaTable() {
        try (Statement statement = util.getLocalConnection()
                .createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS contas");

        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();

        }
    }

    @Override
    public void saveConta(Conta conta) {
        try (PreparedStatement statement = util.getLocalConnection()
                .prepareStatement("INSERT INTO contas (numero_conta, titular_id, saldo) VALUES (?, ?, ?)")) {
            statement.setLong(1, conta.getNumeroConta());
            statement.setLong(2, conta.getTitular().getId());
            statement.setBigDecimal(3, conta.getSaldo());
            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("erro ao salvar a conta: " + e.getMessage(), e);

        }
    }

    @Override
    public Conta getContaById(Long id) {
        try (PreparedStatement statement = util.getLocalConnection()
                .prepareStatement("SELECT * FROM contas WHERE ID = ?")) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Conta(
                        resultSet.getLong("id"),
                        new User(resultSet.getLong("titular_id")),
                        resultSet.getBigDecimal("saldo")
                );
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("conta não encontrada/erro ao buscar conta: " + e.getMessage());

        }

        return null;
    }

    @Override
    public Conta getContaByNumeroConta(Long numeroConta) {
        try (PreparedStatement statement = util.getLocalConnection()
                .prepareStatement("SELECT * FROM contas WHERE numero_conta = ?")) {
            statement.setLong(1, numeroConta);
            statement.executeQuery();

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Conta(
                        resultSet.getLong("id"),
                        new User(resultSet.getLong("titular_id")),
                        resultSet.getBigDecimal("saldo")
                );
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("conta não encontrada/erro ao buscar conta: " + e.getMessage());

        }

        return null;
    }


    @Override
    public void updateSaldo(Long numeroConta, BigDecimal novoSaldo) {
        try (PreparedStatement statement = util.getLocalConnection()
                .prepareStatement("UPDATE contas SET saldo = ? WHERE numero_conta = ?")) {
            statement.setBigDecimal(1, novoSaldo);
            statement.setLong(2, numeroConta);
            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("erro na atualização do saldo: " + e.getMessage());

        }

    }
}
