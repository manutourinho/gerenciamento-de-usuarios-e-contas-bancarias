package com.maxima.teste_jr.service.serviceImpl;

import com.maxima.teste_jr.dao.ContaDao;
import com.maxima.teste_jr.dao.daoImpl.ContaDaoImpl;
import com.maxima.teste_jr.model.Conta;
import com.maxima.teste_jr.model.User;
import com.maxima.teste_jr.service.ContaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Random;

@Service
@Transactional
public class ContaServiceImpl implements ContaService {

    private final ContaDao dao = new ContaDaoImpl();


    @Override
    public void createContaTable() { dao.createContaTable(); }

    @Override
    public void dropContaTable() { dao.dropContaTable(); }

    @Override
    public Conta saveConta(User titular) {
        if (titular.getIdade() < 18) {
            throw new IllegalArgumentException("apenas usuários com 18 anos ou mais podem criar uma conta.");
        }

        Conta conta = Conta.builder()
                .numeroConta(generateAccountNumber())
                .titular(titular)
                .saldo(BigDecimal.ZERO)
                .build();

        dao.saveConta(conta);

        return conta;
    }

    public Conta findById(Long id) {
        Conta conta = dao.getContaById(id);
        if (conta == null) {
            throw new IllegalArgumentException("conta com ID " + id + " não encontrada.");
        }
        return conta;
    }

    public Conta findByNumeroConta(Long numeroConta) {
        Conta conta = dao.getContaByNumeroConta(numeroConta);
        if (conta == null) {
            throw new IllegalArgumentException("conta de número " + numeroConta + " não encontrada.");
        }
        return conta;
    }

    public void updateSaldo(Long numeroConta, BigDecimal novoSaldo) {
        Conta conta = findByNumeroConta(numeroConta);
        dao.updateSaldo(numeroConta, novoSaldo);
    }

    private Long generateAccountNumber() {
        Random rand = new Random();

        return rand.nextLong(9999999999L - 1000000000L + 1) + 1000000000L;
    }
}

