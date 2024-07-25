# Simulador de PicPay - API com Spring Boot

Este é um projeto de uma API simplificada que simula o funcionamento básico do PicPay. Os usuários podem realizar transações entre si, e há um mock que simula um serviço de autorização para realizar a transação.
- [Link para o github do desafio](https://github.com/PicPay/picpay-desafio-backend)
## Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Uso](#uso)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Mock de Serviço de Autorização](#mock-de-serviço-de-autorização)
- [Contribuição](#contribuição)

## Sobre o Projeto

Este projeto foi desenvolvido para simular operações básicas de transações entre usuários, similar ao PicPay. Ele inclui um mock de serviço de autorização para validar transações.
Ocorreu problemas no mock referente as notificações, por isso acabei não conseguindo implementar.

## Funcionalidades

- Criação de usuários
- Realização de transações entre usuários
- Mock de serviço de autorização para validação de transações

## Pré-requisitos

Para rodar este projeto, você vai precisar ter instalado:

- Java 11 ou superior
- Maven
- PostgreSQL (ou outro banco de dados relacional de sua preferência)

## Instalação

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/simulador-picpay.git
cd simulador-picpay
```

2. Configure o banco de dados:

Crie um banco de dados com o H2databse e ajuste as configurações no arquivo `application.properties` localizado em `src/main/resources`:
```properties
spring.datasource.url = jdbc:h2:mem:test_mem
spring.datasource.driver-class-name = org.h2.Driver
spring.datasource.username = seu-usarname
spring.datasource.password = senha(pode ficar vázia)
spring.jpa.database-plataform = org.hibernate.dialect.H2Dialect
spring.h2.console.enabled = true
```

3. Compile e execute a aplicação:
```bash
mvn clean install
mvn spring-boot:run
```

## Uso

A API estará disponível em `http://localhost:8080`. Você pode usar ferramentas como Postman ou Insomnia para interagir com a API.

### Endpoints

- **Criar Usuário:**
- `POST /user`
- Corpo da Requisição:
```json
{
    "firstName": "First Name",
    "lastName": "Last Name",
    "balance": 100,
    "document": "123456",
    "email": "email@example.com",
    "password": "123",
    "type": "COMMON"
}
```
- Nota: O campo `type` pode ser `COMMON` ou `MERCHANT`.

---

- **Consultar todos os usuários:**
- `GET /users`

- **Realizar Transação:**
- `POST /transactions`
- Corpo da Requisição:
```json
{
    "value": 10,
    "senderId": 2,
    "receiverId": 1
}
```

## Estrutura do Projeto
```
simulador-picpay
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── simplepicpay
│   │   │           ├── controllers
│   │   │           │   ├── TransactionController.java
│   │   │           │   └── UserController.java
│   │   │           ├── domain
│   │   │           │   ├── transaction
│   │   │           │   │   └── Transaction.java
│   │   │           │   └── user
│   │   │           │       ├── User.java
│   │   │           │       ├── UserType.java
│   │   │           ├── dto
│   │   │           │    ├── TransactionDTO.java
│   │   │           │    ├── UserDTO.java
│   │   │           ├── infra
│   │   │           │    ├── AppConfig.java
│   │   │           ├── repositories
│   │   │           │    ├── TransactionRepository.java
│   │   │           │    ├── UserRepository.java
│   │   │           ├── services
│   │   │           │    ├── TransactionService.java
│   │   │           │    ├── UserService.java
│   │   │           └── SimplePicpayApplication.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── com
│               └── simplepicpay
│                   └── simuladorpicpay
│                       └── SimplePicpayApplicationTests.java
|
├── .gitignore
├── mvnw
├── mvnw.cmd
└── pom.xml
```

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Lombok
- H2Database
- Maven

## Mock de Serviço de Autorização

Para a simulação do serviço de autorização, foi utilizado um mock. O código do mock pode ser encontrado [aqui](https://util.devi.tools/api/v2/authorize).

Certifique-se de configurar e executar o mock antes de iniciar a aplicação principal para garantir que as transações sejam autorizadas corretamente.

## Contribuição

Contribuições são bem-vindas! Se você tiver alguma sugestão ou encontrar algum problema, por favor, abra uma issue ou envie um pull request.

---
