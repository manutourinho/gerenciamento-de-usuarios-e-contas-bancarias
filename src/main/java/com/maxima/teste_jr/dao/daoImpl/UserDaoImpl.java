package com.maxima.teste_jr.dao.daoImpl;

import com.maxima.teste_jr.config.DatabaseUtil;
import com.maxima.teste_jr.dao.UserDao;
import com.maxima.teste_jr.model.User;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final DatabaseUtil util;

    public UserDaoImpl() {
        util = new DatabaseUtil();
    }

    @Override
    public void createUsersTable() {
        try (Statement statement = util.getLocalConnection().createStatement()) {
            statement.executeUpdate("""
                        CREATE TABLE IF NOT EXISTS users (
                            ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                            nome VARCHAR(255),
                            idade INT,
                            cpf VARCHAR(14) UNIQUE
                        )
                    """);

            System.out.println("a tabela users foi criada!");

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("erro ao criar o tabela users: " + e.getMessage());
            e.getStackTrace();

        }

    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = util.getLocalConnection()
                .createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("erro ao remover tabela users" + e.getMessage());
            e.getStackTrace();

        }
    }

    @Override
    public User saveUser(User user) {
        try (PreparedStatement statement = util.getLocalConnection()
                .prepareStatement("INSERT INTO users (nome, idade, cpf) VALUES (?, ?, ?)")) {
            statement.setString(1, user.getNome());
            statement.setInt(2, user.getIdade());
            statement.setString(3, user.getCpf());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                }
            }

            return user;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("erro ao salvar o user: " + e.getMessage());

        }
    }

    @Override
    public void removeUserById(Long id) {
        try (PreparedStatement statement = util.getLocalConnection()
                .prepareStatement("DELETE FROM users WHERE ID = ?")) {
            statement.setLong(1, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.err.println("nenhum usuário encontrado com este ID: " + id);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("erro ao remover o user: " + e.getMessage());
            e.getStackTrace();

        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = util.getLocalConnection()
                .prepareStatement("SELECT * FROM users")) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User(
                            resultSet.getLong("ID"),
                            resultSet.getString("NOME"),
                            resultSet.getInt("IDADE"),
                            resultSet.getString("CPF"));
                    users.add(user);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("erro ao buscar todos os users: " + e.getMessage());
            e.printStackTrace();

        }

        return users;
    }

    @Override
    public User getUserById(Long id) {
        try (PreparedStatement statement = util.getLocalConnection()
                .prepareStatement("SELECT * FROM users WHERE ID = ?")) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getLong("ID"),
                        resultSet.getString("NOME"),
                        resultSet.getInt("IDADE"),
                        resultSet.getString("CPF")
                );
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("conta não encontrada/erro ao buscar conta: " + e.getMessage());

        }

        return null;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement statement = util.getLocalConnection()
                .createStatement()) {
            statement.executeUpdate("DELETE FROM users");
            System.out.println("a tabela users foi limpa!");

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("erro ao efetuar limpeza em tabela users: " + e.getMessage());
            e.getStackTrace();
        }
    }
}
