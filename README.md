# 📚 FórumHub API

API REST para gerenciamento de tópicos de um fórum, desenvolvida em **Java + Spring Boot**, com autenticação via **JWT** e persistência em **PostgreSQL**.

---

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3**
- **Spring Security**
- **JWT (JSON Web Token)**
- **PostgreSQL**
- **Maven**
- **Lombok**
- **Hibernate/JPA**

---

## 📌 Funcionalidades

- Cadastro e autenticação de usuários
- Geração de token JWT
- CRUD de tópicos (protegido por autenticação)
- Validação de duplicidade de tópicos
- Controle de acesso via filtro de segurança
- Tratamento de exceções personalizadas

---

## 📂 Estrutura do Projeto

\`\`\`
src
├── main
│   ├── java/com/seuusuario/forumhub
│   │   ├── controller      # Controladores REST
│   │   ├── domain          # Entidades e repositórios
│   │   ├── dto             # Objetos de transferência de dados
│   │   ├── infra/security  # Configurações e filtros de segurança
│   │   ├── service         # Regras de negócio
│   │   └── exception       # Classes de exceção personalizada
│   └── resources
│       ├── application.properties
│       └── db/migration    # Scripts SQL (Flyway)
\`\`\`

---

## ⚙️ Configuração do Ambiente

### 1️⃣ Clonar o repositório
\`\`\`bash
git clone https://github.com/seuusuario/forumhub.git
cd forumhub
\`\`\`

### 2️⃣ Configurar o banco de dados
Crie um banco no PostgreSQL:
\`\`\`sql
CREATE DATABASE forumhub;
\`\`\`

### 3️⃣ Configurar o \`application.properties\`
\`\`\`properties
spring.datasource.url=jdbc:postgresql://localhost:5432/forumhub
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update

api.security.token.secret=uma_senha_secreta
jwt.expiration=3600000
\`\`\`

---

## ▶️ Executando o Projeto

\`\`\`bash
mvn spring-boot:run
\`\`\`

A aplicação estará disponível em:
\`\`\`
http://localhost:8080
\`\`\`

---

## 🔐 Autenticação JWT

### 1. Gerar Token
- **POST** \`/login\`
- Body:
  \`\`\`json
  {
  "login": "usuario",
  "senha": "senha"
  }
  \`\`\`
  ![Login Request](docs/images/login.png)

### 2. Usar Token nas Requisições
- Enviar no Header:
  \`\`\`
  Authorization: Bearer SEU_TOKEN_AQUI
  \`\`\`
  ![Token Header](docs/images/token-header.png)

---

## 📬 Endpoints Principais

| Método | Endpoint           | Descrição                      | Autenticação |
|--------|--------------------|----------------------------------|--------------|
| POST   | \`/login\`           | Autentica e retorna token JWT   | ❌           |
| GET    | \`/topicos\`         | Lista todos os tópicos          | ✅           |
| POST   | \`/topicos\`         | Cadastra novo tópico            | ✅           |
| PUT    | \`/topicos/{id}\`    | Atualiza um tópico              | ✅           |
| DELETE | \`/topicos/{id}\`    | Remove um tópico                | ✅           |

![Listando Tópicos](docs/images/list-topics.gif)

---

## 🧪 Testando com Postman/Insomnia

1. Faça login em \`/login\` para obter o token.
2. Nas próximas requisições, envie o token no header **Authorization**.
3. Teste todos os endpoints conforme descrito acima.

![Testando API](docs/images/test-api.gif)

---

## Desenvolvido por Fabio Cruz
