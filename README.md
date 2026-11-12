<div align="center">

# 🛒 E-commerce API

**API REST para explorar operações e regras de negócio de um sistema de comércio eletrônico.**

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.0-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Driver-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Status](https://img.shields.io/badge/Status-Em_desenvolvimento-yellow?style=for-the-badge)

</div>

## 📖 Sobre o projeto

A **E-commerce API** é um projeto back-end em desenvolvimento voltado ao domínio de comércio eletrônico. A proposta é evoluir uma aplicação Spring Boot com regras de negócio, persistência, segurança e integrações externas.

> 🚧 **Projeto em desenvolvimento.** A presença de uma dependência no projeto não significa que a funcionalidade correspondente esteja concluída.

## 🛠️ Tecnologias

Dependências identificadas no `pom.xml`:

- **Java 17** e **Spring Boot 4.1.0**
- **Spring Web MVC** e **Spring Data JPA**
- **PostgreSQL** e **H2** (drivers declarados)
- **Spring Security** e biblioteca **Auth0 Java JWT** (dependências presentes; fluxo JWT ainda não concluído)
- **Bean Validation**
- **Springdoc OpenAPI** (dependência de documentação de API)
- **Lombok** e **Maven**

## 🧩 Domínio e evolução

A proposta da API é servir como base para operações de comércio eletrônico. Os casos de uso e endpoints já implementados serão detalhados após a revisão dos controllers e serviços.

<!-- TODO: preencher os endpoints e funcionalidades implementadas, sem confundir com o roadmap. -->

## ▶️ Como executar

**Pré-requisitos:** JDK 17 e banco configurado conforme os arquivos da aplicação.

```bash
git clone https://github.com/jdaniel-fonseca/ecommerce-api.git
cd ecommerce-api
./mvnw spring-boot:run
```

No Windows, utilize `mvnw.cmd spring-boot:run`.

> Verifique os perfis e configurações de banco em `src/main/resources`. A execução não foi validada neste ambiente.

## 🗺️ Roadmap

- [ ] Concluir autenticação e autorização com JWT
- [ ] Implementar notificações de e-mail transacionais (por exemplo, confirmação de pedido)
- [ ] Ampliar testes unitários e de integração
- [ ] Documentar endpoints, exemplos de payload e respostas
- [ ] Preparar Docker e Docker Compose
- [ ] Fazer deploy na AWS

**Ideia de integração de e-mail:** utilizar Spring Mail com um provedor SMTP ou Amazon SES, mantendo credenciais em variáveis de ambiente.

## 👨🏽‍💻 Autor

Desenvolvido por **José Daniel de Oliveira Fonseca**.

[GitHub](https://github.com/jdaniel-fonseca) · [Repositório](https://github.com/jdaniel-fonseca/ecommerce-api)
