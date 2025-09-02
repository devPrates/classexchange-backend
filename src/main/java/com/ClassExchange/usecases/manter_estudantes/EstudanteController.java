package com.ClassExchange.usecases.manter_estudantes;

import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/estudantes")
@RequiredArgsConstructor
@Tag(name = "Estudantes", description = "Operações relacionadas aos estudantes")
public class EstudanteController {

    private final EstudanteService service;

    @PostMapping
    @Operation(summary = "Criar um novo estudante", description = "Cria um novo estudante no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estudante criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<EstudanteResponse> criar(@Valid @RequestBody EstudanteRequest request) {
        EstudanteResponse response = service.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar todos os estudantes", description = "Retorna uma lista com todos os estudantes cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de estudantes retornada com sucesso")
    public ResponseEntity<List<EstudanteResponse>> listarTodos() {
        List<EstudanteResponse> estudantes = service.listarTodos();
        return ResponseEntity.ok(estudantes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar estudante por ID", description = "Retorna um estudante específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudante encontrado"),
            @ApiResponse(responseCode = "404", description = "Estudante não encontrado")
    })
    public ResponseEntity<EstudanteResponse> buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar estudante", description = "Atualiza os dados de um estudante existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudante atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Estudante não encontrado")
    })
    public ResponseEntity<EstudanteResponse> atualizar(@PathVariable UUID id, @Valid @RequestBody EstudanteRequest request) {
        EstudanteResponse response = service.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar estudante", description = "Remove um estudante do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Estudante deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estudante não encontrado")
    })
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}