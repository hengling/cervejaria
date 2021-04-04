# Cervejaria

O projeto Cervejaria tem como objetivo viabilizar o controle do estoque de cerveja de seu estabelecimento.
O projeto criado para desenvolver conhecimentos em testes unitários com JUnit 5 e Clean Architecture.

## Ambiente

* Java 11
* Maven 3.6.3 (ou superior)

## Executando o projeto

No terminal, entre na pasta raiz do projeto e execute os seguintes comandos:

```bash
./mvnw clean install
./mvnw spring-boot:run 
```

Os serviços da `api` ficarão disponíveis em `http://localhost:8080/api`.
Consulte o `swagger` para verificar o catálogo de serviços disponíveis em `http://localhost:8080/swagger-ui.html`.

## Executando os testes

No terminal, entre na pasta raiz do projeto e execute os seguintes comandos:

```bash
./mvnw clean test
```