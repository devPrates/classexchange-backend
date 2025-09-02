# Arquitetura do Sistema

## 📐 Visão Geral

O ClassExchange segue uma arquitetura em camadas baseada no padrão **Clean Architecture** e **Domain-Driven Design (DDD)**.

## 🏗️ Estrutura de Camadas

### 1. **Domain Layer** (`domain/`)
- **Entidades**: Modelos de negócio com relacionamentos JPA
- **Enums**: Tipos enumerados do sistema
- **BaseEntity**: Classe base com auditoria (ID, timestamps)

### 2. **Use Cases Layer** (`usecases/`)
- **Controllers**: Endpoints REST e validações
- **Services**: Lógica de negócio e regras
- **Repositories**: Interfaces de acesso a dados
- **DTOs**: Request/Response para transferência de dados

### 3. **Infrastructure Layer**
- **Config**: Configurações do Spring (CORS, Swagger)
- **Exception**: Tratamento global de erros

## 📦 Padrão de Organização

Cada funcionalidade segue o padrão `manter_<entidade>`:

```
manter_professores/
├── ProfessorController.java    # REST endpoints
├── ProfessorService.java       # Lógica de negócio
├── ProfessorRepository.java    # Acesso a dados
├── ProfessorRequest.java       # DTO de entrada
└── ProfessorResponse.java      # DTO de saída
```

## 🔄 Fluxo de Dados

```
Client → Controller → Service → Repository → Database
   ↓        ↓         ↓         ↓
 Response ← DTO ← Entity ← JPA ← PostgreSQL
```

## 🛡️ Princípios Aplicados

- **Single Responsibility**: Cada classe tem uma responsabilidade
- **Dependency Inversion**: Dependências via interfaces
- **Open/Closed**: Extensível sem modificar código existente
- **Separation of Concerns**: Camadas bem definidas

## 📋 Convenções

### Nomenclatura
- **Entidades**: Singular (Professor, Classe)
- **Controllers**: `<Entidade>Controller`
- **Services**: `<Entidade>Service`
- **DTOs**: `<Entidade>Request/Response`
- **Endpoints**: Plural (`/api/professores`)

### Validações
- **Request DTOs**: Jakarta Validation (`@NotBlank`, `@Email`)
- **Services**: Regras de negócio e exceções customizadas
- **Global Handler**: Tratamento centralizado de erros

## 🗄️ Persistência

- **ORM**: Spring Data JPA
- **Database**: PostgreSQL
- **IDs**: UUID para todas as entidades
- **Auditoria**: Timestamps automáticos via `@PrePersist`/`@PreUpdate`

## 📡 API Design

- **REST**: Endpoints seguem convenções RESTful
- **HTTP Status**: Códigos apropriados (200, 201, 404, 400)
- **Documentação**: Swagger/OpenAPI automático
- **Validação**: Automática via `@Valid`