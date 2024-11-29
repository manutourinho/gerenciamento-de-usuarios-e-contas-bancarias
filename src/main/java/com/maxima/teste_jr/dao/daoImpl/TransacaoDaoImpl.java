package com.maxima.teste_jr.dao.daoImpl;

import com.maxima.teste_jr.config.DatabaseUtil;
import com.maxima.teste_jr.dao.ContaDao;
import com.maxima.teste_jr.dao.TransacaoDao;
import com.maxima.teste_jr.model.Conta;
import com.maxima.teste_jr.model.Transacao;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransacaoDaoImpl implements TransacaoDao {

    private final DatabaseUtil util;
    private ContaDao contaDao;

    public TransacaoDaoImpl() {
        util = new DatabaseUtil();
    }

    @Override
    public void createTransacaoTable() {
        try (Statement statement = util.getLocalConnection().createStatement()) {
            statement.executeUpdate("""
                        CREATE TABLE IF NOT EXISTS transacoes (
                            ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                            conta_remetente BIGINT NOT NULL,
                            conta_destinataria BIGINT NOT NULL,
                            valor DECIMAL(15, 2) NOT NULL,
                            localDateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                            FOREIGN KEY (conta_remetente) REFERENCES contas(numero_conta),
                            FOREIGN KEY (conta_destinataria) REFERENCES contas(numero_conta)
                        )
                    """);

            System.out.println("a tabela transacoes foi criada!");

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("erro ao criar o tabela transacoes: " + e.getMessage());
            e.getStackTrace();

        }

    }

    @Override
    public void dropTransacaoTable() {
        try (Statement statement = util.getLocalConnection()
                .createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS transacoes");

        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();

        }

    }

    @Override
    public void saveTransacao(Transacao transacao) {
        try (PreparedStatement statement = util.getLocalConnection()
                .prepareStatement("INSERT INTO transacoes (conta_rementente, conta_destinataria, valor, localDateTime) VALUES (?, ?, ?, ?)")) {
            statement.setLong(1, transacao.getRemetente().getNumeroConta());
            statement.setLong(2, transacao.getDestinatario().getNumeroConta());
            statement.setBigDecimal(3, transacao.getValor());
            statement.setTimestamp(4, Timestamp.valueOf(transacao.getLocalDateTime()));
            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("erro ao efetuar a transação: " + e.getMessage());

        }

    }

    @Override
    public Transacao getTransacaoById(Long id) {
        Transacao transacao = null;

        try (PreparedStatement statement = util.getLocalConnection()
                .prepareStatement("SELECT * FROM transacoes WHERE ID = ?")) {
            statement.setLong(1, id);


            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Conta remetente = contaDao.getContaById(resultSet.getLong("conta_remetente"));
                Conta destinatario = contaDao.getContaById(resultSet.getLong("conta_destinataria"));
                BigDecimal valor = resultSet.getBigDecimal("valor");
                LocalDateTime localDateTime = resultSet.getTimestamp("localdatetime").toLocalDateTime();

                transacao = new Transacao(resultSet.getLong("id"), remetente, destinatario, valor, localDateTime);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("conta não encontrada/erro ao buscar conta: " + e.getMessage());

        }

        return transacao;
    }

    @Override
    public List<Transacao> getTransacoes() {
        List<Transacao> transacoes = new ArrayList<>();
        try (Statement statement = util.getLocalConnection()
                .createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM transacoes")) {

            while (resultSet.next()) {
                Conta remetente = contaDao.getContaById(resultSet.getLong("conta_remetente"));
                Conta destinatario = contaDao.getContaById(resultSet.getLong("conta_destinataria"));
                BigDecimal valor = resultSet.getBigDecimal("valor");
                LocalDateTime localDateTime = resultSet.getTimestamp("localdatetime").toLocalDateTime();

                Transacao transacao = new Transacao(resultSet.getLong("id"), remetente, destinatario, valor, localDateTime);
                transacoes.add(transacao);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();
        }
        return transacoes;

    }

}
