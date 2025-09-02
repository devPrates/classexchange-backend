# API Reference

## 📡 Endpoints Principais

### Base URL
```
http://localhost:8080/api
```

## 👨‍🏫 Professores

### Listar Professores
```http
GET /api/professores
```

### Criar Professor
```http
POST /api/professores
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "siape": "1234567",
  "celular": "(11) 99999-9999"
}
```

### Buscar Professor por ID
```http
GET /api/professores/{id}
```

### Atualizar Professor
```http
PUT /api/professores/{id}
Content-Type: application/json

{
  "nome": "João Silva Santos",
  "email": "joao.santos@email.com",
  "siape": "1234567",
  "celular": "(11) 88888-8888"
}
```

### Deletar Professor
```http
DELETE /api/professores/{id}
```

## 🎓 Estudantes

### Endpoints Disponíveis
- `GET /api/estudantes` - Listar todos
- `POST /api/estudantes` - Criar novo
- `GET /api/estudantes/{id}` - Buscar por ID
- `PUT /api/estudantes/{id}` - Atualizar
- `DELETE /api/estudantes/{id}` - Deletar

## 📚 Disciplinas

### Endpoints Disponíveis
- `GET /api/disciplinas` - Listar todas
- `POST /api/disciplinas` - Criar nova
- `GET /api/disciplinas/{id}` - Buscar por ID
- `PUT /api/disciplinas/{id}` - Atualizar
- `DELETE /api/disciplinas/{id}` - Deletar

## 🏫 Campus

### Endpoints Disponíveis
- `GET /api/campus` - Listar todos
- `POST /api/campus` - Criar novo
- `GET /api/campus/{id}` - Buscar por ID
- `PUT /api/campus/{id}` - Atualizar
- `DELETE /api/campus/{id}` - Deletar

## 📖 Cursos

### Endpoints Disponíveis
- `GET /api/cursos` - Listar todos
- `POST /api/cursos` - Criar novo
- `GET /api/cursos/{id}` - Buscar por ID
- `PUT /api/cursos/{id}` - Atualizar
- `DELETE /api/cursos/{id}` - Deletar

## 🔗 Relacionamentos

### Professor-Classe
- `GET /api/professorClasse` - Listar associações
- `POST /api/professorClasse` - Criar associação
- `PUT /api/professorClasse/{id}` - Atualizar
- `DELETE /api/professorClasse/{id}` - Remover

### Coordenador-Curso
- `GET /api/coordenadorCurso` - Listar coordenações
- `POST /api/coordenadorCurso` - Criar coordenação
- `PUT /api/coordenadorCurso/{id}` - Atualizar
- `DELETE /api/coordenadorCurso/{id}` - Remover

### Diretor-Ensino
- `GET /api/diretorEnsino` - Listar direções
- `POST /api/diretorEnsino` - Criar direção
- `PUT /api/diretorEnsino/{id}` - Atualizar
- `DELETE /api/diretorEnsino/{id}` - Remover

## 📋 Códigos de Status

| Código | Descrição |
|--------|----------|
| 200 | OK - Sucesso |
| 201 | Created - Recurso criado |
| 400 | Bad Request - Dados inválidos |
| 404 | Not Found - Recurso não encontrado |
| 500 | Internal Server Error - Erro interno |

## 🔍 Documentação Interativa

Acesse a documentação completa e interativa:
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## 📝 Exemplo de Resposta de Erro

```json
{
  "message": "Professor não encontrado com ID: 123e4567-e89b-12d3-a456-426614174000",
  "status": 404,
  "timestamp": "2024-01-15T10:30:00"
}
```

## 🔐 Autenticação

> **Nota**: Atualmente a API não possui autenticação implementada. Todos os endpoints são públicos.