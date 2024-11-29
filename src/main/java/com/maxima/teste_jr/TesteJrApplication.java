package com.maxima.teste_jr;

import com.maxima.teste_jr.service.ContaService;
import com.maxima.teste_jr.service.TransacaoService;
import com.maxima.teste_jr.service.UserService;
import com.maxima.teste_jr.service.serviceImpl.ContaServiceImpl;
import com.maxima.teste_jr.service.serviceImpl.TransacaoServiceImpl;
import com.maxima.teste_jr.service.serviceImpl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TesteJrApplication {

	public static void main(String[] args) {
		UserService userService = new UserServiceImpl();
		ContaService contaService = new ContaServiceImpl();
		TransacaoService transacaoService = new TransacaoServiceImpl();

		userService.createUsersTable();
		contaService.createContaTable();
		transacaoService.createTransacaoTable();

		SpringApplication.run(TesteJrApplication.class, args);

	}

}
