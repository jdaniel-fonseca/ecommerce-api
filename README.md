<div align="center">

# 🛒 E-commerce API

API REST para praticar regras de negócio e integrações de um e-commerce.

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Status](https://img.shields.io/badge/Status-Em_desenvolvimento-yellow?style=for-the-badge)

</div>

## Sobre

API sendo desenvolvida para trabalhar com cenários de e-commerce e aprofundar meus conhecimentos em Java / Spring Boot, persistência de dados, segurança e regras de negócio.

O projeto ainda está em desenvolvimento; as próximas funcionalidades estão listadas abaixo.

## Tecnologias

- Java 17
- Spring Boot 4
- Spring Web MVC e Spring Data JPA
- Bean Validation
- Spring Security e biblioteca Java JWT (**dependências presentes; autenticação JWT ainda não concluída**)
- Springdoc OpenAPI (**dependência presente; documentação ainda será revisada**)
- Maven e Lombok
- PostgreSQL (driver presente; **será o banco adotado no projeto**)
- H2 (driver também presente)

## Próximos passos

- [ ] Consolidar as regras de negócio de produtos, pedidos e estoque
- [ ] Concluir autenticação e autorização com JWT
- [ ] Configurar e revisar a documentação Swagger / OpenAPI
- [ ] Adicionar envio de e-mails transacionais (como confirmação de pedido)
- [ ] Criar testes unitários e de integração
- [ ] Preparar Docker e Docker Compose
- [ ] Fazer deploy na AWS

## Como executar

```bash
git clone https://github.com/jdaniel-fonseca/ecommerce-api.git
cd ecommerce-api
./mvnw spring-boot:run
```

No Windows, utilize `mvnw.cmd spring-boot:run`. Confira os perfis e as propriedades de conexão em `src/main/resources` antes de iniciar.

> Os endpoints e os exemplos de requisição serão documentados conforme eu consolidar as funcionalidades.

## Autor

[José Daniel](https://github.com/jdaniel-fonseca)
