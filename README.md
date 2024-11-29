# Gerenciamento de Usuários e Contas Bancárias 👥

---

## Descrição do Projeto
Este projeto é uma aplicação backend desenvolvida em Java, com foco na gestão de usuários e suas respectivas contas bancárias. Ele oferece endpoints RESTful para CRUD de usuários e gerenciamento de contas, incluindo operações para listar usuários, buscar por ID e visualizar informações relacionadas a suas contas bancárias.

---

## Tecnologias Utilizadas
- Java 17
- Spring Boot
- Spring Data JPA
- Banco de Dados H2 (em memória)
- REST API

---

## Decisões Técnicas e Justificativas

_**1. Banco de Dados H2:**_

Banco em memória configurado para facilitar o desenvolvimento e testes rápidos.
Justificativa: Permite rodar a aplicação sem a necessidade de instalação ou configuração de um banco externo.

**_2. Estrutura do Banco de Dados:_**

Tabela users: Armazena informações básicas dos usuários como nome, idade, e cpf.
Tabela contas: Relacionada à tabela users via titular_id (chave estrangeira), armazenando o saldo e número da conta.
Justificativa: A separação permite escalabilidade, como adicionar novos tipos de contas futuramente.

**_3. Formatação do CPF:_**

Usada no retorno dos dados para exibir o CPF no formato XXX.XXX.XXX-XX.
Justificativa: Melhor legibilidade para o consumidor da API.


**_4. Tratamento de Exceções:_**

Exceções como IllegalArgumentException retornam respostas HTTP apropriadas (ex.: 400 Bad Request ou 404 Not Found).
Justificativa: Fornecer respostas claras e consistentes ao cliente.

---

## Como Executar o Projeto
### Pré-requisitos
- Java 17 ou superior instalado.
- Um cliente HTTP para testes, como Postman ou cURL.

---

### Passos para Execução
1. Clone o repositório:
    ```bash
       git clone https://github.com/seu-usuario/nome-do-repositorio.git
        cd nome-do-repositorio
   
2. Compile e Execute o Projeto
    ```bash
       ./mvnw spring-boot:run

3. Acesse o Console do Banco de Dados H2
- Acesse a URL: http://localhost:8080/h2-console.
- Configuração de Conexão:
  - JDBC URL: jdbc:h2:mem:maxima
  - User: maxima
  - Password: (deixe em branco)
- Clique em Connect.

4. Verifique os Dados no Banco: Após executar os endpoints ou inserir dados, você pode visualizar as tabelas users e contas no console do H2.

---

## Inserindo Dados no Banco H2
Para testes, execute os scripts SQL abaixo diretamente no console do H2:

    ```bash
       INSERT INTO users (nome, idade, cpf) VALUES 
        ('João Silva', 30, '12345678901'),
        ('Maria Oliveira', 25, '98765432109');
        
        INSERT INTO contas (numero_conta, titular_id, saldo) VALUES
        (123456, 1, 1500.00),
        (987654, 2, 2500.00);

---

## Endpoints Disponíveis
### Usuários
1. URL: POST /api/users
2. URL: GET /api/users
3. URL: GET /api/users/{id}

### Transações
1. URL: GET /api/transactions


---

## 📧 Contato
- **Email**: [tourinhomanuelacontact@gmail.com](mailto:tourinhomanuelacontact@gmail.com)
- **LinkedIn**: [linkedin.com/in/manuelatourinho/](https://www.linkedin.com/in/manuelatourinho/)
- **GitHub**: [github.com/manutourinho/](https://github.com/manutourinho/)
