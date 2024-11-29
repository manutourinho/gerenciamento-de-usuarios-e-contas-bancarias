# Gerenciamento de Usuários e Contas Bancárias
Descrição do Projeto
Este projeto é uma aplicação backend desenvolvida em Java, com foco na gestão de usuários e suas respectivas contas bancárias. Ele oferece endpoints RESTful para CRUD de usuários e gerenciamento de contas, incluindo operações para listar usuários, buscar por ID e visualizar informações relacionadas a suas contas bancárias.

--

# Tecnologias Utilizadas
- Java 17
- Spring Boot
- Spring Data JPA
- Banco de Dados H2 (em memória)
- REST API
- Decisões Técnicas e Justificativas

**Banco em memória configurado para facilitar o desenvolvimento e testes rápidos.**
Justificativa: Permite rodar a aplicação sem a necessidade de instalação ou configuração de um banco externo.
Estrutura do Banco de Dados:

Tabela users: Armazena informações básicas dos usuários como nome, idade, e cpf.
Tabela contas: Relacionada à tabela users via titular_id (chave estrangeira), armazenando o saldo e número da conta.
Justificativa: A separação permite escalabilidade, como adicionar novos tipos de contas futuramente.
Formatação do CPF:

Usada no retorno dos dados para exibir o CPF no formato XXX.XXX.XXX-XX.
Justificativa: Melhor legibilidade para o consumidor da API.
Tratamento de Exceções:

Exceções como IllegalArgumentException retornam respostas HTTP apropriadas (ex.: 400 Bad Request ou 404 Not Found).
Justificativa: Fornecer respostas claras e consistentes ao cliente.
Como Executar o Projeto
Pré-requisitos
Java 17 ou superior instalado.
Um cliente HTTP para testes, como Postman ou cURL.

--

