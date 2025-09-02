package com.ClassExchange.usecases.manter_professorClasse;

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
@RequestMapping("/api/professorClasse")
@Tag(name = "Professor Classe", description = "API para gerenciamento de associações entre professores e classes")
public class ProfessorClasseController {

    @Autowired
    private ProfessorClasseService professorClasseService;

    @PostMapping
    @Operation(summary = "Criar uma nova associação professor-classe", description = "Cria uma nova associação entre professor e classe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Associação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<ProfessorClasseResponse> criar(@Valid @RequestBody ProfessorClasseRequest request) {
        ProfessorClasseResponse response = professorClasseService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar todas as associações professor-classe", description = "Retorna uma lista com todas as associações cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de associações retornada com sucesso")
    public ResponseEntity<List<ProfessorClasseResponse>> listarTodos() {
        List<ProfessorClasseResponse> associacoes = professorClasseService.listarTodos();
        return ResponseEntity.ok(associacoes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar associação por ID", description = "Retorna uma associação específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Associação encontrada"),
            @ApiResponse(responseCode = "404", description = "Associação não encontrada")
    })
    public ResponseEntity<ProfessorClasseResponse> buscarPorId(@PathVariable UUID id) {
        Optional<ProfessorClasseResponse> associacao = professorClasseService.buscarPorId(id);
        return associacao.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar associação professor-classe", description = "Atualiza os dados de uma associação existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Associação atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Associação não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<ProfessorClasseResponse> atualizar(@PathVariable UUID id, @Valid @RequestBody ProfessorClasseRequest request) {
        try {
            ProfessorClasseResponse response = professorClasseService.atualizar(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar associação professor-classe", description = "Remove uma associação do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Associação deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Associação não encontrada")
    })
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        try {
            professorClasseService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}