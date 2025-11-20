package com.ClassExchange.usecases.manter_cursos;

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
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
@Tag(name = "Cursos", description = "API para gerenciamento de cursos")
public class CursoController {

    private final CursoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar um novo curso", description = "Cria um novo curso no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Campus não encontrado")
    })
    public CursoResponse criar(@Valid @RequestBody CursoRequest request) {
        return service.criar(request);
    }

    @GetMapping
    @Operation(summary = "Listar todos os cursos", description = "Retorna uma lista com todos os cursos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de cursos retornada com sucesso")
    public List<CursoResponse> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar curso por ID", description = "Retorna um curso específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso encontrado"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public CursoResponse buscarPorId(
            @Parameter(description = "ID do curso", required = true)
            @PathVariable UUID id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar curso", description = "Atualiza os dados de um curso existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public CursoResponse atualizar(
            @Parameter(description = "ID do curso", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody CursoRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar curso", description = "Remove um curso do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public void deletar(
            @Parameter(description = "ID do curso", required = true)
            @PathVariable UUID id) {
        service.deletar(id);
    }

    @GetMapping("/slug/{slug}")
    @Operation(summary = "Buscar curso por slug", description = "Retorna um curso específico pelo seu slug")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso encontrado"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public CursoResponse buscarPorSlug(
            @Parameter(description = "Slug do curso", required = true)
            @PathVariable String slug) {
        return service.buscarPorSlug(slug)
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));
    }

    @GetMapping("/{id}/estudantes")
    @Operation(summary = "Listar estudantes do curso", description = "Retorna todos os estudantes vinculados ao curso informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudantes retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public java.util.List<com.ClassExchange.usecases.manter_cursos.CursoResponse.EstudanteSimplificado> listarEstudantesDoCurso(
            @Parameter(description = "ID do curso", required = true)
            @PathVariable java.util.UUID id) {
        return service.listarEstudantesDoCurso(id);
    }

    @GetMapping("/{id}/periodos")
    @Operation(summary = "Listar períodos do curso", description = "Retorna todos os períodos vinculados ao curso, com disciplinas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Períodos retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public java.util.List<com.ClassExchange.usecases.manter_periodos.PeriodoCursoResponse> listarPeriodosDoCurso(
            @Parameter(description = "ID do curso", required = true)
            @PathVariable java.util.UUID id) {
        return service.listarPeriodosDoCurso(id);
    }
}