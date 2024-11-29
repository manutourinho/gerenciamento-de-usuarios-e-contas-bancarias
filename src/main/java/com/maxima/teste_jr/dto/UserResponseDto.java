package com.maxima.teste_jr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

    private Long id;

    private String nome;

    private int idade;

    private String cpf;

    private Long numeroConta;

    private BigDecimal saldo;

}

