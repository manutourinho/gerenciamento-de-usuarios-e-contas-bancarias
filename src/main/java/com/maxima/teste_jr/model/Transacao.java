package com.maxima.teste_jr.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transacao {

    @EqualsAndHashCode.Include
    private Long id;
    private Conta remetente;
    private Conta destinatario;
    private BigDecimal valor;
    private LocalDateTime localDateTime;

    public Transacao(Conta remetente, Conta destinatario, BigDecimal valor, LocalDateTime localDateTime) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.valor = valor;
        this.localDateTime = LocalDateTime.now();
    }

    public Transacao(Conta remetente, Conta destinatario, BigDecimal valor) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.valor = valor;
    }
}
