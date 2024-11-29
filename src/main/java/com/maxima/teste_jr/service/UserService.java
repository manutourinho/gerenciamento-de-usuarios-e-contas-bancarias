package com.maxima.teste_jr.service;

import com.maxima.teste_jr.dto.UserResponseDto;
import com.maxima.teste_jr.model.User;

import java.util.List;

public interface UserService {

    void createUsersTable();

    void dropUsersTable();

    User saveUser(User user);

    void removeUserById(Long id);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long id);

    void cleanUsersTable();
}
