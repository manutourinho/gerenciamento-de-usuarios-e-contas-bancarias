package com.maxima.teste_jr.dao;

import com.maxima.teste_jr.model.Conta;

import java.math.BigDecimal;

public interface ContaDao {

    void createContaTable();

    void dropContaTable();

    void saveConta(Conta conta);

    Conta getContaById(Long id);

    Conta getContaByNumeroConta(Long numeroConta);

    void updateSaldo(Long numeroConta, BigDecimal saldo);

}
