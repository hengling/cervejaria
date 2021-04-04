# Cervejaria

O projeto Cervejaria tem como objetivo viabilizar o controle do estoque de cerveja de seu estabelecimento.
O projeto criado para desenvolver conhecimentos em testes unitários com JUnit 5 e Clean Architecture.

## Ambiente

* Java 11
* Maven 3.6.3 (ou superior)

## Arquitetura do projeto

* **application**: pacote que concentra classes de configuração e a classe da aplicação `spring boot`;
* **data**: pacote responsável pelo gerenciamento dos dados em fontes diversas (banco de dados, outros sistemas e etc...);
* **domain**: pacote responsável pelas regras de negócio; não possui, e nunca possuirá, dependência de nenhum framework;
* **entrypoint**: pacote responsável pelo meio de entrada para o sistema; no nosso caso, são os controllers que expõem as `API's rest`;
* **util**: pacote que armazena utilitários que podem ser utilizados em todos os pacotes acima;

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