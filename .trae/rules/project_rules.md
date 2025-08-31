# Project Rules

Este documento define o padrão arquitetural, de código e de boas práticas para o desenvolvimento desta API em Java Spring Boot.

---

## Arquitetura do Projeto

- O projeto deve utilizar Java Spring Boot.
- Banco de dados: PostgreSQL hospedado no Neon/Supabase.
- Todas as entidades devem ser colocadas no pacote `entity`.
- Cada caso de uso deve ficar dentro de `usecase`, com um subpacote chamado `manter_<entidade>`.
- Dentro de cada `manter_<entidade>` devem estar:
  - Controller
  - Service
  - Repository
  - Request DTO
  - Response DTO
- Não criar uma pasta `dto` separada — os DTOs ficam junto dos arquivos do caso de uso.
- Criar uma pasta `exception` para centralizar o tratamento global de erros e exceções.
- Criar uma pasta `enums` para armazenar todos os tipos enumerados utilizados no projeto.

---

## BaseEntity

- Todas as entidades devem herdar de `BaseEntity`.
- `BaseEntity` deve conter:
  - Campo `id` do tipo UUID gerado automaticamente.
  - Campos `createdAt` e `updatedAt` para auditoria.
  - Lógica automática para preencher `createdAt` na criação e atualizar `updatedAt` em modificações (`@PrePersist` e `@PreUpdate`).

---

## 🧱 Padrão para CRUD

Para cada entidade:

1. **Entity**
   - Nome no singular.
   - Usar Lombok (`@Getter`, `@Setter`, `@NoArgsConstructor`).
   - Quando necessário, utilizar `@AllArgsConstructor` e `@Builder`.

2. **Request DTO**
   - Nome: `<Entidade>Request`.
   - Tipo: `record`.
   - Contém apenas dados de entrada.
   - Utilizar anotações do Jakarta Validation (`@NotBlank`, `@Email`, `@Size`, etc.) para validar os campos.

3. **Response DTO**
   - Nome: `<Entidade>Response`.
   - Tipo: `record`.
   - Contém o ID, os campos da entidade e as datas de criação e atualização.

4. **Repository**
   - Deve estender `JpaRepository` com o tipo de ID `UUID`.

5. **Service**
   - Contém a lógica de negócio.
   - Faz a conversão entre Entity e DTO.
   - Lança exceções customizadas (`NotFoundException`, `BusinessException`) quando necessário.
   - Retorna Optional em métodos de busca por ID quando aplicável.

6. **Controller**
   - Mapeado com a rota no plural (ex.: `/professores`, `/campus`).
   - Expõe endpoints para criação, listagem, busca por ID, atualização e exclusão.
   - Utilizar `@Valid` nos parâmetros de Request DTO para validação automática.
   - Evitar lógica de negócio no Controller — concentrar no Service.

---

## 📌 Tratamento Global de Exceções

### Pacote `exception`

- **ApiExceptionHandler.java**
  - Classe anotada com `@ControllerAdvice`.
  - Métodos `@ExceptionHandler` para capturar exceções customizadas e genéricas.
  - Retorna respostas no formato padronizado (`ErrorResponse`).
  - Tratar `MethodArgumentNotValidException` para erros de validação de campos.

- **ErrorResponse.java**
  - DTO (`record`) para resposta de erro.
  - Campos: `message`, `status`, `timestamp`.

- **BusinessException.java**
  - Exceção para erros de regra de negócio (`400 Bad Request`).

- **NotFoundException.java**
  - Exceção para recurso não encontrado (`404 Not Found`).

---

## 📦 Dependências obrigatórias no Spring Initializr

- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Lombok
- Spring Boot DevTools
- Springdoc OpenAPI UI (Swagger)
- Validation (`spring-boot-starter-validation`)

---

## 📂 Pacote de Enums

- Criar um pacote chamado `enums` na raiz do projeto.
- Cada enum deve ter nome no singular e seguir o padrão `UPPER_SNAKE_CASE` para os valores.
- Usar enums para representar valores fixos no sistema (ex.: status, tipos, categorias).

---

## Boas Práticas

- Nomes de classes no singular e com a primeira letra maiúscula.
- Nomes de pacotes em minúsculo e com `_` para separar palavras.
- DTOs sempre no mesmo pacote do caso de uso.
- Utilizar Lombok para reduzir boilerplate.
- Utilizar `record` para DTOs.
- Evitar lógica complexa no Controller — concentrar no Service.
- Criar exceções customizadas para cenários previsíveis.
- Configurar Swagger/OpenAPI para documentação automática.
- Garantir que todos os endpoints tratem erros de forma consistente via `ApiExceptionHandler`.
- Sempre validar entradas de dados com `@Valid` e anotações do Jakarta Validation.

---

**Objetivo:**  
Este documento serve como guia oficial para manter a consistência do código e garantir que todas as novas funcionalidades sigam o mesmo padrão arquitetural e de desenvolvimento.