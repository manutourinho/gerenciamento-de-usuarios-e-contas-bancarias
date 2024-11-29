package com.maxima.teste_jr.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Conta {

    @EqualsAndHashCode.Include
    private Long id;
    private User titular;
    private Long numeroConta;
    private BigDecimal saldo = BigDecimal.ZERO;
    private List<Transacao> transacoes;

    public Conta(Long id, User titularId, BigDecimal saldo) {
        this.id = id;
        this.titular = titularId;
        this.saldo = saldo;
    }

}
