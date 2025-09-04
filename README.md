# NeoApps - API de Cadastro de Clientes

![Spring Boot](https://spring.io/images/projects/spring-boot-4f3e2b22fb2a1c4865f79f5991d7c35e.png)

## Desafio

Este projeto foi desenvolvido como parte do processo de candidatura da NeoApps.  
O objetivo foi construir um **MVP de API REST** para cadastro de clientes pessoa física, com funcionalidades de:

- Inclusão de novos clientes
- Atualização de clientes existentes
- Exclusão de clientes existentes
- Listagem paginada de clientes
- Busca por atributos cadastrais (CPF, CEP, Estado, Nome, Email)
- Retorno da idade calculada a partir da data de nascimento
- Documentação Swagger para toda a API
- Segurança com Spring Security e JWT
- Persistência de dados com Spring Data JPA (H2 Database para testes)

---

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.3.x
- Spring Data JPA
- Spring Security + JWT
- H2 Database (banco em memória)
- Swagger/OpenAPI (springdoc)
- Lombok
- Maven

---

## Estrutura do Projeto

- `com.neoApps.neoApps.controller` → Controllers da API (`CustomerController`, `AuthController`)
- `com.neoApps.neoApps.dto` → DTOs para Requests e Responses (`CustomerCreateDTO`, `CustomerResponseDTO`, `CustomerUpdateDTO`)
- `com.neoApps.neoApps.model` → Entidade `Customer`
- `com.neoApps.neoApps.repository` → Repositório JPA
- `com.neoApps.neoApps.security` → JWT Util, Password Encoder, SecurityConfig
- `com.neoApps.neoApps.service` → Lógica de negócio e manipulação de clientes

---

## Como Rodar

### Com Maven

```bash
mvn clean spring-boot:run
