package com.ClassExchange.usecases.manter_professores;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/professores")
@Tag(name = "Professores", description = "API para gerenciamento de professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    @Operation(summary = "Criar um novo professor", description = "Cria um novo professor no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Professor criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<ProfessorResponse> criar(@Valid @RequestBody ProfessorRequest request) {
        ProfessorResponse response = professorService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar todos os professores", description = "Retorna uma lista com todos os professores cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de professores retornada com sucesso")
    public ResponseEntity<List<ProfessorResponse>> listarTodos() {
        List<ProfessorResponse> professores = professorService.listarTodos();
        return ResponseEntity.ok(professores);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar professor por ID", description = "Retorna um professor específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professor encontrado"),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    })
    public ResponseEntity<ProfessorResponse> buscarPorId(@PathVariable UUID id) {
        Optional<ProfessorResponse> professor = professorService.buscarPorId(id);
        return professor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar professor", description = "Atualiza os dados de um professor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professor atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<ProfessorResponse> atualizar(@PathVariable UUID id, @Valid @RequestBody ProfessorRequest request) {
        try {
            ProfessorResponse response = professorService.atualizar(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar professor", description = "Remove um professor do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Professor deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    })
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        try {
            professorService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}