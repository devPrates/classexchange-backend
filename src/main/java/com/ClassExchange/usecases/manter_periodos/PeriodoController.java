package com.ClassExchange.usecases.manter_periodos;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/periodos")
@RequiredArgsConstructor
@Tag(name = "Períodos", description = "API para gerenciamento de períodos")
public class PeriodoController {

    private final PeriodoService periodoService;

    @PostMapping
    @Operation(summary = "Criar novo período", description = "Cria um novo período no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Período criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Disciplina ou turma não encontrada")
    })
    public ResponseEntity<PeriodoResponse> criar(@Valid @RequestBody PeriodoRequest request) {
        PeriodoResponse response = periodoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar todos os períodos", description = "Retorna uma lista com todos os períodos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de períodos retornada com sucesso")
    public ResponseEntity<List<PeriodoResponse>> listarTodos() {
        List<PeriodoResponse> periodos = periodoService.listarTodos();
        return ResponseEntity.ok(periodos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar período por ID", description = "Retorna um período específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Período encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Período não encontrado")
    })
    public ResponseEntity<PeriodoResponse> buscarPorId(
            @Parameter(description = "ID do período", required = true)
            @PathVariable UUID id) {
        PeriodoResponse response = periodoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar período", description = "Atualiza os dados de um período existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Período atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Período, disciplina ou turma não encontrada")
    })
    public ResponseEntity<PeriodoResponse> atualizar(
            @Parameter(description = "ID do período", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody PeriodoRequest request) {
        PeriodoResponse response = periodoService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar período", description = "Remove um período do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Período deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Período não encontrado")
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do período", required = true)
            @PathVariable UUID id) {
        periodoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}