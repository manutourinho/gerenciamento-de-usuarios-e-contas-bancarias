package com.maxima.teste_jr.service.serviceImpl;

import com.maxima.teste_jr.dao.TransacaoDao;
import com.maxima.teste_jr.dao.daoImpl.TransacaoDaoImpl;
import com.maxima.teste_jr.exceptions.SaldoInsuficienteException;
import com.maxima.teste_jr.model.Conta;
import com.maxima.teste_jr.model.Transacao;
import com.maxima.teste_jr.service.ContaService;
import com.maxima.teste_jr.service.TransacaoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TransacaoServiceImpl implements TransacaoService {

    private final TransacaoDao dao = new TransacaoDaoImpl();
    private final ContaService contaService =  new ContaServiceImpl();


    @Override
    public void createTransacaoTable() { dao.createTransacaoTable(); }

    @Override
    public void dropTransacaoTable() { dao.dropTransacaoTable(); }

    @Override
    public void realizarTransferencia(String contaOrigemNumero, String contaDestinoNumero, BigDecimal valor) {
        Conta contaOrigem = contaService.findByNumeroConta(Long.valueOf(contaOrigemNumero));
        Conta contaDestino = contaService.findByNumeroConta(Long.valueOf(contaDestinoNumero));

        if (contaOrigem == null || contaDestino == null) {
            throw new IllegalArgumentException("uma das contas não foi encontrada.");
        }

        if (contaOrigem.getSaldo().compareTo(valor) < 0) {
            throw new SaldoInsuficienteException("saldo insuficiente na conta de origem!");
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor)); // debito
        contaDestino.setSaldo(contaDestino.getSaldo().add(valor)); // credito

        contaService.updateSaldo(contaOrigem.getNumeroConta(), contaOrigem.getSaldo().subtract(valor));
        contaService.updateSaldo(contaDestino.getNumeroConta(), contaDestino.getSaldo().add(valor));

        Transacao transacao = new Transacao(contaOrigem, contaDestino, valor, LocalDateTime.now());
        dao.saveTransacao(transacao);
    }

    public Transacao getTransacaoById(Long id) { return dao.getTransacaoById(id); }

    public List<Transacao> getAllTransacoes() { return dao.getTransacoes(); }
    


}
