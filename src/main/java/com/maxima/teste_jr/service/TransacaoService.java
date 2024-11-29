package com.maxima.teste_jr.service;

import com.maxima.teste_jr.model.Transacao;

import java.math.BigDecimal;
import java.util.List;

public interface TransacaoService {

    public void createTransacaoTable();

    public void dropTransacaoTable();

    void realizarTransferencia(String contaOrigemNumero, String contaDestinoNumero, BigDecimal valor);

    public Transacao getTransacaoById(Long id);

    public List<Transacao> getAllTransacoes();

}
