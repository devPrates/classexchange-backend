package com.ClassExchange.usecases.manter_disciplinas;

import com.ClassExchange.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/disciplinas")
@RequiredArgsConstructor
@Tag(name = "Disciplinas", description = "API para gerenciamento de disciplinas")
public class DisciplinaController {

    private final DisciplinaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar uma nova disciplina", description = "Cria uma nova disciplina no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Disciplina criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public DisciplinaResponse criar(@Valid @RequestBody DisciplinaRequest request) {
        return service.criar(request);
    }

    @GetMapping
    @Operation(summary = "Listar todas as disciplinas", description = "Retorna uma lista com todas as disciplinas cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de disciplinas retornada com sucesso")
    public List<DisciplinaResponse> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar disciplina por ID", description = "Retorna uma disciplina específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disciplina encontrada"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada")
    })
    public DisciplinaResponse buscarPorId(
            @Parameter(description = "ID da disciplina", required = true)
            @PathVariable UUID id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar disciplina", description = "Atualiza os dados de uma disciplina existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disciplina atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada")
    })
    public DisciplinaResponse atualizar(
            @Parameter(description = "ID da disciplina", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody DisciplinaRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar disciplina", description = "Remove uma disciplina do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Disciplina deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada")
    })
    public void deletar(
            @Parameter(description = "ID da disciplina", required = true)
            @PathVariable UUID id) {
        service.deletar(id);
    }
}