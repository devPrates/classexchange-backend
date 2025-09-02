package com.ClassExchange.usecases.manter_classes;

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
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
@Tag(name = "Classes", description = "API para gerenciamento de classes")
public class ClasseController {

    private final ClasseService classeService;

    @PostMapping
    @Operation(summary = "Criar nova classe", description = "Cria uma nova classe no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Classe criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Disciplina ou turma não encontrada")
    })
    public ResponseEntity<ClasseResponse> criar(@Valid @RequestBody ClasseRequest request) {
        ClasseResponse response = classeService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar todas as classes", description = "Retorna uma lista com todas as classes cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de classes retornada com sucesso")
    public ResponseEntity<List<ClasseResponse>> listarTodos() {
        List<ClasseResponse> classes = classeService.listarTodos();
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar classe por ID", description = "Retorna uma classe específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classe encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Classe não encontrada")
    })
    public ResponseEntity<ClasseResponse> buscarPorId(
            @Parameter(description = "ID da classe", required = true)
            @PathVariable UUID id) {
        Optional<ClasseResponse> response = classeService.buscarPorId(id);
        return response.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar classe", description = "Atualiza os dados de uma classe existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classe atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Classe, disciplina ou turma não encontrada")
    })
    public ResponseEntity<ClasseResponse> atualizar(
            @Parameter(description = "ID da classe", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody ClasseRequest request) {
        ClasseResponse response = classeService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar classe", description = "Remove uma classe do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Classe deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Classe não encontrada")
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID da classe", required = true)
            @PathVariable UUID id) {
        classeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}