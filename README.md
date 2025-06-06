# 🚀 Ascacibas Finances - API de Controle Financeiro Pessoal

Bem-vindo(a) ao Ascacibas Finances! Este é um projeto de API REST construída com **Java 21** e **Spring Boot 3**, com o objetivo de oferecer uma solução completa para controle financeiro pessoal.

A API foi desenvolvida com foco em:
- Boas práticas de arquitetura e desenvolvimento
- Testes unitários para garantir confiabilidade
- Segurança na autenticação e gerenciamento de usuários
- Evolução contínua para integração com um futuro frontend em React

---

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Security (JWT)**
- **Maven**
- **JUnit 5** + **Mockito** (Testes Unitários)
- **PostgreSQL** (banco de dados planejado)
- **Swagger OpenAPI 3** (Documentação interativa da API)
- **Postman** (Testes de endpoints)

---

## ⚙️ Funcionalidades Implementadas

- 🔐 **Autenticação e Registro**
  - `POST /auth/register`: Cadastrar novo usuário
  - `POST /auth/login`: Autenticar usuário e gerar token

- 👤 **Usuários**
  - `GET /user`: Listar todos usuários
  - `DELETE /user/{id}`: Deletar usuário

- 💰 **Receitas**
  - `POST /revenue`: Criar nova receita
  - `GET /revenue`: Listar todas as receitas
  - `DELETE /revenue/{id}`: Deletar receita

- 🛒 **Despesas**
  - `POST /expense`: Criar nova despesa
  - `GET /expense`: Listar todas as despesas
  - `GET /expense/listBetweenDate`: Listar despesas entre datas
  - `DELETE /expense/{id}`: Deletar despesa

- 🎯 **Metas Financeiras**
  - `POST /goal`: Criar meta financeira
  - `GET /goal`: Listar metas financeiras
  - `DELETE /goal/{id}`: Deletar meta

- 📊 **Resumo Financeiro Mensal**
  - `GET /summary/{ano}/{mes}`: Resumo de receitas, despesas e saldo por mês

---

## 🧪 Testes

- Testes unitários com **JUnit 5** e **Mockito**.
- Cobertura de testes: **99%** nas camadas de serviço.
- Testes de endpoints via **Postman**.

---

## 📚 Documentação da API

A documentação completa dos endpoints está disponível via **Swagger UI**:

Após subir a aplicação, acesse:
http://localhost:8080/swagger-ui/index.html
-------

## 🚧 Próximos Passos

- Integração com **frontend React** para uma aplicação web moderna e responsiva.
- Deploy da aplicação em cloud (AWS/GCP/Azure).
- Banco de dados PostgreSQL.
- Melhorias nas validações de dados.
- CI/CD para integração contínua.
- Dockerização da aplicação.

---

Feito com 💻 por Mateus Ascacibas
![Java](https://img.shields.io/badge/Java-21-blue.svg) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg) ![JUnit5](https://img.shields.io/badge/JUnit5-tested-brightgreen.svg) ![Coverage](https://img.shields.io/badge/Coverage-99%25-success.svg)

