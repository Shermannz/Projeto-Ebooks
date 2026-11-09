# Projeto Ebooks

API REST de uma loja de ebooks. Usuários compram e publicam livros digitais, com pedidos, saldo em conta e autenticação via OAuth2/JWT.

## Tecnologias

- Java 17
- Spring Boot 2.7.3 (Web, Data JPA, Validation)
- Spring Security + OAuth2 (JWT, grant type `password`)
- H2 (banco em memória no perfil de teste)
- Lombok
- Maven (com Maven Wrapper)
- Postman (coleção de requisições incluída)

## Funcionalidades

- CRUD de ebooks e categorias
- CRUD de usuários, com perfis de acesso (`ADMIN`, `OPERATOR` e `Visitante`)
- Login com token JWT e senhas criptografadas com BCrypt
- Pedidos de compra com status `PENDENTE` e `PAGO`
- Ao pagar um pedido: o valor é debitado do saldo do comprador, os ebooks entram na biblioteca dele e o valor é creditado ao autor
- Publicação de ebooks pelo próprio autor (`/publish`)
- Consulta e edição dos dados do usuário logado (`/profile`)
- Validação de dados e tratamento global de exceções
- CORS configurável por variável de ambiente

## Estrutura

```
src/main/java/com/example/ebooks/
├── config/          # servidor de autorização, servidor de recursos e segurança
├── controllers/     # endpoints da API
├── services/        # regras de negócio e tratamento de exceções
├── repositories/    # acesso ao banco com Spring Data JPA
├── entities/        # entidades JPA
└── dto/             # objetos de entrada e saída
postman/             # coleção do Postman
```

## Como rodar

**Pré-requisito:** [JDK 17](https://adoptium.net/).

1. Clone o repositório:

   ```bash
   git clone https://github.com/Shermannz/Projeto-Ebooks.git
   cd Projeto-Ebooks
   ```

2. Execute a aplicação:

   ```bash
   # Linux / macOS
   ./mvnw spring-boot:run

   # Windows
   mvnw.cmd spring-boot:run
   ```

A API sobe em `http://localhost:8080`. Por padrão ela usa o perfil `test`, com banco H2 em memória já populado pelo arquivo `import.sql` (usuários, categorias, ebooks e um pedido de exemplo).

O console do H2 fica em `http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:mem:testdb`, usuário `sa`, senha em branco).

## Autenticação

O login é feito em `POST /oauth/token`, com o cliente OAuth2 no Basic Auth (`myclientid` / `myclientsecret`) e os dados do usuário no corpo:

```bash
curl -X POST http://localhost:8080/oauth/token \
  -u myclientid:myclientsecret \
  -d "grant_type=password&username=fulano@gmail.com&password=123456"
