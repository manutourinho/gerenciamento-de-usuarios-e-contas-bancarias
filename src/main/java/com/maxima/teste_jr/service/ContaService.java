package com.maxima.teste_jr.service;

import com.maxima.teste_jr.model.Conta;
import com.maxima.teste_jr.model.User;

import java.math.BigDecimal;

public interface ContaService {

    public void createContaTable();

    public void dropContaTable();

    Conta saveConta(User titular);

    Conta findById(Long id);

    Conta findByNumeroConta(Long numeroConta);

    void updateSaldo(Long numeroConta, BigDecimal novoSaldo);

}

