package com.maxima.teste_jr.dao;

import com.maxima.teste_jr.model.Conta;
import com.maxima.teste_jr.model.Transacao;

import java.util.List;

public interface TransacaoDao {

    void createTransacaoTable();

    void dropTransacaoTable();

    void saveTransacao(Transacao transacao);

    Transacao getTransacaoById(Long id);

    List<Transacao> getTransacoes();

}
