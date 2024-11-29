package com.maxima.teste_jr.controller;

import com.maxima.teste_jr.exceptions.SaldoInsuficienteException;
import com.maxima.teste_jr.model.Transacao;
import com.maxima.teste_jr.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @Autowired
    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }


    @PostMapping
    public ResponseEntity<Map<String, String>> createTransacao(@RequestBody Map<String, Object> payload) {
        try {
            String contaOrigem = payload.get("contaOrigem").toString();
            String contaDestino = payload.get("contaDestino").toString();
            BigDecimal valor = new BigDecimal(payload.get("valor").toString());

            transacaoService.realizarTransferencia(contaOrigem, contaDestino, valor);

            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "transferência realizada com sucesso.");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (SaldoInsuficienteException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "saldo insuficiente na conta originária para a transação!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "erro ao realizar transação: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }

    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getTransacaoById(@PathVariable Long id) {
        Transacao transacao = transacaoService.getTransacaoById(id);

        if (transacao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transação não encontrada.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(transacao);
    }


    @GetMapping
    public ResponseEntity<List<Transacao>> getAllTransacoes() {
        List<Transacao> transacoes = transacaoService.getAllTransacoes();
        return ResponseEntity.status(HttpStatus.OK).body(transacoes);
    }


}

