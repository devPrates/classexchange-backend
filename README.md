# ClassExchange Backend

## 📋 Descrição

API REST para gerenciamento acadêmico desenvolvida em Java Spring Boot. O ClassExchange permite o controle de professores, estudantes, disciplinas, classes e relacionamentos acadêmicos em instituições de ensino.

## 🚀 Tecnologias

- **Java 21+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **Swagger/OpenAPI**
- **Jakarta Validation**
- **Lombok**

## 📁 Estrutura do Projeto

```
src/main/java/com/ClassExchange/
├── config/                 # Configurações (CORS, Swagger)
├── domain/
│   ├── entity/            # Entidades JPA
│   └── enums/             # Enumerações
├── exception/             # Tratamento global de exceções
└── usecases/              # Casos de uso (CRUD)
    ├── manter_professores/
    ├── manter_estudantes/
    ├── manter_disciplinas/
    └── ...
```

## 🏗️ Arquitetura

Cada caso de uso segue o padrão:
- **Controller** → Endpoints REST
- **Service** → Lógica de negócio
- **Repository** → Acesso a dados
- **Request/Response DTOs** → Transferência de dados

## 🔧 Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.6+
- PostgreSQL

### Passos

1. **Clone o repositório**
```bash
git clone <url-do-repositorio>
cd classexchange-backend
```

2. **Configure o banco de dados**

O projeto já vem configurado com banco H2 (em memória) para facilitar os testes. Verifique se no arquivo `application.properties` o perfil ativo está apontando para `test`:

```properties
spring.profiles.active=test
```

As configurações do H2 estão no arquivo `application-test.properties`. Para usar PostgreSQL em produção, altere o perfil para `dev` e configure as variáveis de ambiente.

3. **Execute a aplicação**
```bash
./mvnw spring-boot:run
```

4. **Acesse a documentação**
- Swagger UI: http://localhost:8080/swagger-ui.html
- API Docs: http://localhost:8080/v3/api-docs

## 📚 Documentação

Veja a pasta `docs/` para documentação detalhada:
- [Arquitetura](docs/arquitetura.md)
- [API Reference](docs/api-reference.md)

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature
3. Commit suas mudanças
4. Push para a branch
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT.
