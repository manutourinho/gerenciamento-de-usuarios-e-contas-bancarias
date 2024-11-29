package com.maxima.teste_jr.service.serviceImpl;

import com.maxima.teste_jr.dao.ContaDao;
import com.maxima.teste_jr.dao.UserDao;
import com.maxima.teste_jr.dao.daoImpl.ContaDaoImpl;
import com.maxima.teste_jr.dao.daoImpl.UserDaoImpl;
import com.maxima.teste_jr.dto.UserResponseDto;
import com.maxima.teste_jr.model.Conta;
import com.maxima.teste_jr.model.User;
import com.maxima.teste_jr.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao dao = new UserDaoImpl();
    private final ContaDao contaDao = new ContaDaoImpl();


    @Override
    public void createUsersTable() { dao.createUsersTable(); }

    @Override
    public void dropUsersTable() { dao.dropUsersTable(); }

    @Override
    public User saveUser(User user) {
        if (user.getIdade() < 18) {
            throw new IllegalArgumentException("Apenas usuários com 18 anos ou mais podem ser cadastrados");
        }

        User userToSave = User.builder()
                .nome(user.getNome())
                .idade(user.getIdade())
                .cpf(user.getCpf())
                .build();

        dao.saveUser(userToSave);
        return userToSave;
    }

    @Override
    public void removeUserById(Long id) { dao.removeUserById(id); }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = dao.getAllUsers();

        return users.stream()
                .map(this::convertUserToUserResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = dao.getUserById(id);

        if (user == null) {
            throw new IllegalArgumentException("usuário com a ID não encontrado " + id);
        }

        return convertUserToUserResponseDto(user);
    }

    @Override
    public void cleanUsersTable() { dao.cleanUsersTable(); }

    private UserResponseDto convertUserToUserResponseDto(User user) {
        Conta conta = contaDao.getContaById(user.getId());

        return UserResponseDto.builder()
                .id(user.getId())
                .nome(user.getNome())
                .idade(user.getIdade())
                .cpf(formatCpf(user.getCpf()))
                .numeroConta(conta != null ? conta.getNumeroConta() : null)
                .saldo(conta != null ? conta.getSaldo() : BigDecimal.ZERO)
                .build();
    }

    public static String formatCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            throw new IllegalArgumentException("CPF inválido!");

        }

        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");

    }


}
