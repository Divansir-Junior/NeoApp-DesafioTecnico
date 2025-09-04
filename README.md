# 🚀 NeoApps - API de Cadastro de Clientes (Teste Técnico)

![Spring Boot](https://raw.githubusercontent.com/spring-projects/spring-boot/main/spring-boot.png)

> Este projeto foi desenvolvido como parte do **processo seletivo da NeoApps**.  
> O objetivo é demonstrar habilidades em **Spring Boot, REST API, Spring Security, JWT e JPA**.

---

## 📝 Objetivo do Teste

Construir um **MVP de API REST** para cadastro de clientes pessoa física com funcionalidades essenciais:

- ✅ Criar novos clientes  
- ✅ Atualizar clientes existentes  
- ✅ Excluir clientes  
- ✅ Listar clientes de forma paginada  
- ✅ Buscar clientes por atributos cadastrais (CPF, CEP, Estado, Nome, Email)  
- ✅ Retornar a **idade calculada** a partir da data de nascimento  
- ✅ Documentar a API com **Swagger/OpenAPI**  
- ✅ Proteger endpoints com **Spring Security + JWT**  
- ✅ Utilizar **Spring Data JPA** para persistência  

---

## 🛠 Tecnologias Utilizadas

- 💻 **Java 21**  
- 🌱 **Spring Boot 3.3.x**  
- 🔐 **Spring Security + JWT**  
- 🗄 **Spring Data JPA** (H2 Database em memória)  
- 📄 **Swagger/OpenAPI (springdoc)**  
- ✨ **Lombok**  
- ⚙️ **Maven**  

---

## 📁 Estrutura do Projeto

- `com.neoApps.neoApps.controller` → Controllers da API (`CustomerController`, `AuthController`)  
- `com.neoApps.neoApps.dto` → DTOs de Request/Response (`CustomerCreateDTO`, `CustomerResponseDTO`, `CustomerUpdateDTO`)  
- `com.neoApps.neoApps.model` → Entidade `Customer`  
- `com.neoApps.neoApps.repository` → Repositório JPA  
- `com.neoApps.neoApps.security` → JWT Util, Password Encoder, SecurityConfig  
- `com.neoApps.neoApps.service` → Lógica de negócio e manipulação de clientes  

---

## ⚡ Funcionalidades

### 🔑 Autenticação

- `POST /auth/login`  
  Recebe **email** e **password** e retorna um **JWT** para acesso aos endpoints protegidos.

### 👤 Clientes

- `POST /customers` → Criar cliente (JWT necessário)  
- `GET /customers` → Listar todos os clientes (JWT necessário)  
- `GET /customers/search` → Buscar clientes por atributos (JWT necessário)  
- `DELETE /customers/{id}` → Excluir cliente por ID (JWT necessário)  

> Todos os endpoints protegidos devem receber o header:  
> `Authorization: Bearer <token>`

---

## 📚 Swagger

A documentação da API está disponível em:
http://localhost:8080/swagger-ui.html

