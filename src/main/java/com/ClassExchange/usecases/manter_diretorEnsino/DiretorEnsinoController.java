package com.ClassExchange.usecases.manter_diretorEnsino;

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
@RequestMapping("/api/diretorEnsino")
@Tag(name = "Diretor de Ensino", description = "API para gerenciamento de diretores de ensino")
public class DiretorEnsinoController {

    @Autowired
    private DiretorEnsinoService diretorEnsinoService;

    @PostMapping
    @Operation(summary = "Criar uma nova direção de ensino", description = "Cria uma nova associação entre professor e campus como diretor de ensino")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Direção criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<DiretorEnsinoResponse> criar(@Valid @RequestBody DiretorEnsinoRequest request) {
        DiretorEnsinoResponse response = diretorEnsinoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar todas as direções de ensino", description = "Retorna uma lista com todas as direções cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de direções retornada com sucesso")
    public ResponseEntity<List<DiretorEnsinoResponse>> listarTodos() {
        List<DiretorEnsinoResponse> direcoes = diretorEnsinoService.listarTodos();
        return ResponseEntity.ok(direcoes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar direção por ID", description = "Retorna uma direção específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Direção encontrada"),
            @ApiResponse(responseCode = "404", description = "Direção não encontrada")
    })
    public ResponseEntity<DiretorEnsinoResponse> buscarPorId(@PathVariable UUID id) {
        Optional<DiretorEnsinoResponse> direcao = diretorEnsinoService.buscarPorId(id);
        return direcao.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/campus/{campusId}")
    @Operation(summary = "Diretor de ensino ativo por campus", description = "Retorna o diretor de ensino ativo para o campus informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diretor de ensino retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Diretor de ensino não encontrado para o campus")
    })
    public ResponseEntity<DiretorEnsinoResponse> buscarAtivoPorCampus(@PathVariable UUID campusId) {
        Optional<DiretorEnsinoResponse> direcao = diretorEnsinoService.buscarAtivoPorCampus(campusId);
        return direcao.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar direção de ensino", description = "Atualiza os dados de uma direção existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Direção atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Direção não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<DiretorEnsinoResponse> atualizar(@PathVariable UUID id, @Valid @RequestBody DiretorEnsinoRequest request) {
        try {
            DiretorEnsinoResponse response = diretorEnsinoService.atualizar(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar direção de ensino", description = "Remove uma direção do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Direção deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Direção não encontrada")
    })
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        try {
            diretorEnsinoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
