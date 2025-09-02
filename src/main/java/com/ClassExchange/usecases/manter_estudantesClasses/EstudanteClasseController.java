package com.ClassExchange.usecases.manter_estudantesClasses;

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
@RequestMapping("/api/estudantes-classes")
@RequiredArgsConstructor
@Tag(name = "EstudantesClasses", description = "Operações relacionadas às matrículas de estudantes em classes")
public class EstudanteClasseController {

    private final EstudanteClasseService service;

    @PostMapping
    @Operation(summary = "Criar uma nova matrícula de estudante em classe", description = "Cria uma nova matrícula de estudante em uma classe específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Matrícula criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Estudante ou Classe não encontrados")
    })
    public ResponseEntity<EstudanteClasseResponse> criar(@Valid @RequestBody EstudanteClasseRequest request) {
        EstudanteClasseResponse response = service.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar todas as matrículas", description = "Retorna uma lista com todas as matrículas de estudantes em classes")
    @ApiResponse(responseCode = "200", description = "Lista de matrículas retornada com sucesso")
    public ResponseEntity<List<EstudanteClasseResponse>> listarTodos() {
        List<EstudanteClasseResponse> estudantesClasses = service.listarTodos();
        return ResponseEntity.ok(estudantesClasses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar matrícula por ID", description = "Retorna uma matrícula específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula encontrada"),
            @ApiResponse(responseCode = "404", description = "Matrícula não encontrada")
    })
    public ResponseEntity<EstudanteClasseResponse> buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar matrícula", description = "Atualiza os dados de uma matrícula existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Matrícula, Estudante ou Classe não encontrados")
    })
    public ResponseEntity<EstudanteClasseResponse> atualizar(@PathVariable UUID id, @Valid @RequestBody EstudanteClasseRequest request) {
        EstudanteClasseResponse response = service.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar matrícula", description = "Remove uma matrícula do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Matrícula deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Matrícula não encontrada")
    })
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}