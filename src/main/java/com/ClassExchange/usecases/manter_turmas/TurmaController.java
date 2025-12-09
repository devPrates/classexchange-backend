package com.ClassExchange.usecases.manter_turmas;

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
@RequestMapping("/api/turmas")
@RequiredArgsConstructor
@Tag(name = "Turmas", description = "API para gerenciamento de turmas")
public class TurmaController {

    private final TurmaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar uma nova turma", description = "Cria uma nova turma no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Turma criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public TurmaResponse criar(@Valid @RequestBody TurmaRequest request) {
        return service.criar(request);
    }

    @GetMapping
    @Operation(summary = "Listar todas as turmas", description = "Retorna uma lista com todas as turmas cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de turmas retornada com sucesso")
    public List<TurmaResponse> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/slug/{slug}")
    @Operation(summary = "Buscar turma por slug", description = "Retorna uma turma específica pelo seu slug com períodos e disciplinas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turma encontrada"),
            @ApiResponse(responseCode = "404", description = "Turma não encontrada")
    })
    public TurmaComPeriodosResponse buscarPorSlug(
            @Parameter(description = "Slug da turma", required = true)
            @PathVariable String slug) {
        return service.buscarPorSlugComPeriodos(slug)
                .orElseThrow(() -> new NotFoundException("Turma não encontrada"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar turma por ID", description = "Retorna uma turma específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turma encontrada"),
            @ApiResponse(responseCode = "404", description = "Turma não encontrada")
    })
    public TurmaResponse buscarPorId(
            @Parameter(description = "ID da turma", required = true)
            @PathVariable UUID id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new NotFoundException("Turma não encontrada"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar turma", description = "Atualiza os dados de uma turma existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turma atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Turma não encontrada")
    })
    public TurmaResponse atualizar(
            @Parameter(description = "ID da turma", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody TurmaRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar turma", description = "Remove uma turma do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Turma deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Turma não encontrada")
    })
    public void deletar(
            @Parameter(description = "ID da turma", required = true)
            @PathVariable UUID id) {
        service.deletar(id);
    }

    @GetMapping("/curso/{cursoId}")
    @Operation(summary = "Listar turmas por ID de curso", description = "Retorna todas as turmas pertencentes ao curso informado")
    @ApiResponse(responseCode = "200", description = "Lista de turmas retornada com sucesso")
    public List<TurmaResponse> listarPorCursoId(
            @Parameter(description = "ID do curso", required = true)
            @PathVariable UUID cursoId) {
        return service.listarPorCursoId(cursoId);
    }

    @GetMapping("/curso/slug/{slug}")
    @Operation(summary = "Listar turmas por slug de curso", description = "Retorna todas as turmas pertencentes ao curso informado pelo slug")
    @ApiResponse(responseCode = "200", description = "Lista de turmas retornada com sucesso")
    public List<TurmaResponse> listarPorCursoSlug(
            @Parameter(description = "Slug do curso", required = true)
            @PathVariable String slug) {
        return service.listarPorCursoSlug(slug);
    }
}
