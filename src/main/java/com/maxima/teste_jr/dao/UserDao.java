package com.maxima.teste_jr.dao;

import com.maxima.teste_jr.model.User;

import java.util.List;

public interface UserDao {

    void createUsersTable();

    void dropUsersTable();

    User saveUser(User user);

    void removeUserById(Long id);

    List<User> getAllUsers();

    User getUserById(Long id);

    void cleanUsersTable();

}
